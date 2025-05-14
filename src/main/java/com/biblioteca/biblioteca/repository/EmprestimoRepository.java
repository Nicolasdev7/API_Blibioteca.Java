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

    // Busca empréstimos com base no status de devolução
    List<Emprestimo> findByDevolvido(boolean devolvido);

    // Exemplo de consulta personalizada com @Query
    @Query("SELECT e FROM Emprestimo e WHERE e.usuario.id = :usuarioId AND e.devolvido = :devolvido")
    List<Emprestimo> findEmprestimosByUsuarioAndDevolvido(Long usuarioId, boolean devolvido);

    // Exemplo de consulta com JOIN (caso precise acessar os detalhes do livro ou do usuário)
    @Query("SELECT e FROM Emprestimo e JOIN e.livro l WHERE l.id = :livroId AND e.devolvido = :devolvido")
    List<Emprestimo> findEmprestimosByLivroAndDevolvido(Long livroId, boolean devolvido);
}
