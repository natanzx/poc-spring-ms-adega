package br.com.poc.desafio.adega.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.poc.desafio.adega.repository.ClienteRepository;
import br.com.poc.desafio.adega.stub.RandomStub;

@ExtendWith(MockitoExtension.class)
class ListarClientesFieisServiceTest {

    @InjectMocks
    private ListarClientesFieisService service;

    @Mock
    private ClienteRepository clienteRepository;

    @Test
    void deveListarClienteFiesComSucesso() {
        final var clienteEntity = RandomStub.generateCliente();

        when(clienteRepository.findClientesFieis()).thenReturn(Collections.singletonList(clienteEntity));

        final var atual = service.listar();

        verify(clienteRepository).findClientesFieis();

        Assertions.assertEquals(clienteEntity.getCpf(), atual.get(0).cpf());
        Assertions.assertEquals(clienteEntity.getNome(), atual.get(0).nome());
    }
}