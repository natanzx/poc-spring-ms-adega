package br.com.poc.desafio.adega.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.poc.desafio.adega.domain.entity.VinhoEntity;

@Repository
public interface VinhoRepository extends JpaRepository<VinhoEntity, Integer> {

    String QUERY_RECOMENDACAO_VINHO =
        "select v.* from vinho v  "
            + " inner join ( "
            + "     select * from cliente_compras cc "
            + "     where cliente_cpf = :cpf "
            + "     order by quantidade desc "
            + "     limit 1 "
            + " ) a on v.codigo = a.vinho_codigo ";

    @Query(value = QUERY_RECOMENDACAO_VINHO, nativeQuery = true)
    Optional<VinhoEntity> findRecomendacao(@Param("cpf") String cpf);
}
