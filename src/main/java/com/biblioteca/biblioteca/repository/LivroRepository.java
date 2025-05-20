package com.biblioteca.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.biblioteca.biblioteca.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    // Busca livros pelo título (case insensitive)
    List<Livro> findByTituloIgnoreCase(String titulo);

    // Busca livros pelo autor (case insensitive)
    List<Livro> findByAutorIgnoreCase(String autor);

    // Busca livros pela categoria (case insensitive)
    List<Livro> findByCategoriaIgnoreCase(String categoria);

    // Busca livros disponíveis ou não
    List<Livro> findByDisponivel(boolean disponivel);

    // Busca livros por autor e categoria (case insensitive)
    @Query("SELECT l FROM Livro l WHERE LOWER(l.autor) = LOWER(:autor) AND LOWER(l.categoria) = LOWER(:categoria)")
    List<Livro> findLivrosByAutorAndCategoria(String autor, String categoria);

    // Conta o número de livros disponíveis
    @Query("SELECT COUNT(l) FROM Livro l WHERE l.disponivel = true")
    long countLivrosDisponiveis();

    // Busca livros pelo título contendo parte do texto (case insensitive)
    List<Livro> findByTituloContainingIgnoreCase(String titulo);

    // Busca livros pelo autor contendo parte do texto (case insensitive)
    List<Livro> findByAutorContainingIgnoreCase(String autor);
}
