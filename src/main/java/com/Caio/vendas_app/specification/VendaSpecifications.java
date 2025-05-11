package com.Caio.vendas_app.specification;

import com.Caio.vendas_app.Entity.Venda;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;
import java.util.List;

public class VendaSpecifications {

    public static Specification<Venda> filtrarPorData(Integer dia, Integer mes, Integer ano) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (dia != null) {
                predicates.add(criteriaBuilder.equal(
                        criteriaBuilder.function("day", Integer.class, root.get("data")), dia));
            }
            if (mes != null) {
                predicates.add(criteriaBuilder.equal(
                        criteriaBuilder.function("month", Integer.class, root.get("data")), mes));
            }
            if (ano != null) {
                predicates.add(criteriaBuilder.equal(
                        criteriaBuilder.function("year", Integer.class, root.get("data")), ano));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
