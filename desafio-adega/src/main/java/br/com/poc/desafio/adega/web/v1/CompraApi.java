package br.com.poc.desafio.adega.web.v1;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.poc.desafio.adega.domain.response.CompraResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Compra", description = "Serviços referentes as compras de uma Adega")
public interface CompraApi {

    @Operation(summary = "Lista das compras ordenadas.",
        description = "Lista das compras ordenadas.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso."),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "500", description = "Falha inesperada")
        }
    )
    ResponseEntity<List<CompraResponse>> compras();

    @Operation(summary = "Retornar a maior compra do ano informando os dados da compra disponibilizados.",
        description = "Retornar a maior compra do ano informando os dados da compra disponibilizados.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Ano não informado."),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "500", description = "Falha inesperada")
        }
    )
    ResponseEntity<CompraResponse> maiorCompra(
        @Parameter(description = "Ano de pequisa", example = "2019") final Integer ano);
}
