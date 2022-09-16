package com.procesos.negocio.juan.controllers;

import com.procesos.negocio.juan.models.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class UsuarioController {
    @GetMapping(value = "/usuario/{id}")
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setApellido("arias");
        usuario.setDireccion("colinas de la florida");
        usuario.setDocumento("1091680641");
        usuario.setTelefono("3183221422");
        usuario.setFechaNacimiento(new Date(11/00/11));
        return usuario;

    }
}
