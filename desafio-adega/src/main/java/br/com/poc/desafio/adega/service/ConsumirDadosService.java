package br.com.poc.desafio.adega.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.poc.desafio.adega.domain.entity.ClienteComprasEntity;
import br.com.poc.desafio.adega.domain.entity.ClienteComprasEntityPk;
import br.com.poc.desafio.adega.domain.entity.ClienteEntity;
import br.com.poc.desafio.adega.domain.entity.VinhoEntity;
import br.com.poc.desafio.adega.domain.response.mock.ClienteCompraMockResponse;
import br.com.poc.desafio.adega.domain.response.mock.ClienteCompraMockResponse.CompraMockReponse;
import br.com.poc.desafio.adega.domain.response.mock.VinhoMockResponse;
import br.com.poc.desafio.adega.repository.ClienteCompraRepository;
import br.com.poc.desafio.adega.repository.ClienteRepository;
import br.com.poc.desafio.adega.repository.VinhoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class ConsumirDadosService implements CommandLineRunner {

    @Value("${app.url.vinhos-mock}")
    private String urlVinhos;

    @Value("${app.url.clientes-compras-mock}")
    private String urlClientesCompras;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final VinhoRepository vinhoRepository;
    private final ClienteRepository clienteRepository;
    private final ClienteCompraRepository clienteCompraRepository;

    @Override
    public void run(final String... args) throws Exception {
        log.info("Iniciando carregando dos dados de mock");

        final RestTemplate restTemplate = new RestTemplate();

        carregarVinhos(restTemplate);
        carregarClientes(restTemplate);

        log.info("Finalizado o carregando dos dados de mock");
    }

    private void carregarVinhos(final RestTemplate restTemplate) throws JsonProcessingException {
        final String json = restTemplate.getForObject(urlVinhos, String.class);

        final VinhoMockResponse[] vinhosResponse = objectMapper.readValue(json, VinhoMockResponse[].class);

        for (VinhoMockResponse vinho : vinhosResponse) {
            final var vinhoEntity = VinhoEntity.builder()
                .codigo(vinho.codigo())
                .tipoVinho(vinho.tipoVinho())
                .preco(vinho.preco())
                .safra(vinho.safra())
                .anoCompra(vinho.anoCompra())
                .build();

            vinhoRepository.save(vinhoEntity);
        }
    }

    private void carregarClientes(final RestTemplate restTemplate) throws JsonProcessingException {
        final String json = restTemplate.getForObject(urlClientesCompras, String.class);

        final ClienteCompraMockResponse[] clienteComprasResponse = objectMapper.readValue(json,
            ClienteCompraMockResponse[].class);

        for (ClienteCompraMockResponse cliente : clienteComprasResponse) {
            final var clienteEntity = ClienteEntity.builder()
                .cpf(cliente.getCpf())
                .nome(cliente.getNome())
                .build();

            clienteRepository.save(clienteEntity);

            for (CompraMockReponse compra : cliente.getCompras()) {
                final var vinhoEntity = vinhoRepository.findById(compra.getCodigo())
                    .orElse(null);

                final var clienteComprasEntity = ClienteComprasEntity.builder()
                    .id(ClienteComprasEntityPk.builder()
                        .cliente(clienteEntity)
                        .vinho(vinhoEntity)
                        .build())
                    .quantidade(compra.getQuantidade())
                    .valorTotal(calcularValorTotalCompra(vinhoEntity.getPreco(), compra.getQuantidade()))
                    .build();

                clienteCompraRepository.save(clienteComprasEntity);
            }
        }
    }

    private static BigDecimal calcularValorTotalCompra(final BigDecimal preco, final Integer quantidade) {
        return preco.multiply(BigDecimal.valueOf(quantidade));
    }
}
