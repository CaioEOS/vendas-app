package com.Caio.vendas_app.Service;

import com.Caio.vendas_app.Controller.DTO.VendaDTO;
import com.Caio.vendas_app.Repository.VendaRepository;
import com.Caio.vendas_app.Entity.Venda;
import com.Caio.vendas_app.specification.VendaSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.math.BigDecimal;
import java.util.stream.Collectors;

@Service
public class VendaService {
    @Autowired
    private VendaRepository vendaRepository;
    public Venda criarVenda(VendaDTO vendaDto) {
        var vendaEntity = new Venda();
        vendaEntity.setData(LocalDate.parse(vendaDto.dataFormatada()));
        vendaEntity.setValor(vendaDto.valor());
        vendaRepository.save(vendaEntity);
        return vendaEntity;
    }
    public List<VendaDTO> listarVendas(Integer dia, Integer mes, Integer ano) {
        List<Venda> vendas = vendaRepository.findAll(VendaSpecifications.filtrarPorData(dia, mes, ano));
        return vendas.stream()
                .map(VendaDTO::fromVenda)
                .collect(Collectors.toList());
    }
    public BigDecimal calcularTotal() {
        return vendaRepository.findAll()
                .stream()
                .map(Venda::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    public void deletarVenda(Long id) {
        vendaRepository.deleteById(id);
    }
}