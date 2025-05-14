package com.biblioteca.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.biblioteca.biblioteca.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Busca um usuário pelo email
    Usuario findByEmail(String email);

    // Busca um usuário pelo nome
    List<Usuario> findByNome(String nome);

    // Busca usuários por role (ex: ROLE_USER, ROLE_ADMIN)
    List<Usuario> findByRole(String role);

    // Busca usuários pela matrícula
    Usuario findByMatricula(String matricula);

    // Exemplo de consulta personalizada com @Query
    @Query("SELECT u FROM Usuario u WHERE u.role = :role AND u.matricula LIKE :matricula")
    List<Usuario> findUsuariosByRoleAndMatricula(String role, String matricula);

    // Exemplo de consulta para contar usuários com um papel específico
    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.role = :role")
    long countUsuariosByRole(String role);
}
