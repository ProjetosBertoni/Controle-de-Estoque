package com.felipe.estoque.service;

import com.felipe.estoque.domain.Produto;
import com.felipe.estoque.dto.AdicionarProdutoDTO;
import com.felipe.estoque.dto.AtualizarProduto;
import com.felipe.estoque.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarTodosProdutos(){
        return produtoRepository.findAll();
    }

    public Produto adicionarProdutos(AdicionarProdutoDTO dto){
        Produto novoProduto = new Produto();
        novoProduto.setNome(dto.nome());
        novoProduto.setPreco(dto.preco());
        novoProduto.setQuantidade(dto.quantidade());
        return produtoRepository.save(novoProduto);

    }

    public void remover(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        produtoRepository.delete(produto);
    }

    public Produto atualizarProduto(Long id, AtualizarProduto dto){
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        produto.setNome(dto.nome());
        produto.setQuantidade(dto.quantidade());
        produto.setPreco(dto.preco());
        return produtoRepository.save(produto);


    }


}
