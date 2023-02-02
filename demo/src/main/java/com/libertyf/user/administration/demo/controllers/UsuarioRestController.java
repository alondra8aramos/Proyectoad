package com.libertyf.user.administration.demo.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.libertyf.user.administration.demo.model.services.IUsuarioService;
import com.libertyf.user.administration.demo.model.entity.Usuario;

//Suponiendo que front se encuentra en 4200
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UsuarioRestController {

	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping("/usuarios")
	public List<Usuario> index() {
		return usuarioService.findAll();
	}

	//solo 10 usuarios
	@GetMapping("/usuarios/page/{page}")
	public Page<Usuario> index(@PathVariable Integer page) {
		return usuarioService.findAll(PageRequest.of(page, 10));
	}

	@GetMapping("/usuarios/{id}")
	public Usuario show(@PathVariable Long id) {
		return usuarioService.findById(id);
	}

	@PostMapping("/usuarios/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario create(@ModelAttribute Usuario usuario, @RequestParam("archivo") MultipartFile archivo) {
		Map<String, Object> response = new HashMap<>();
		String nombreArchivo = UUID.randomUUID().toString() + archivo.getOriginalFilename();
		Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
		try {
			Files.copy(archivo.getInputStream(), rutaArchivo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		usuario.setFoto(nombreArchivo);

		return usuarioService.save(usuario);
	}

	@PutMapping("/usuarios/update/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario update(@ModelAttribute Usuario usuario, @PathVariable Long id) {
		Usuario usuarioActual = usuarioService.findById(id);

		usuarioActual.setEmail(usuario.getEmail());
		usuarioActual.setNombre(usuario.getNombre());
		usuarioActual.setGender(usuario.getGender());
		usuarioActual.setEstatus(usuario.getEstatus());

		return usuarioService.save(usuarioActual);
	}

	@DeleteMapping("/usuarios/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
	
		usuarioService.delete(id);
	}

	@PostMapping("/usuarios/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
		Map<String, Object> response = new HashMap<>();

		Usuario usuario = usuarioService.findById(id);

		if (!archivo.isEmpty()) {
			String nombreArchivo = UUID.randomUUID().toString() + archivo.getOriginalFilename();
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();

			try {
				Files.copy(archivo.getInputStream(), rutaArchivo);
			} catch (IOException e) {
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String nombreFotoAnterior = usuario.getFoto();
			if(nombreFotoAnterior !=null && nombreFotoAnterior.length() >0) {
				Path rutaFotoAnterior = Paths.get("Uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}
			}
			usuario.setFoto(nombreArchivo);
			usuarioService.save(usuario);

			response.put("usuario", usuario);

		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
