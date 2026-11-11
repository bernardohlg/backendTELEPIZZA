package com.bcopstein.ex4_lancheriaddd_v1.Adaptadores.Apresentacao;

import com.bcopstein.ex4_lancheriaddd_v1.Dominio.Entidades.PoliticaDesconto;
import com.bcopstein.ex4_lancheriaddd_v1.Dominio.Servicos.DescontosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/descontos")
public class DescontosController {

    @Autowired
    private DescontosService descontosService;

    @GetMapping("/lista")
    @CrossOrigin("*")
    public List<PoliticaDesconto> listarPoliticas() {
        return descontosService.listarPoliticas();
    }

    @PutMapping("/corrente/{codigo}")
    @CrossOrigin("*")
    public ResponseEntity<Object> definirPoliticaCorrente(@PathVariable(value = "codigo") String codigo) {
        boolean sucesso = descontosService.definirPoliticaCorrente(codigo);
        if (!sucesso) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Map.of("mensagem", "Política de desconto definida como corrente com sucesso."));
    }
}
