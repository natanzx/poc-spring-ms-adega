package br.com.poc.desafio.adega.domain.response;

import lombok.Builder;

@Builder
public record ClienteResponse(
    String nome,
    String cpf
) {

}
