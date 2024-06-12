package br.com.poc.desafio.adega.web.v1;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.poc.desafio.adega.service.BuscarMaiorCompraPorAnoService;
import br.com.poc.desafio.adega.service.ListarComprasService;
import lombok.SneakyThrows;

@ExtendWith(MockitoExtension.class)
class CompraControllerTest {

    public MockMvc mockMvc;
    public final ObjectMapper objectMapper = new ObjectMapper();
    public final Random random = new Random();

    @InjectMocks
    private CompraController controller;

    @Mock
    private ListarComprasService listarComprasService;

    @Mock
    private BuscarMaiorCompraPorAnoService buscarMaiorCompraPorAnoService;

    @BeforeEach
    void init() {
        mockMvc = standaloneSetup(controller).build();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Test
    @SneakyThrows
    void deveListarComprasComSucesso() {
        mockMvc.perform(get("/v1/compras")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());

        verify(listarComprasService).listar();
    }

    @Test
    @SneakyThrows
    void deveBuscarMaiorCompraPorAnoComSucesso() {
        final var ano = random.nextInt();

        mockMvc.perform(get("/v1/maior-compra/{ano}", ano)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());

        verify(buscarMaiorCompraPorAnoService).buscar(ano);
    }
}