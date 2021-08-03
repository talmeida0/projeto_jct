package com.jctecnology.site.Controller;

import com.jctecnology.site.Model.Usuario;
import com.jctecnology.site.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/usuario")
@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        try {
            usuarioService.login(usuario);
            return new ResponseEntity<>("Connected", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
