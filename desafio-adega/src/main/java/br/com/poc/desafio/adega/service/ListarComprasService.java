package br.com.poc.desafio.adega.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.poc.desafio.adega.domain.response.CompraResponse;
import br.com.poc.desafio.adega.mapper.CompraResponseMapper;
import br.com.poc.desafio.adega.repository.ClienteCompraRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ListarComprasService {

    private final ClienteCompraRepository clienteCompraRepository;
    private final CompraResponseMapper mapper;

    public List<CompraResponse> listar() {
        log.info("Listando compras ordenadas de forma crescente por valor total");

        return clienteCompraRepository.findAllByOrderByIdVinhoPrecoAsc().stream()
            .map(mapper)
            .collect(Collectors.toList());
    }
}
