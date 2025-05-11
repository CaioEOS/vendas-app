package com.Caio.vendas_app.Controller.DTO;

import com.Caio.vendas_app.Entity.Venda;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public record VendaDTO(String dataFormatada, BigDecimal valor) {

    // Metodo estático para converter uma única Venda.
    public static VendaDTO fromVenda(Venda venda) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = venda.getData().format(formatter); // Formata a data
        return new VendaDTO(dataFormatada, venda.getValor());
    }

    // Metodo estático para converter uma lista de Venda.
    public static List<VendaDTO> fromVendas(List<Venda> vendas) {
        return vendas.stream()
                .map(VendaDTO::fromVenda) // Chama o metodo fromVenda para cada Venda
                .collect(Collectors.toList());
    }
}