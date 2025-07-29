package com.felipe.estoque.controller;

import com.felipe.estoque.domain.Produto;
import com.felipe.estoque.dto.AdicionarProdutoDTO;
import com.felipe.estoque.dto.AtualizarProduto;
import com.felipe.estoque.repository.ProdutoRepository;
import com.felipe.estoque.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    private final ProdutoService produtoService;
    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoService produtoService, ProdutoRepository produtoRepository) {
        this.produtoService = produtoService;
        this.produtoRepository = produtoRepository;
    }


    @GetMapping
    public ResponseEntity<List<Produto>> listarTodosProdutos(){
        List<Produto> produtos = produtoService.listarTodosProdutos();
        if (produtos.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(produtos);
    }

    @PostMapping
    public ResponseEntity<Produto> adicionarProduto(@RequestBody AdicionarProdutoDTO produto) {
        boolean existeProduto = produtoRepository.existsByNome(produto.nome());
        if (!existeProduto){
            Produto produtos = produtoService.adicionarProdutos(produto);
            return ResponseEntity.status(HttpStatus.CREATED).body(produtos);
        }

        return ResponseEntity.badRequest().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Produto> removerProduto(@PathVariable Long id){
        produtoService.remover(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody AtualizarProduto dto) {
        Produto produtoAtualizado = produtoService.atualizarProduto(id, dto);
        return ResponseEntity.ok(produtoAtualizado);
    }

}
