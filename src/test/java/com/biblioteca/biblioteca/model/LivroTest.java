package com.biblioteca.biblioteca.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

class LivroTest {

    @Test
    void testConstrutorPadrao() {
        Livro livro = new Livro();
        assertNotNull(livro, "O construtor padrão deve criar uma instância não nula");
        assertTrue(livro.isDisponivel(), "Por padrão, o livro deve estar disponível");
        assertNotNull(livro.getEmprestimos(), "A lista de empréstimos deve ser inicializada");
        assertEquals(0, livro.getEmprestimos().size(), "A lista de empréstimos deve estar vazia inicialmente");
    }

    @Test
    void testConstrutorComParametros() {
        Livro livro = new Livro("Dom Casmurro", "Machado de Assis", "Literatura Brasileira");
        
        assertEquals("Dom Casmurro", livro.getTitulo());
        assertEquals("Machado de Assis", livro.getAutor());
        assertEquals("Literatura Brasileira", livro.getCategoria());
        assertTrue(livro.isDisponivel(), "O livro deve estar disponível ao ser criado");
        assertNotNull(livro.getEmprestimos(), "A lista de empréstimos deve ser inicializada");
    }

    @Test
    void testGettersAndSetters() {
        Livro livro = new Livro();
        
        livro.setId(1L);
        assertEquals(1L, livro.getId());
        
        livro.setTitulo("O Alienista");
        assertEquals("O Alienista", livro.getTitulo());
        
        livro.setAutor("Machado de Assis");
        assertEquals("Machado de Assis", livro.getAutor());
        
        livro.setCategoria("Contos");
        assertEquals("Contos", livro.getCategoria());
        
        livro.setDisponivel(false);
        assertFalse(livro.isDisponivel());
        
        List<Emprestimo> emprestimos = new ArrayList<>();
        Emprestimo emprestimo = new Emprestimo();
        emprestimos.add(emprestimo);
        livro.setEmprestimos(emprestimos);
        assertEquals(1, livro.getEmprestimos().size());
        assertSame(emprestimos, livro.getEmprestimos());
    }

    @Test
    void testEquals() {
        Livro livro1 = new Livro("Dom Casmurro", "Machado de Assis", "Literatura Brasileira");
        livro1.setId(1L);
        
        Livro livro2 = new Livro("Memórias Póstumas", "Machado de Assis", "Romance");
        livro2.setId(1L);
        
        Livro livro3 = new Livro("Dom Casmurro", "Machado de Assis", "Literatura Brasileira");
        livro3.setId(2L);
        
        assertEquals(livro1, livro1, "Uma instância deve ser igual a ela mesma");
        
        assertEquals(livro1, livro2, "Instâncias com mesmo id devem ser iguais");
        
        assertNotEquals(livro1, livro3, "Instâncias com ids diferentes devem ser diferentes");
        
        assertNotEquals(null, livro1, "Comparação com null deve retornar false");
        
        assertNotEquals(livro1, new Object(), "Comparação com classe diferente deve retornar false");
    }

    @Test
    void testHashCode() {
        Livro livro1 = new Livro("Dom Casmurro", "Machado de Assis", "Literatura Brasileira");
        livro1.setId(1L);
        
        Livro livro2 = new Livro("Memórias Póstumas", "Machado de Assis", "Romance");
        livro2.setId(1L);
        
        Livro livro3 = new Livro("Dom Casmurro", "Machado de Assis", "Literatura Brasileira");
        livro3.setId(2L);
        
        assertEquals(livro1.hashCode(), livro2.hashCode(), "Hash codes devem ser iguais para objetos com mesmo id");
        assertNotEquals(livro1.hashCode(), livro3.hashCode(), "Hash codes devem ser diferentes para objetos com ids diferentes");
    }

    @Test
    void testToString() {
        Livro livro = new Livro("Dom Casmurro", "Machado de Assis", "Literatura Brasileira");
        livro.setId(1L);
        livro.setDisponivel(false);
        
        String expected = "Livro{id=1, titulo='Dom Casmurro', autor='Machado de Assis', categoria='Literatura Brasileira', disponivel=false}";
        assertEquals(expected, livro.toString(), "toString deve retornar a representação esperada");
    }

    @Test
    void testDisponibilidadePadrao() {
        Livro livro = new Livro();
        assertTrue(livro.isDisponivel(), "Por padrão, o livro deve estar disponível");
        
        livro.setDisponivel(false);
        assertFalse(livro.isDisponivel(), "A disponibilidade deve ser alterável");
    }

    @Test
    void testListaEmprestimos() {
        Livro livro = new Livro();
        assertNotNull(livro.getEmprestimos(), "A lista de empréstimos deve ser inicializada");
        assertEquals(0, livro.getEmprestimos().size(), "A lista de empréstimos deve estar vazia inicialmente");
        
        List<Emprestimo> emprestimos = new ArrayList<>();
        Emprestimo emprestimo = new Emprestimo();
        emprestimos.add(emprestimo);
        
        livro.setEmprestimos(emprestimos);
        assertEquals(1, livro.getEmprestimos().size(), "A lista de empréstimos deve conter o empréstimo adicionado");
    }
}