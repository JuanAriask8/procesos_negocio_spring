package com.procesos.negocio.juan.repository;

import com.procesos.negocio.juan.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    List<Usuario> findAllByNombre(String nombre);
    List<Usuario> findAllByApellido(String apellido);

    List<Usuario> findAllByNombreAndApellido(String nombre, String apellido);
}
