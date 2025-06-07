package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.controller.LivroController;
import com.biblioteca.biblioteca.model.Livro;
import com.biblioteca.biblioteca.repository.LivroRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LivroControllerTest {

    
    public Long getId() {
    long id = 0;
    return (long) id;
    }

    public void setId(int id) {
    }

    public String getTitulo() {
        return getTitulo();
    }

    public void setTitulo(String titulo) {
    }

    public String getAutor() {
        return getAutor();
    }

    public void setAutor(String autor) {
    }


    @Mock
    private LivroRepository livroRepository;

    @InjectMocks
    private LivroController livroController;

    private Livro livro;

    @BeforeEach
    void setup() {
        livro = new Livro();
        livro.setId(1L);
        livro.setTitulo("Teste");
        livro.setAutor("Autor Teste");
    }
    
    // Teste para listar todos os livros
    @Test
    void testlistarTodos() {
        when(livroRepository.findAll()).thenReturn(Arrays.asList(livro));

        List<Livro> result = livroController.listarTodos();

        assertEquals(1, result.size());
        assertEquals("Teste", result.get(0).getTitulo());
        verify(livroRepository, times(1)).findAll();
    }

    @Test
    void testbuscarPorId() {
        when(livroRepository.findById(1L)).thenReturn(Optional.of(livro));

    ResponseEntity<Livro> response = livroController.buscarPorId(1L);

        assertEquals(200, response.getStatusCodeValue());
    assertEquals("Autor Teste", response.getBody().getAutor());

    }

    @Test
    void testcriar() {
        when(livroRepository.save(any(Livro.class))).thenReturn(livro);

        Livro novo = new Livro();
        novo.setTitulo("Novo");
        novo.setAutor("Autor");

        Livro saved = livroController.criar(novo);

        assertEquals("Teste", saved.getTitulo()); // por causa do mock
    }
}

