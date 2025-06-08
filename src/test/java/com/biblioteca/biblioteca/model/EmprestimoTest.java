package com.biblioteca.biblioteca.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

class EmprestimoTest {

    private Livro livro;
    private Usuario usuario;
    private Emprestimo emprestimo;

    @BeforeEach
    void setUp() {
        livro = new Livro("Dom Casmurro", "Machado de Assis", "Literatura");
        usuario = new Usuario("João Silva", "joao@email.com", "senha123", "USER", "2023001");
        emprestimo = new Emprestimo(livro, usuario);
    }

    @Test
    void testConstrutorPadrao() {
        Emprestimo emp = new Emprestimo();
        assertNotNull(emp, "O construtor padrão deve criar uma instância não nula");
        assertNull(emp.getLivro(), "Livro deve ser nulo no construtor padrão");
        assertNull(emp.getUsuario(), "Usuário deve ser nulo no construtor padrão");
        assertNull(emp.getDataEmprestimo(), "Data empréstimo deve ser nula no construtor padrão");
        assertNull(emp.getDataDevolucao(), "Data devolução deve ser nula no construtor padrão");
    }

    @Test
    void testConstrutorComParametros() {
        assertNotNull(emprestimo, "O construtor com parâmetros deve criar uma instância não nula");
        assertEquals(livro, emprestimo.getLivro(), "O livro deve ser o mesmo passado no construtor");
        assertEquals(usuario, emprestimo.getUsuario(), "O usuário deve ser o mesmo passado no construtor");
        assertEquals(LocalDate.now(), emprestimo.getDataEmprestimo(), "A data de empréstimo deve ser a data atual");
        assertNull(emprestimo.getDataDevolucao(), "A data de devolução deve ser nula ao criar um empréstimo");
        assertFalse(emprestimo.isDevolvido(), "O empréstimo não deve estar devolvido ao ser criado");
    }

    @Test
    void testGettersAndSetters() {
        Emprestimo emp = new Emprestimo();
        
        emp.setId(1L);
        assertEquals(1L, emp.getId());
        
        Livro novoLivro = new Livro("Memórias Póstumas", "Machado de Assis", "Romance");
        emp.setLivro(novoLivro);
        assertEquals(novoLivro, emp.getLivro());
        
        Usuario novoUsuario = new Usuario("Maria Silva", "maria@email.com", "senha456", "ADMIN", "2023002");
        emp.setUsuario(novoUsuario);
        assertEquals(novoUsuario, emp.getUsuario());
        
        LocalDate dataTeste = LocalDate.of(2023, 1, 15);
        emp.setDataEmprestimo(dataTeste);
        assertEquals(dataTeste, emp.getDataEmprestimo());
        
        emp.setDataDevolucao(dataTeste.plusDays(7));
        assertEquals(dataTeste.plusDays(7), emp.getDataDevolucao());
    }

    @Test
    void testIsDevolvido() {
        assertFalse(emprestimo.isDevolvido(), "Novo empréstimo não deve estar devolvido");
        
        emprestimo.setDataDevolucao(LocalDate.now());
        assertTrue(emprestimo.isDevolvido(), "Empréstimo com data de devolução deve estar marcado como devolvido");
        
        emprestimo.setDataDevolucao(null);
        assertFalse(emprestimo.isDevolvido(), "Empréstimo sem data de devolução não deve estar marcado como devolvido");
    }

    @Test
    void testDevolverEmprestimoJaDevolvido() {
        emprestimo.devolver();
        
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            emprestimo.devolver();
        }, "Tentar devolver um empréstimo já devolvido deve lançar exceção");
        
        assertEquals("Empréstimo já devolvido.", exception.getMessage(), "A mensagem de exceção deve ser a esperada");
    }

    @Test
    void testEquals() {
        Emprestimo emp1 = new Emprestimo(livro, usuario);
        emp1.setId(1L);
        
        Emprestimo emp2 = new Emprestimo(new Livro("Outro Livro", "Outro Autor", "Outra Categoria"), 
                                        new Usuario("Outro Usuário", "outro@email.com", "senha789", "USER", "2023003"));
        emp2.setId(1L);
        
        Emprestimo emp3 = new Emprestimo(livro, usuario);
        emp3.setId(2L);
        
        assertEquals(emp1, emp1, "Uma instância deve ser igual a ela mesma");
        
        assertEquals(emp1, emp2, "Instâncias com mesmo id devem ser iguais");
        
        assertNotEquals(emp1, emp3, "Instâncias com ids diferentes devem ser diferentes");
        
        assertNotEquals(null, emp1, "Comparação com null deve retornar false");
        
        assertNotEquals(emp1, new Object(), "Comparação com classe diferente deve retornar false");
    }

    @Test
    void testHashCode() {
        Emprestimo emp1 = new Emprestimo(livro, usuario);
        emp1.setId(1L);
        
        Emprestimo emp2 = new Emprestimo(new Livro("Outro Livro", "Outro Autor", "Outra Categoria"), 
                                        new Usuario("Outro Usuário", "outro@email.com", "senha789", "USER", "2023003"));
        emp2.setId(1L);
        
        Emprestimo emp3 = new Emprestimo(livro, usuario);
        emp3.setId(2L);
        
        assertEquals(emp1.hashCode(), emp2.hashCode(), "Hash codes devem ser iguais para objetos com mesmo id");
        assertNotEquals(emp1.hashCode(), emp3.hashCode(), "Hash codes devem ser diferentes para objetos com ids diferentes");
    }

    @Test
    void testToString() {
        emprestimo.setId(1L);
        LocalDate dataEmprestimo = LocalDate.of(2023, 6, 1);
        emprestimo.setDataEmprestimo(dataEmprestimo);
        
        String expected = "Emprestimo{id=1, livro=" + livro + ", usuario=" + usuario + 
                          ", dataEmprestimo=" + dataEmprestimo + ", dataDevolucao=null}";
        assertEquals(expected, emprestimo.toString(), "toString deve retornar a representação esperada");
    }
}