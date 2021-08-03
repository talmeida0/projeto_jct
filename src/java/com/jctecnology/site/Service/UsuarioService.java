package com.jctecnology.site.Service;

import com.jctecnology.site.Dao.UsuarioDao;
import com.jctecnology.site.Model.Usuario;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UsuarioService {

    private UsuarioDao usuarioDao = new UsuarioDao();

    public Usuario criarUsuario(Usuario usuario) throws SQLException {
        usuarioDao.inserirUsuario(usuario);
        return usuarioDao.procurarUsuario(usuario.getLogin());
    }

    public void login(Usuario usuario) throws Exception {
        Usuario usuarioCadastrado = usuarioDao.procurarUsuario(usuario.getLogin());
        if (usuarioCadastrado == null) {
            throw new Exception("Usuário não encontrado");
        }
        if (!usuarioCadastrado.getSenha().equals(usuario.getSenha())) {
            throw new Exception("Senha inválida");
        }
    }
}
