package br.com.poc.desafio.adega.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.poc.desafio.adega.domain.entity.ClienteComprasEntity;

@Repository
public interface ClienteCompraRepository extends JpaRepository<ClienteComprasEntity, Integer> {

    List<ClienteComprasEntity> findAllByOrderByIdVinhoPrecoAsc();

    Optional<ClienteComprasEntity> findFirstByIdVinhoAnoCompraOrderByValorTotalDesc(final Integer anoCompra);

}
