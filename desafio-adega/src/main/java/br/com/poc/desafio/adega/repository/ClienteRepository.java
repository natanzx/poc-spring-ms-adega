package br.com.poc.desafio.adega.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.poc.desafio.adega.domain.entity.ClienteEntity;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, String> {

    String QUERY_CLIENTE_FIEIS =
        "  select c.* from cliente c "
            + "inner join ( "
            + " select "
            + "     c.cpf, "
            + "     SUM(cc.quantidade) as quantidade, "
            + "     SUM(cc.valor_total) as valor_total "
            + " from cliente c "
            + "     inner join cliente_compras cc on c.cpf = cc.cliente_cpf "
            + " group by c.cpf "
            + " order by 2 desc, 3 desc "
            + " limit 3 "
            + ") a on a.cpf = c.cpf ";

    @Query(value = QUERY_CLIENTE_FIEIS, nativeQuery = true)
    List<ClienteEntity> findClientesFieis();
}
