package br.com.poc.desafio.adega.domain.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "CLIENTE")
public class ClienteEntity {

    @Id
    @EqualsAndHashCode.Include
    @Column(unique = true)
    private String cpf;

    @Column
    private String nome;

    @OneToMany(mappedBy = "id.cliente", cascade = CascadeType.ALL)
    private List<ClienteComprasEntity> compras;
}
