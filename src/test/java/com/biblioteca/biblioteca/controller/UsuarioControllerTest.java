package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.model.Usuario;
import com.biblioteca.biblioteca.repository.UsuarioRepository;
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
class UsuarioControllerTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioController usuarioController;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("João Silva");
        usuario.setEmail("joao@email.com");
        usuario.setSenha("senha123");
        usuario.setRole("USER");
        usuario.setMatricula("2023001");
    }

    @Test
    void listarTodos_DeveRetornarListaDeUsuarios() {
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario));

        List<Usuario> resultado = usuarioController.listarTodos();

        assertEquals(1, resultado.size());
        assertEquals("João Silva", resultado.get(0).getNome());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void listarTodos_QuandoNaoHouverUsuarios_DeveRetornarListaVazia() {
        when(usuarioRepository.findAll()).thenReturn(List.of());

        List<Usuario> resultado = usuarioController.listarTodos();
        assertTrue(resultado.isEmpty());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void buscarPorId_QuandoUsuarioExistir_DeveRetornarUsuario() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        ResponseEntity<Usuario> resposta = usuarioController.buscarPorId(1L);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
        assertEquals("joao@email.com", resposta.getBody().getEmail());
        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    void buscarPorId_QuandoUsuarioNaoExistir_DeveRetornarNotFound() {
        when(usuarioRepository.findById(2L)).thenReturn(Optional.empty());

        ResponseEntity<Usuario> resposta = usuarioController.buscarPorId(2L);

        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
        assertNull(resposta.getBody());
        verify(usuarioRepository, times(1)).findById(2L);
    }

    @Test
    void criar_DeveSalvarERetornarUsuario() {
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome("Maria Oliveira");
        novoUsuario.setEmail("maria@email.com");
        
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario resultado = usuarioController.criar(novoUsuario);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("João Silva", resultado.getNome());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void atualizar_QuandoUsuarioExistir_DeveAtualizarERetornarUsuario() {
        Usuario usuarioAtualizado = new Usuario();
        usuarioAtualizado.setNome("João Silva Atualizado");
        usuarioAtualizado.setEmail("joao.novo@email.com");
        usuarioAtualizado.setRole("ADMIN");
        
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioAtualizado);

        ResponseEntity<Usuario> resposta = usuarioController.atualizar(1L, usuarioAtualizado);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
        assertEquals("João Silva Atualizado", resposta.getBody().getNome());
        assertEquals("ADMIN", resposta.getBody().getRole());
        verify(usuarioRepository, times(1)).findById(1L);
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void atualizar_QuandoUsuarioNaoExistir_DeveRetornarNotFound() {
        Usuario usuarioAtualizado = new Usuario();
        usuarioAtualizado.setNome("Usuário Inexistente");
        
        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Usuario> resposta = usuarioController.atualizar(99L, usuarioAtualizado);

        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
        assertNull(resposta.getBody());
        verify(usuarioRepository, times(1)).findById(99L);
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    @Test
    void deletar_QuandoUsuarioExistir_DeveDeletarERetornarNoContent() {
        when(usuarioRepository.existsById(1L)).thenReturn(true);
        doNothing().when(usuarioRepository).deleteById(1L);

        ResponseEntity<Void> resposta = usuarioController.deletar(1L);

        assertEquals(HttpStatus.NO_CONTENT, resposta.getStatusCode());
        assertNull(resposta.getBody());
        verify(usuarioRepository, times(1)).existsById(1L);
        verify(usuarioRepository, times(1)).deleteById(1L);
    }

    @Test
    void deletar_QuandoUsuarioNaoExistir_DeveRetornarNotFound() {
        when(usuarioRepository.existsById(99L)).thenReturn(false);

        ResponseEntity<Void> resposta = usuarioController.deletar(99L);

        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
        assertNull(resposta.getBody());
        verify(usuarioRepository, times(1)).existsById(99L);
        verify(usuarioRepository, never()).deleteById(anyLong());
    }

    @Test
    void criar_DeveValidarCamposObrigatorios() {
        Usuario usuarioInvalido = new Usuario();
        usuarioInvalido.setNome(null); 
        
        when(usuarioRepository.save(any(Usuario.class))).thenThrow(new IllegalArgumentException());

        assertThrows(IllegalArgumentException.class, () -> {
            usuarioController.criar(usuarioInvalido);
        });
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void atualizar_DeveAtualizarTodosOsCampos() {
        Usuario usuarioAtualizado = new Usuario();
        usuarioAtualizado.setNome("Novo Nome");
        usuarioAtualizado.setEmail("novo@email.com");
        usuarioAtualizado.setSenha("novaSenha");
        usuarioAtualizado.setRole("ADMIN");
        usuarioAtualizado.setMatricula("2023002");
        
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioAtualizado);

        ResponseEntity<Usuario> resposta = usuarioController.atualizar(1L, usuarioAtualizado);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        Usuario usuarioRetornado = resposta.getBody();
        assertNotNull(usuarioRetornado);
        assertEquals("Novo Nome", usuarioRetornado.getNome());
        assertEquals("novo@email.com", usuarioRetornado.getEmail());
        assertEquals("novaSenha", usuarioRetornado.getSenha());
        assertEquals("ADMIN", usuarioRetornado.getRole());
        assertEquals("2023002", usuarioRetornado.getMatricula());
    }
}