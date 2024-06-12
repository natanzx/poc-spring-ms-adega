package br.com.poc.desafio.adega.stub;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

import java.math.BigDecimal;
import java.util.Random;

import br.com.poc.desafio.adega.domain.entity.ClienteComprasEntity;
import br.com.poc.desafio.adega.domain.entity.ClienteComprasEntityPk;
import br.com.poc.desafio.adega.domain.entity.ClienteEntity;
import br.com.poc.desafio.adega.domain.entity.VinhoEntity;

public class RandomStub {

    private static final Random random = new Random();

    public static ClienteComprasEntity generateClienteCompraEntity() {
        final var clienteEntity = generateCliente();
        final var vinhoEntity = generateVinho();

        return ClienteComprasEntity.builder()
            .id(ClienteComprasEntityPk.builder()
                .cliente(clienteEntity)
                .vinho(vinhoEntity)
                .build())
            .build();
    }

    public static ClienteEntity generateCliente() {
        return ClienteEntity.builder()
            .nome(randomAlphabetic(10))
            .cpf(randomNumeric(11))
            .build();
    }

    public static VinhoEntity generateVinho() {
        return VinhoEntity.builder()
            .anoCompra(random.nextInt())
            .safra(random.nextInt())
            .preco(BigDecimal.ONE)
            .tipoVinho(randomAlphabetic(5))
            .build();
    }

}
