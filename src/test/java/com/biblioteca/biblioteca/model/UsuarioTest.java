package com.biblioteca.biblioteca.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void testConstrutorPadrao() {
        Usuario usuario = new Usuario();
        assertNotNull(usuario, "O construtor padrão deve criar uma instância não nula");
    }

    @Test
    void testConstrutorComParametros() {
        Usuario usuario = new Usuario("João Silva", "joao@email.com", "senha123", "ADMIN", "2023001");
        
        assertEquals("João Silva", usuario.getNome());
        assertEquals("joao@email.com", usuario.getEmail());
        assertEquals("senha123", usuario.getSenha());
        assertEquals("ADMIN", usuario.getRole());
        assertEquals("2023001", usuario.getMatricula());
    }

    @Test
    void testGettersAndSetters() {
        Usuario usuario = new Usuario();
        
        usuario.setId(1L);
        assertEquals(1L, usuario.getId());
        
        usuario.setNome("Maria Oliveira");
        assertEquals("Maria Oliveira", usuario.getNome());
        
        usuario.setEmail("maria@email.com");
        assertEquals("maria@email.com", usuario.getEmail());
        
        usuario.setSenha("novaSenha456");
        assertEquals("novaSenha456", usuario.getSenha());
        
        usuario.setRole("USER");
        assertEquals("USER", usuario.getRole());
        
        usuario.setMatricula("2023002");
        assertEquals("2023002", usuario.getMatricula());
    }

    @Test
    void testEquals() {
        Usuario usuario1 = new Usuario("João Silva", "joao@email.com", "senha123", "ADMIN", "2023001");
        usuario1.setId(1L);
        
        Usuario usuario2 = new Usuario("João Silva", "joao@email.com", "senha123", "ADMIN", "2023001");
        usuario2.setId(1L);
        
        Usuario usuario3 = new Usuario("Maria Oliveira", "maria@email.com", "outraSenha", "USER", "2023002");
        usuario3.setId(2L);
        
        assertEquals(usuario1, usuario1, "Uma instância deve ser igual a ela mesma");
        
        assertEquals(usuario1, usuario2, "Instâncias com mesmo id e email devem ser iguais");
        
        assertNotEquals(usuario1, usuario3, "Instâncias com id/email diferentes devem ser diferentes");
        
        assertNotEquals(null, usuario1, "Comparação com null deve retornar false");
        
        assertNotEquals(usuario1, new Object(), "Comparação com classe diferente deve retornar false");
    }

    @Test
    void testHashCode() {
        Usuario usuario1 = new Usuario("João Silva", "joao@email.com", "senha123", "ADMIN", "2023001");
        usuario1.setId(1L);
        
        Usuario usuario2 = new Usuario("João Silva", "joao@email.com", "senha123", "ADMIN", "2023001");
        usuario2.setId(1L);
        
        Usuario usuario3 = new Usuario("Maria Oliveira", "maria@email.com", "outraSenha", "USER", "2023002");
        usuario3.setId(2L);
        
        assertEquals(usuario1.hashCode(), usuario2.hashCode(), "Hash codes devem ser iguais para objetos iguais");
        assertNotEquals(usuario1.hashCode(), usuario3.hashCode(), "Hash codes devem ser diferentes para objetos diferentes");
    }

    @Test
    void testToString() {
        Usuario usuario = new Usuario("João Silva", "joao@email.com", "senha123", "ADMIN", "2023001");
        usuario.setId(1L);
        
        String expected = "Usuario{id=1, nome='João Silva', email='joao@email.com', role='ADMIN', matricula='2023001'}";
        assertEquals(expected, usuario.toString(), "toString deve retornar a representação esperada");
    }
}