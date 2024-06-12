package br.com.poc.desafio.adega.web.v1;

import static java.util.Optional.ofNullable;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.poc.desafio.adega.domain.response.ClienteResponse;
import br.com.poc.desafio.adega.domain.response.VinhoResponse;
import br.com.poc.desafio.adega.service.BuscarRecomendacaoVinhoClienteService;
import br.com.poc.desafio.adega.service.ListarClientesFieisService;
import lombok.RequiredArgsConstructor;

@RequestMapping("/v1")
@RestController
@RequiredArgsConstructor
public class ClienteController implements ClienteApi {

    private final ListarClientesFieisService listarClientesFieisService;
    private final BuscarRecomendacaoVinhoClienteService buscarRecomendacaoVinhoClienteService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/clientes-fieis")
    public ResponseEntity<List<ClienteResponse>> clientesFieis() {
        return ResponseEntity
            .ok()
            .body(listarClientesFieisService.listar());
    }

    @GetMapping("/recomendacao/{cpf}/tipo")
    public ResponseEntity<VinhoResponse> recomendacoes(@PathVariable("cpf") final String cpf) {
        return ofNullable(buscarRecomendacaoVinhoClienteService.buscar(cpf))
            .map(vinhoResponse -> ResponseEntity
                .ok()
                .body(vinhoResponse)
            ).orElse(ResponseEntity.notFound().build());
    }

}
