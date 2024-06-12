package br.com.poc.desafio.adega.domain.response.mock;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;

@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record VinhoMockResponse(
    Integer codigo,
    String tipoVinho,
    BigDecimal preco,
    Integer safra,
    Integer anoCompra
) {

}
