package br.com.poc.desafio.adega.domain.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ClienteComprasEntityPk {

    @ManyToOne
    @JoinColumn(name = "cliente_cpf", referencedColumnName = "cpf")
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "vinho_codigo", referencedColumnName = "codigo")
    private VinhoEntity vinho;

}
