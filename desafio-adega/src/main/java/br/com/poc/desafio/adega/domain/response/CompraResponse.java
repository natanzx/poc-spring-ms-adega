package br.com.poc.desafio.adega.domain.response;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CompraResponse implements Serializable {

    private static final long serialVersionUID = 1874921215991999389L;

    private final String nomeCliente;
    private final String cpfCliente;
    private final ProdutoResponse produto;

    @JsonProperty("quantidade_das_compras")
    private final Integer quantidade;

    @JsonProperty("valor_total_compra")
    private final BigDecimal valorTotal;

    @Data
    @Builder
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static final class ProdutoResponse implements Serializable {

        private static final long serialVersionUID = -5260011598333051138L;

        private final String tipoVinho;
        private final BigDecimal preco;
        private final Integer safra;
        private final Integer anoCompra;
    }
}
