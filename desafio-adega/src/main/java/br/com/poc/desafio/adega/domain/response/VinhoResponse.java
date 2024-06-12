package br.com.poc.desafio.adega.domain.response;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record VinhoResponse(
    String tipoVinho,
    BigDecimal preco,
    Integer safra,
    Integer anoCompra
) {

}
