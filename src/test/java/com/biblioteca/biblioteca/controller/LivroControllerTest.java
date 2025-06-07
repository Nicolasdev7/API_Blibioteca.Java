package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.model.Livro;
import com.biblioteca.biblioteca.repository.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LivroControllerTest {

    @Mock
    private LivroRepository livroRepository;

    @InjectMocks
    private LivroController livroController;

    private Livro livro;

    @BeforeEach
    void setup() {
        livro = new Livro();
        livro.setId(1L);
        livro.setTitulo("Dom Casmurro");
        livro.setAutor("Machado de Assis");
        livro.setCategoria("Literatura Brasileira");
        livro.setDisponivel(true);
    }

    // Testes existentes (mantidos para completude)
    @Test
    void testListarTodos() {
        when(livroRepository.findAll()).thenReturn(Arrays.asList(livro));

        List<Livro> result = livroController.listarTodos();

        assertEquals(1, result.size());
        assertEquals("Dom Casmurro", result.get(0).getTitulo());
        verify(livroRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPorIdEncontrado() {
        when(livroRepository.findById(1L)).thenReturn(Optional.of(livro));

        ResponseEntity<Livro> response = livroController.buscarPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Machado de Assis", response.getBody().getAutor());
        verify(livroRepository, times(1)).findById(1L);
    }

    // Testes adicionais para cobrir todos os cenários

    @Test
    void testBuscarPorIdNaoEncontrado() {
        when(livroRepository.findById(2L)).thenReturn(Optional.empty());

        ResponseEntity<Livro> response = livroController.buscarPorId(2L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(livroRepository, times(1)).findById(2L);
    }

    @Test
    void testCriarLivro() {
        Livro novoLivro = new Livro();
        novoLivro.setTitulo("Memórias Póstumas");
        novoLivro.setAutor("Machado de Assis");
        
        when(livroRepository.save(any(Livro.class))).thenReturn(livro);

        Livro resultado = livroController.criar(novoLivro);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Dom Casmurro", resultado.getTitulo());
        verify(livroRepository, times(1)).save(any(Livro.class));
    }

    @Test
    void testAtualizarLivroExistente() {
        Livro livroAtualizado = new Livro();
        livroAtualizado.setTitulo("Dom Casmurro - Edição Especial");
        livroAtualizado.setAutor("Machado de Assis");
        livroAtualizado.setCategoria("Clássicos Brasileiros");
        livroAtualizado.setDisponivel(false);

        when(livroRepository.findById(1L)).thenReturn(Optional.of(livro));
        when(livroRepository.save(any(Livro.class))).thenReturn(livroAtualizado);

        ResponseEntity<Livro> response = livroController.atualizar(1L, livroAtualizado);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Dom Casmurro - Edição Especial", response.getBody().getTitulo());
        assertEquals("Clássicos Brasileiros", response.getBody().getCategoria());
        assertFalse(response.getBody().isDisponivel());
        verify(livroRepository, times(1)).findById(1L);
        verify(livroRepository, times(1)).save(any(Livro.class));
    }

    @Test
    void testAtualizarLivroNaoExistente() {
        Livro livroAtualizado = new Livro();
        livroAtualizado.setTitulo("Livro Inexistente");

        when(livroRepository.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Livro> response = livroController.atualizar(99L, livroAtualizado);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(livroRepository, times(1)).findById(99L);
        verify(livroRepository, never()).save(any(Livro.class));
    }

    @Test
    void testDeletarLivroExistente() {
        when(livroRepository.findById(1L)).thenReturn(Optional.of(livro));
        doNothing().when(livroRepository).deleteById(1L);

        ResponseEntity<Void> response = livroController.deletar(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(livroRepository, times(1)).findById(1L);
        verify(livroRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeletarLivroNaoExistente() {
        when(livroRepository.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Void> response = livroController.deletar(99L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(livroRepository, times(1)).findById(99L);
        verify(livroRepository, never()).deleteById(anyLong());
    }

    @Test
    void testListarTodosVazio() {
        when(livroRepository.findAll()).thenReturn(List.of());

        List<Livro> result = livroController.listarTodos();

        assertTrue(result.isEmpty());
        verify(livroRepository, times(1)).findAll();
    }
}