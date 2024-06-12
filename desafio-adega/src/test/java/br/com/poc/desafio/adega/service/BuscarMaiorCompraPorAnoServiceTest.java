package br.com.poc.desafio.adega.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.poc.desafio.adega.domain.entity.ClienteComprasEntity;
import br.com.poc.desafio.adega.domain.response.CompraResponse;
import br.com.poc.desafio.adega.mapper.CompraResponseMapper;
import br.com.poc.desafio.adega.repository.ClienteCompraRepository;
import br.com.poc.desafio.adega.stub.RandomStub;

@ExtendWith(MockitoExtension.class)
class BuscarMaiorCompraPorAnoServiceTest {

    @InjectMocks
    private BuscarMaiorCompraPorAnoService service;

    @Mock
    private ClienteCompraRepository clienteCompraRepository;

    @Spy
    private CompraResponseMapper mapper;

    @Test
    void deveBuscarMaiorCompraComSucesso() {
        final Integer ano = 1;
        final var clienteComprasEntity = RandomStub.generateClienteCompraEntity();

        when(clienteCompraRepository.findFirstByIdVinhoAnoCompraOrderByValorTotalDesc(any()))
            .thenReturn(Optional.of(clienteComprasEntity));

        final var atual = service.buscar(ano);

        verify(mapper).apply(clienteComprasEntity);

        assertEquals(clienteComprasEntity.getQuantidade(), atual.getQuantidade());
        assertEquals(clienteComprasEntity.getValorTotal(), atual.getValorTotal());
        assertEquals(clienteComprasEntity.getId().getCliente().getCpf(), atual.getCpfCliente());
        assertEquals(clienteComprasEntity.getId().getCliente().getNome(), atual.getNomeCliente());

        assertEquals(clienteComprasEntity.getId().getVinho().getAnoCompra(), atual.getProduto().getAnoCompra());
        assertEquals(clienteComprasEntity.getId().getVinho().getTipoVinho(), atual.getProduto().getTipoVinho());
        assertEquals(clienteComprasEntity.getId().getVinho().getPreco(), atual.getProduto().getPreco());
        assertEquals(clienteComprasEntity.getId().getVinho().getSafra(), atual.getProduto().getSafra());
    }
}