package com.procesos.negocio.juan.controllers;

import com.procesos.negocio.juan.models.Usuario;
import com.procesos.negocio.juan.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @GetMapping(value = "/usuario/{id}")
    public Optional<Usuario> getUsuario(@PathVariable Long id){
       /* Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setApellido("arias");
        usuario.setDireccion("colinas de la florida");
        usuario.setDocumento("1091680641");
        usuario.setTelefono("3183221422");
        usuario.setFechaNacimiento(new Date(2002,03,25));*/
        Optional<Usuario> usuario= usuarioRepository.findById(id);
        return usuario;

    }
    @PostMapping("/usuario")
    public Usuario crearUsuario(@RequestBody Usuario usuario){
        usuarioRepository.save(usuario);
        return usuario;

    }
    @GetMapping("/usuario")
    public List<Usuario>listarUsuarios(){
        return usuarioRepository.findAll();
    }
    @GetMapping("/usuario/{nombre}/{apellido}")
    public List<Usuario>listarPorNombreAndApellido(@PathVariable String nombre, @PathVariable String apellido){
        return usuarioRepository.findAllByNombreAndApellido(nombre,apellido);
    }
    @GetMapping("/usuario/apellido/{apellido}")
    public List<Usuario>listarPorApellido( @PathVariable String apellido){
        return usuarioRepository.findAllByApellido(apellido);
    }
    @GetMapping("/usuario/nombre/{nombre}")
    public List<Usuario>listarPorNombre( @PathVariable String nombre){
        return usuarioRepository.findAllByNombre(nombre);
    }

    @PutMapping("/usuario/{id}")
    public Usuario editarUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        Usuario usuarioBD= usuarioRepository.findById(id).get();
        try{
            usuarioBD.setNombre(usuario.getNombre());
            usuarioBD.setApellido(usuario.getApellido());
            usuarioBD.setFechaNacimiento(usuarioBD.getFechaNacimiento());
            usuarioBD.setDireccion(usuario.getDireccion());
            usuarioBD.setDocumento(usuario.getDocumento());
            usuarioBD.setTelefono(usuario.getTelefono());
            usuarioRepository.save(usuarioBD);
            return usuarioBD;

        }catch (Exception e){
            return null;
        }
    }
    @DeleteMapping("/usuario/{id}")
    public Usuario eliminarUsuario(@PathVariable Long id){
        Usuario usuarioBD = usuarioRepository.findById(id).get();
        try{
            usuarioRepository.delete(usuarioBD);
            return usuarioBD;
            }catch (Exception e){
            return null;
        }
    }

}
