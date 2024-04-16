package com.example.iemgi.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.iemgi.Entidad.Usuario; 
import com.example.iemgi.Repositorio.RepositorioRegistro;
import com.example.iemgi.Repositorio.RepositorioUsuario;

@Service
public class ServicioUsuario {
    @Autowired
    private RepositorioUsuario RepositorioUsuario;

    public Usuario insertar(Usuario usuario) {
        return RepositorioUsuario.save(usuario);
    }
    public boolean autenticarUsuario(String matricula, String contrasena) {
        Usuario usuario = RepositorioUsuario.findByMatricula(matricula);
        return usuario != null && usuario.getContrasena().equals(contrasena);
    }
    public Usuario obtenerPorMatricula(String matricula) {
       
        return RepositorioUsuario.findByMatricula(matricula);
    }
    public void actualizar(Usuario usuario) {
        RepositorioUsuario.save(usuario);
    }
}
