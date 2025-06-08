package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.model.Emprestimo;
import com.biblioteca.biblioteca.model.Livro;
import com.biblioteca.biblioteca.model.Usuario;
import com.biblioteca.biblioteca.repository.EmprestimoRepository;
import com.biblioteca.biblioteca.repository.LivroRepository;
import com.biblioteca.biblioteca.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmprestimoControllerTest {

    @Mock
    private EmprestimoRepository emprestimoRepository;

    @Mock
    private LivroRepository livroRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private EmprestimoController emprestimoController;

    private Emprestimo emprestimo;
    private Livro livro;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        livro = new Livro();
        livro.setId(1L);
        livro.setTitulo("Dom Casmurro");
        livro.setDisponivel(false);

        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("João Silva");

        emprestimo = new Emprestimo();
        emprestimo.setId(1L);
        emprestimo.setLivro(livro);
        emprestimo.setUsuario(usuario);
        emprestimo.setDataEmprestimo(LocalDate.now());
    }

    @Test
    void listarTodos_DeveRetornarListaDeEmprestimos() {
        when(emprestimoRepository.findAll()).thenReturn(List.of(emprestimo));

        List<Emprestimo> resultado = emprestimoController.listarTodos();

        assertEquals(1, resultado.size());
        assertEquals("Dom Casmurro", resultado.get(0).getLivro().getTitulo());
        verify(emprestimoRepository, times(1)).findAll();
    }

    @Test
    void listarTodos_QuandoNaoHouverEmprestimos_DeveRetornarListaVazia() {
        when(emprestimoRepository.findAll()).thenReturn(List.of());

        List<Emprestimo> resultado = emprestimoController.listarTodos();

        assertTrue(resultado.isEmpty());
        verify(emprestimoRepository, times(1)).findAll();
    }

    @Test
    void buscarPorId_QuandoEmprestimoExistir_DeveRetornarEmprestimo() {
        when(emprestimoRepository.findById(1L)).thenReturn(Optional.of(emprestimo));

        ResponseEntity<Emprestimo> resposta = emprestimoController.buscarPorId(1L);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
        assertEquals("João Silva", resposta.getBody().getUsuario().getNome());
        verify(emprestimoRepository, times(1)).findById(1L);
    }

    @Test
    void buscarPorId_QuandoEmprestimoNaoExistir_DeveRetornarNotFound() {
        when(emprestimoRepository.findById(2L)).thenReturn(Optional.empty());

        ResponseEntity<Emprestimo> resposta = emprestimoController.buscarPorId(2L);

        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
        assertNull(resposta.getBody());
        verify(emprestimoRepository, times(1)).findById(2L);
    }

    @Test
    void criar_QuandoLivroEUsuarioExistem_DeveCriarEmprestimo() {
        when(livroRepository.existsById(1L)).thenReturn(true);
        when(usuarioRepository.existsById(1L)).thenReturn(true);
        when(emprestimoRepository.save(any(Emprestimo.class))).thenReturn(emprestimo);

        ResponseEntity<Emprestimo> resposta = emprestimoController.criar(emprestimo);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
        assertEquals(1L, resposta.getBody().getId());
        verify(emprestimoRepository, times(1)).save(any(Emprestimo.class));
    }

    @Test
    void criar_QuandoLivroNaoExiste_DeveRetornarBadRequest() {
        when(livroRepository.existsById(1L)).thenReturn(false);

        ResponseEntity<Emprestimo> resposta = emprestimoController.criar(emprestimo);

        assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
        assertNull(resposta.getBody());
        verify(emprestimoRepository, never()).save(any(Emprestimo.class));
    }

    @Test
    void criar_QuandoUsuarioNaoExiste_DeveRetornarBadRequest() {
        when(livroRepository.existsById(1L)).thenReturn(true);
        when(usuarioRepository.existsById(1L)).thenReturn(false);

        ResponseEntity<Emprestimo> resposta = emprestimoController.criar(emprestimo);

        assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
        assertNull(resposta.getBody());
        verify(emprestimoRepository, never()).save(any(Emprestimo.class));
    }

    @Test
    void devolver_QuandoEmprestimoExistirENaoDevolvido_DeveMarcarComoDevolvido() {
        when(emprestimoRepository.findById(1L)).thenReturn(Optional.of(emprestimo));
        when(emprestimoRepository.save(any(Emprestimo.class))).thenReturn(emprestimo);

        ResponseEntity<Emprestimo> resposta = emprestimoController.devolver(1L);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
        assertTrue(resposta.getBody().isDevolvido());
        assertNotNull(resposta.getBody().getDataDevolucao());
        assertTrue(livro.isDisponivel());
        verify(emprestimoRepository, times(1)).findById(1L);
        verify(emprestimoRepository, times(1)).save(any(Emprestimo.class));
    }

    @Test
    void devolver_QuandoEmprestimoNaoExistir_DeveRetornarNotFound() {
        when(emprestimoRepository.findById(2L)).thenReturn(Optional.empty());

        ResponseEntity<Emprestimo> resposta = emprestimoController.devolver(2L);

        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
        assertNull(resposta.getBody());
        verify(emprestimoRepository, never()).save(any(Emprestimo.class));
    }

    @Test
    void devolver_QuandoEmprestimoJaDevolvido_DeveRetornarBadRequest() {
        emprestimo.devolver();
        when(emprestimoRepository.findById(1L)).thenReturn(Optional.of(emprestimo));

        ResponseEntity<Emprestimo> resposta = emprestimoController.devolver(1L);

        assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
        assertTrue(resposta.getBody().isDevolvido());
        verify(emprestimoRepository, never()).save(any(Emprestimo.class));
    }

    @Test
    void deletar_QuandoEmprestimoExistir_DeveDeletarERetornarNoContent() {
        when(emprestimoRepository.existsById(1L)).thenReturn(true);
        doNothing().when(emprestimoRepository).deleteById(1L);

        ResponseEntity<Void> resposta = emprestimoController.deletar(1L);

        assertEquals(HttpStatus.NO_CONTENT, resposta.getStatusCode());
        assertNull(resposta.getBody());
        verify(emprestimoRepository, times(1)).existsById(1L);
        verify(emprestimoRepository, times(1)).deleteById(1L);
    }

    @Test
    void deletar_QuandoEmprestimoNaoExistir_DeveRetornarNotFound() {
        when(emprestimoRepository.existsById(2L)).thenReturn(false);

        ResponseEntity<Void> resposta = emprestimoController.deletar(2L);

        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
        assertNull(resposta.getBody());
        verify(emprestimoRepository, never()).deleteById(anyLong());
    }

    @Test
    void criar_DeveVerificarDisponibilidadeDoLivro() {
        livro.setDisponivel(true);
        when(livroRepository.existsById(1L)).thenReturn(true);
        when(usuarioRepository.existsById(1L)).thenReturn(true);
        when(emprestimoRepository.save(any(Emprestimo.class))).thenReturn(emprestimo);

        ResponseEntity<Emprestimo> resposta = emprestimoController.criar(emprestimo);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        verify(emprestimoRepository, times(1)).save(any(Emprestimo.class));
    }
}