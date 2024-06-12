package br.com.poc.desafio.adega.service;

import org.springframework.stereotype.Service;

import br.com.poc.desafio.adega.domain.response.VinhoResponse;
import br.com.poc.desafio.adega.repository.VinhoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BuscarRecomendacaoVinhoClienteService {

    private final VinhoRepository vinhoRepository;

    public VinhoResponse buscar(final String cpf) {
        log.info("Buscando recomendação de vinho para o cliente {}", cpf);

        return vinhoRepository.findRecomendacao(cpf)
            .map(vinho -> VinhoResponse.builder()
                .tipoVinho(vinho.getTipoVinho())
                .safra(vinho.getSafra())
                .anoCompra(vinho.getAnoCompra())
                .preco(vinho.getPreco())
                .build())
            .orElse(null);
    }
}
