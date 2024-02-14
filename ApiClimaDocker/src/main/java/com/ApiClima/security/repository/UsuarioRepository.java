package com.ApiClima.security.repository;

import com.ApiClima.security.entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    Optional<Usuario> findOneByEmail(String email);
    boolean existsByEmail(String email);}
