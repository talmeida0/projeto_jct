package com.jctecnology.site.Controller;

import com.jctecnology.site.Dto.EmpresaDTO;
import com.jctecnology.site.Model.Orcamento;
import com.jctecnology.site.Service.EmpresaService;
import com.jctecnology.site.Service.OrcamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RequestMapping("/orcamento")
@RestController
public class OrcamentoController {
    @Autowired
    private OrcamentoService orcamentoService;

    @RequestMapping
    public ResponseEntity<Void> criarEmpresa(@RequestBody EmpresaDTO empresaDto) {
        try {
            orcamentoService.gerarOrcamento(empresaDto);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (SQLException throwables) {
            System.out.println(throwables.getErrorCode());
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
