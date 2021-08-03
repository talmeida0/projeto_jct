package com.jctecnology.site.Controller;

import com.jctecnology.site.Dto.EmpresaDTO;
import com.jctecnology.site.Service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RequestMapping("/empresa")
@RestController
public class EmpresaController {
    @Autowired
    private EmpresaService empresaService;

    @RequestMapping
    public ResponseEntity<Void> criarEmpresa(@RequestBody EmpresaDTO empresa) {
        try {
            empresaService.criarEmpresa(empresa);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (SQLException throwables) {
            System.out.println(throwables.getErrorCode());
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
