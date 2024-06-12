package br.com.poc.desafio.adega.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.poc.desafio.adega.repository.VinhoRepository;
import br.com.poc.desafio.adega.stub.RandomStub;

@ExtendWith(MockitoExtension.class)
class BuscarRecomendacaoVinhoClienteServiceTest {

    @InjectMocks
    private BuscarRecomendacaoVinhoClienteService service;

    @Mock
    private VinhoRepository vinhoRepository;

    @Test
    void deveBuscarRecomendacaoComSucesso() {
        final var cpf = RandomStringUtils.randomNumeric(11);
        final var vinhoEntity = RandomStub.generateVinho();

        when(vinhoRepository.findRecomendacao(any())).thenReturn(Optional.of(vinhoEntity));

        final var atual = service.buscar(cpf);

        verify(vinhoRepository).findRecomendacao(cpf);

        assertEquals(vinhoEntity.getAnoCompra(), atual.anoCompra());
        assertEquals(vinhoEntity.getTipoVinho(), atual.tipoVinho());
        assertEquals(vinhoEntity.getSafra(), atual.safra());
        assertEquals(vinhoEntity.getPreco(), atual.preco());
    }
}