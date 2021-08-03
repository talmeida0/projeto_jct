package com.jctecnology.site.Service;

import com.jctecnology.site.Dao.EmpresaDao;
import com.jctecnology.site.Dao.OrcamentoDao;
import com.jctecnology.site.Dto.EmpresaDTO;
import com.jctecnology.site.Model.Empresa;
import com.jctecnology.site.Model.Orcamento;
import com.jctecnology.site.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class OrcamentoService {

    private OrcamentoDao orcamentoDao = new OrcamentoDao();

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmpresaService empresaService;


    public void gerarOrcamento(EmpresaDTO empresaDTO) throws SQLException {
        Empresa empresaCriada = empresaService.criarEmpresa(empresaDTO);
        Orcamento orcamento = new Orcamento(empresaDTO.getPlano(), empresaDTO.getObservacoes(), Integer.valueOf(empresaDTO.getNumFuncionarios()), empresaCriada);
        orcamentoDao.inserirOrcamento(orcamento);
    }
}
