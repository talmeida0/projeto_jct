package com.jctecnology.site.Service;

import com.jctecnology.site.Dao.EmpresaDao;
import com.jctecnology.site.Dto.EmpresaDTO;
import com.jctecnology.site.Model.Empresa;
import com.jctecnology.site.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class EmpresaService {

    private EmpresaDao empresaDao = new EmpresaDao();

    @Autowired
    private UsuarioService usuarioService;

    public Empresa criarEmpresa(EmpresaDTO empresaDTO) throws SQLException {
        Usuario usuario = new Usuario(empresaDTO.getEmail(), empresaDTO.getSenha());
        Empresa empresa = new Empresa(
                empresaDTO.getNome(),
                empresaDTO.getRazao(),
                empresaDTO.getCnpj(),
                empresaDTO.getStatus(),
                empresaDTO.getEmail(),
                empresaDTO.getTelefone(),
                empresaDTO.getCargo());
        Usuario usuarioCriado = usuarioService.criarUsuario(usuario);
        return empresaDao.inserirEmpresa(empresa, usuarioCriado);


    }
}
