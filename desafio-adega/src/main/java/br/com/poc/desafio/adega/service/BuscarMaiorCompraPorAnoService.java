package br.com.poc.desafio.adega.service;

import org.springframework.stereotype.Service;

import br.com.poc.desafio.adega.domain.response.CompraResponse;
import br.com.poc.desafio.adega.mapper.CompraResponseMapper;
import br.com.poc.desafio.adega.repository.ClienteCompraRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BuscarMaiorCompraPorAnoService {

    private final ClienteCompraRepository clienteCompraRepository;
    private final CompraResponseMapper mapper;

    public CompraResponse buscar(final Integer ano) {
        log.info("Buscando maior compra do ano {}", ano);

        return clienteCompraRepository.findFirstByIdVinhoAnoCompraOrderByValorTotalDesc(ano)
            .map(mapper)
            .orElse(null);
    }
}
