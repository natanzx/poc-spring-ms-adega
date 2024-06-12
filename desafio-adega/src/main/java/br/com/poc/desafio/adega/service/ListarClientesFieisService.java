package br.com.poc.desafio.adega.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.poc.desafio.adega.domain.response.ClienteResponse;
import br.com.poc.desafio.adega.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ListarClientesFieisService {

    private final ClienteRepository clienteRepository;

    public List<ClienteResponse> listar() {
        log.info("Listando clientes fieis");

        return clienteRepository.findClientesFieis().stream()
            .map(cliente -> ClienteResponse.builder()
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .build())
            .collect(Collectors.toList());
    }
}
