package br.com.poc.desafio.adega.web.v1;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.poc.desafio.adega.domain.response.ClienteResponse;
import br.com.poc.desafio.adega.domain.response.VinhoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cliente", description = "Serviços referentes ao cliente de uma Adega")
public interface ClienteApi {

    @Operation(summary = "Lista do top 3 clientes fieis.",
        description = "Lista do top 3 clientes fieis.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso."),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "500", description = "Falha inesperada")
        }
    )
    ResponseEntity<List<ClienteResponse>> clientesFieis();

    @Operation(summary = "Retornar uma recomendação de vinho baseado nos tipos de vinho que o cliente mais compra.",
        description = "Retornar uma recomendação de vinho baseado nos tipos de vinho que o cliente mais compra.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Cpf não informado."),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "500", description = "Falha inesperada")
        }
    )
    ResponseEntity<VinhoResponse> recomendacoes(
        @Parameter(description = "Cpf do cliente", example = "11111111111") final String cpf);

}
