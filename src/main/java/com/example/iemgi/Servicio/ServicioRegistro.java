package com.example.iemgi.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.iemgi.Entidad.Registro; 
import com.example.iemgi.Repositorio.RepositorioRegistro;
import com.example.iemgi.Repositorio.RepositorioUsuario;
@Service
public class ServicioRegistro {
    @Autowired
    private RepositorioRegistro RepositorioRegistro;

    public List<Registro> ObtenerRegistros() {
        return RepositorioRegistro.findAll();
    }

    public Registro insertar(Registro registro) {
        return RepositorioRegistro.save(registro);
    }
    public void delete (Long id) {
        RepositorioRegistro.deleteById(id);
    }
}
