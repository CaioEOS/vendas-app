package com.Caio.vendas_app.Controller;

import com.Caio.vendas_app.Controller.DTO.VendaDTO;
import com.Caio.vendas_app.Service.VendaService;
import com.Caio.vendas_app.Entity.Venda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.math.BigDecimal;

@RestController
@RequestMapping(path = "/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping()
    public ResponseEntity<VendaDTO> criarVenda(@RequestBody VendaDTO vendaDTO) {
        Venda venda = vendaService.criarVenda(vendaDTO);

        return ResponseEntity.created(URI.create("/vendas" + venda.getId())).build();
    }

    @GetMapping()
    public ResponseEntity<List<VendaDTO>> listarVendas(
            @RequestParam(required = false) Integer dia,
            @RequestParam(required = false) Integer mes,
            @RequestParam(required = false) Integer ano) {

        var listaVendas =  vendaService.listarVendas(dia, mes, ano);

        return ResponseEntity.ok().body(listaVendas);
    }

    @GetMapping("/total")
    public BigDecimal calcularTotal() {
        return vendaService.calcularTotal();
    }

    @DeleteMapping("/{id}")
    public void deletarVenda(@PathVariable("id") Long id) {
        vendaService.deletarVenda(id);
    }
}