package br.com.poc.desafio.adega.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import br.com.poc.desafio.adega.domain.entity.ClienteComprasEntity;
import br.com.poc.desafio.adega.domain.response.CompraResponse;
import br.com.poc.desafio.adega.domain.response.CompraResponse.ProdutoResponse;

@Service
public class CompraResponseMapper implements Function<ClienteComprasEntity, CompraResponse> {

    @Override
    public CompraResponse apply(final ClienteComprasEntity compra) {
        return CompraResponse.builder()
            .nomeCliente(compra.getId().getCliente().getNome())
            .cpfCliente(compra.getId().getCliente().getCpf())
            .produto(ProdutoResponse.builder()
                .tipoVinho(compra.getId().getVinho().getTipoVinho())
                .preco(compra.getId().getVinho().getPreco())
                .safra(compra.getId().getVinho().getSafra())
                .anoCompra(compra.getId().getVinho().getAnoCompra())
                .build())
            .quantidade(compra.getQuantidade())
            .valorTotal(compra.getValorTotal())
            .build();
    }
}
