package br.com.poc.desafio.adega.domain.response.mock;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteCompraMockResponse implements Serializable {

    private static final long serialVersionUID = -4225758735096691056L;

    private String nome;
    private String cpf;
    private List<CompraMockReponse> compras;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static final class CompraMockReponse implements Serializable {

        private static final long serialVersionUID = -135936871486503869L;

        private Integer codigo;
        private Integer quantidade;
    }
}
