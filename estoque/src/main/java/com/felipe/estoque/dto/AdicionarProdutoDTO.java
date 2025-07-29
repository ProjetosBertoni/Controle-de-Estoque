package com.felipe.estoque.dto;

public record AdicionarProdutoDTO(
        String nome,
        int quantidade,
        double preco
) {
}
