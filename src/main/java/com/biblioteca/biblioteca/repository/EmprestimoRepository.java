package com.biblioteca.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.biblioteca.biblioteca.model.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    // Busca empréstimos por usuário
    List<Emprestimo> findByUsuarioId(Long usuarioId);

    // Busca empréstimos por livro
    List<Emprestimo> findByLivroId(Long livroId);

    // Busca empréstimos que ainda não foram devolvidos
    @Query("SELECT e FROM Emprestimo e WHERE e.dataDevolucao IS NULL")
    List<Emprestimo> findEmprestimosEmAberto();

    // Busca empréstimos já devolvidos
    @Query("SELECT e FROM Emprestimo e WHERE e.dataDevolucao IS NOT NULL")
    List<Emprestimo> findEmprestimosDevolvidos();

    // Busca empréstimos em aberto de um usuário específico
    @Query("SELECT e FROM Emprestimo e WHERE e.usuario.id = :usuarioId AND e.dataDevolucao IS NULL")
    List<Emprestimo> findEmprestimosEmAbertoPorUsuario(Long usuarioId);

    // Busca empréstimos em aberto de um livro específico
    @Query("SELECT e FROM Emprestimo e WHERE e.livro.id = :livroId AND e.dataDevolucao IS NULL")
    List<Emprestimo> findEmprestimosEmAbertoPorLivro(Long livroId);

    // Consulta personalizada: empréstimos por usuário e status de devolução
    @Query("SELECT e FROM Emprestimo e WHERE e.usuario.id = :usuarioId AND ((:devolvido = true AND e.dataDevolucao IS NOT NULL) OR (:devolvido = false AND e.dataDevolucao IS NULL))")
    List<Emprestimo> findEmprestimosByUsuarioAndDevolvido(Long usuarioId, boolean devolvido);

    // Consulta personalizada: empréstimos por livro e status de devolução
    @Query("SELECT e FROM Emprestimo e WHERE e.livro.id = :livroId AND ((:devolvido = true AND e.dataDevolucao IS NOT NULL) OR (:devolvido = false AND e.dataDevolucao IS NULL))")
    List<Emprestimo> findEmprestimosByLivroAndDevolvido(Long livroId, boolean devolvido);
}
