package com.biblioteca.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.biblioteca.biblioteca.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Busca um usuário pelo email (case insensitive)
    Usuario findByEmailIgnoreCase(String email);

    // Busca usuários pelo nome (case insensitive)
    List<Usuario> findByNomeIgnoreCase(String nome);

    // Busca usuários por role (ex: ROLE_USER, ROLE_ADMIN)
    List<Usuario> findByRole(String role);

    // Busca usuário pela matrícula
    Usuario findByMatricula(String matricula);

    // Busca usuários pelo nome contendo parte do texto (case insensitive)
    List<Usuario> findByNomeContainingIgnoreCase(String nome);

    // Consulta personalizada: busca usuários por role e matrícula (matrícula parcial)
    @Query("SELECT u FROM Usuario u WHERE u.role = :role AND u.matricula LIKE %:matricula%")
    List<Usuario> findUsuariosByRoleAndMatricula(String role, String matricula);

    // Conta usuários com um papel específico
    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.role = :role")
    long countUsuariosByRole(String role);
}
