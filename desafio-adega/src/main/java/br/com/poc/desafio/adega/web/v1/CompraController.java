package br.com.poc.desafio.adega.web.v1;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.poc.desafio.adega.domain.response.CompraResponse;
import br.com.poc.desafio.adega.service.BuscarMaiorCompraPorAnoService;
import br.com.poc.desafio.adega.service.ListarComprasService;
import lombok.RequiredArgsConstructor;

@RequestMapping("/v1")
@RestController
@RequiredArgsConstructor
public class CompraController implements CompraApi {

    private final ListarComprasService listarComprasService;
    private final BuscarMaiorCompraPorAnoService buscarMaiorCompraPorAnoService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/compras")
    public ResponseEntity<List<CompraResponse>> compras() {
        return ResponseEntity
            .ok()
            .body(listarComprasService.listar());
    }

    @GetMapping("/maior-compra/{ano}")
    public ResponseEntity<CompraResponse> maiorCompra(
        @PathVariable("ano") final Integer ano) {
        return ResponseEntity
            .ok()
            .body(buscarMaiorCompraPorAnoService.buscar(ano));
    }

}
