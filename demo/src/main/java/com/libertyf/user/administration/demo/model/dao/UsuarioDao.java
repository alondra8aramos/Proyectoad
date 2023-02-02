package com.libertyf.user.administration.demo.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libertyf.user.administration.demo.model.entity.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Long>{

}
