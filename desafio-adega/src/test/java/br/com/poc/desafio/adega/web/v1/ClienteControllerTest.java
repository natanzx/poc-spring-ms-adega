package br.com.poc.desafio.adega.web.v1;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.apache.commons.lang3.RandomStringUtils;
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

import br.com.poc.desafio.adega.service.BuscarRecomendacaoVinhoClienteService;
import br.com.poc.desafio.adega.service.ListarClientesFieisService;
import lombok.SneakyThrows;

@ExtendWith(MockitoExtension.class)
class ClienteControllerTest {

    public MockMvc mockMvc;
    public final ObjectMapper objectMapper = new ObjectMapper();

    @InjectMocks
    private ClienteController controller;

    @Mock
    private ListarClientesFieisService listarClientesFieisService;

    @Mock
    private BuscarRecomendacaoVinhoClienteService buscarRecomendacaoVinhoClienteService;

    @BeforeEach
    void init() {
        mockMvc = standaloneSetup(controller).build();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Test
    @SneakyThrows
    void deveListarClientesFieisComSucesso() {
        mockMvc.perform(get("/v1/clientes-fieis")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());

        verify(listarClientesFieisService).listar();
    }

    @Test
    @SneakyThrows
    void deveBuscarRecomendacaoComSucesso() {
        final var cpf = RandomStringUtils.randomNumeric(11);

        mockMvc.perform(get("/v1/recomendacao/{cpf}/tipo", cpf)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());

        verify(buscarRecomendacaoVinhoClienteService).buscar(cpf);
    }
}