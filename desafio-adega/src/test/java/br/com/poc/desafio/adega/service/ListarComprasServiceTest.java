package br.com.poc.desafio.adega.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.poc.desafio.adega.domain.response.CompraResponse;
import br.com.poc.desafio.adega.domain.response.CompraResponse.ProdutoResponse;
import br.com.poc.desafio.adega.mapper.CompraResponseMapper;
import br.com.poc.desafio.adega.repository.ClienteCompraRepository;
import br.com.poc.desafio.adega.stub.RandomStub;

@ExtendWith(MockitoExtension.class)
class ListarComprasServiceTest {

    @InjectMocks
    private ListarComprasService service;

    @Mock
    private ClienteCompraRepository clienteCompraRepository;

    @Spy
    private CompraResponseMapper mapper;

    @Test
    void deveListarComprasComSucesso() {
        final var clienteComprasEntity = RandomStub.generateClienteCompraEntity();

        final var compraResponse = CompraResponse.builder()
            .cpfCliente(clienteComprasEntity.getId().getCliente().getCpf())
            .nomeCliente(clienteComprasEntity.getId().getCliente().getNome())
            .quantidade(clienteComprasEntity.getQuantidade())
            .valorTotal(clienteComprasEntity.getValorTotal())
            .produto(ProdutoResponse.builder()
                .anoCompra(clienteComprasEntity.getId().getVinho().getAnoCompra())
                .safra(clienteComprasEntity.getId().getVinho().getSafra())
                .preco(clienteComprasEntity.getId().getVinho().getPreco())
                .tipoVinho(clienteComprasEntity.getId().getVinho().getTipoVinho())
                .build())
            .build();

        when(clienteCompraRepository.findAllByOrderByIdVinhoPrecoAsc())
            .thenReturn(Collections.singletonList(clienteComprasEntity));

        final var atual = service.listar();

        verify(clienteCompraRepository).findAllByOrderByIdVinhoPrecoAsc();
        verify(mapper).apply(clienteComprasEntity);

        assertEquals(compraResponse.getNomeCliente(), atual.get(0).getNomeCliente());
        assertEquals(compraResponse.getCpfCliente(), atual.get(0).getCpfCliente());
        assertEquals(compraResponse.getQuantidade(), atual.get(0).getQuantidade());
        assertEquals(compraResponse.getValorTotal(), atual.get(0).getValorTotal());

        assertEquals(compraResponse.getProduto().getAnoCompra(), atual.get(0).getProduto().getAnoCompra());
        assertEquals(compraResponse.getProduto().getSafra(), atual.get(0).getProduto().getSafra());
        assertEquals(compraResponse.getProduto().getPreco(), atual.get(0).getProduto().getPreco());
        assertEquals(compraResponse.getProduto().getTipoVinho(), atual.get(0).getProduto().getTipoVinho());
    }
}