package com.felipe.estoque.dto;

public record AtualizarProduto(
        String nome,
        int quantidade,
        double preco
) {
}
