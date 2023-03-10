package com.libertyf.user.administration.demo.model.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.libertyf.user.administration.demo.model.entity.Usuario;

public interface IUsuarioService {
	
	public List <Usuario> findAll();
	
	public Page <Usuario> findAll(Pageable pageable);
	
	public Usuario findById(Long id);

	public Usuario save(Usuario usuario);
	
	public void delete(Long id);
	
}
