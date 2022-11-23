package com.procesos.negocio.shirly.services;

import com.procesos.negocio.shirly.models.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsuarioService {
    ResponseEntity<Usuario> getUserById(Long id);
    ResponseEntity<Usuario> createUser(Usuario usuario);
    ResponseEntity<List<Usuario>> allUsers();
    ResponseEntity<List<Usuario>> allUserByNameAndLastName(String nombre, String apellidos);
    ResponseEntity<List<Usuario>> allUserByName(String nombre);
    ResponseEntity<List<Usuario>> allUserByLastName(String apellido);
    ResponseEntity<Usuario> editarUser(Long id, Usuario usuario);
    ResponseEntity<Usuario> deleteUser(Long id);
    ResponseEntity login(String correo , String password);
}
