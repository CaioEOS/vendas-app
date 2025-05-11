package com.Caio.vendas_app.Repository;

import com.Caio.vendas_app.Entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.time.LocalDate;
import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long>, JpaSpecificationExecutor<Venda> {

    List<Venda> findByData(LocalDate data);

    List<Venda> findByDataBetween(LocalDate inicio, LocalDate fim);

    void deleteById(Long id); // Para o DELETE
}
