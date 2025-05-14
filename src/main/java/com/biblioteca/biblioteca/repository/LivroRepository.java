package com.biblioteca.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.biblioteca.biblioteca.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    // Consulta para encontrar livros por título
    List<Livro> findByTitulo(String titulo);

    // Consulta para encontrar livros por autor
    List<Livro> findByAutor(String autor);

    // Consulta para encontrar livros por categoria
    List<Livro> findByCategoria(String categoria);

    // Consulta para verificar se o livro está disponível
    List<Livro> findByDisponivel(boolean disponivel);

    // Exemplo de consulta personalizada com @Query
    @Query("SELECT l FROM Livro l WHERE l.autor = :autor AND l.categoria = :categoria")
    List<Livro> findLivrosByAutorAndCategoria(String autor, String categoria);

    // Exemplo de consulta para contar o número de livros disponíveis
    @Query("SELECT COUNT(l) FROM Livro l WHERE l.disponivel = true")
    long countLivrosDisponiveis();
}
