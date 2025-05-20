package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.model.Emprestimo;
import com.biblioteca.biblioteca.repository.EmprestimoRepository;
import com.biblioteca.biblioteca.repository.LivroRepository;
import com.biblioteca.biblioteca.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimoRepository emprestimoRepository;
    private final LivroRepository livroRepository;
    private final UsuarioRepository usuarioRepository;

    public EmprestimoController(EmprestimoRepository emprestimoRepository, LivroRepository livroRepository, UsuarioRepository usuarioRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.livroRepository = livroRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<Emprestimo> listarTodos() {
        return emprestimoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> buscarPorId(@PathVariable Long id) {
        Optional<Emprestimo> emprestimo = emprestimoRepository.findById(id);
        return emprestimo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Emprestimo> criar(@RequestBody Emprestimo emprestimo) {
        // Verifica se o livro e usuário existem
        if (!livroRepository.existsById(emprestimo.getLivro().getId()) ||
            !usuarioRepository.existsById(emprestimo.getUsuario().getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(emprestimoRepository.save(emprestimo));
    }

    @PutMapping("/{id}/devolver")
    public ResponseEntity<Emprestimo> devolver(@PathVariable Long id) {
        Optional<Emprestimo> emprestimoOpt = emprestimoRepository.findById(id);
        if (emprestimoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Emprestimo emprestimo = emprestimoOpt.get();
        if (emprestimo.isDevolvido()) {
            return ResponseEntity.badRequest().body(emprestimo);
        }
        emprestimo.devolver();
        return ResponseEntity.ok(emprestimoRepository.save(emprestimo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!emprestimoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        emprestimoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}