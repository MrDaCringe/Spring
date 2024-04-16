package com.example.iemgi.Repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.iemgi.Entidad.Usuario; 
public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {
    Usuario findByMatricula(String matricula);
}


