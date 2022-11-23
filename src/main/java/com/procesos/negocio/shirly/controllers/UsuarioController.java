package com.procesos.negocio.shirly.controllers;

import com.procesos.negocio.shirly.models.Usuario;
import com.procesos.negocio.shirly.repository.UsuarioRepository;
import com.procesos.negocio.shirly.services.UsuarioService;
import com.procesos.negocio.shirly.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    private JWTUtil jwtUtil;

    @GetMapping(value = "/usuario/{id}")
    public ResponseEntity getUsuario(@PathVariable Long id,
                                     @RequestHeader(value = "Authorization")String token) {
        if(jwtUtil.getKey(token) == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
        return usuarioService.getUserById(id);
    }


    @PostMapping("/usuario")
    public ResponseEntity crearUsuario(@Valid @RequestBody  Usuario usuario){
        return usuarioService.createUser(usuario);
    }
    @GetMapping("/usuarios")
    public ResponseEntity listarUsuario(){
        return usuarioService.allUsers();
    }
    @GetMapping("/usuario/{nombre}/{apellidos}")
    public ResponseEntity listarPorNombreApellidos(@PathVariable String nombre,@PathVariable String apellidos,
                                                   @RequestHeader(value = "Authorization")String token){
        if(jwtUtil.getKey(token) == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }

        return usuarioService.allUserByNameAndLastName(nombre, apellidos);

    } @GetMapping("/usuario/apellidos/{apellidos}")
    public ResponseEntity listarPorApellidos(@PathVariable String apellidos,
                                             @RequestHeader(value = "Authorization")String token) {
        if(jwtUtil.getKey(token) == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
    return usuarioService.allUserByLastName(apellidos);


    }
        @GetMapping("/usuario/nombre/{nombre}")
        public ResponseEntity listarPorNombres(@PathVariable String nombre,
                                               @RequestHeader(value = "Authorization")String token) {
            if(jwtUtil.getKey(token) == null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
            }
            return usuarioService.allUserByName(nombre);



}
    @PutMapping("/usuario/{id}")
    public ResponseEntity editarUsuario(@PathVariable Long id,
                                        @Valid @RequestBody  Usuario usuario,
                                        @RequestHeader(value = "Authorization")String token){
        if(jwtUtil.getKey(token) == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
        return usuarioService.editarUser(id, usuario);
    }
    @DeleteMapping("/usuario/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id,
                                          @RequestHeader(value = "Authorization")String token) {
        if(jwtUtil.getKey(token) == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
        return usuarioService.deleteUser(id);

    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }}