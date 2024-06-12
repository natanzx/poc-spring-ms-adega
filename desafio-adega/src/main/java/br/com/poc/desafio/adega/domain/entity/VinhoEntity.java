package br.com.poc.desafio.adega.domain.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "VINHO")
public class VinhoEntity {

    @Id
    @EqualsAndHashCode.Include
    @Column(unique = true)
    private Integer codigo;

    @Column
    private String tipoVinho;

    @Column
    private BigDecimal preco;

    @Column
    private Integer safra;

    @Column
    private Integer anoCompra;

}
