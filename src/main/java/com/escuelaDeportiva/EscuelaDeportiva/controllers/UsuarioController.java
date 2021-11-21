package com.escuelaDeportiva.EscuelaDeportiva.controllers;

import java.util.HashMap;
import java.util.Map;

import com.escuelaDeportiva.EscuelaDeportiva.models.UsuarioModel;
import com.escuelaDeportiva.EscuelaDeportiva.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

     // Definimos el método para agregar un usuario
     @PostMapping("/usuarios")
     public ResponseEntity<Map<String, String>>guardar(@Validated @RequestBody UsuarioModel usuario){
         Map<String, String> respuesta = new HashMap<>();
 
         UsuarioModel u = this.usuarioService.buscarUsername(usuario.getUsername());
         if (u.getId() == null) {
             this.usuarioService.guardarUsuario(usuario);
             respuesta.put("mensaje", "Se agregó correctamente el usuario");
 
         } else {
             respuesta.put("mensaje", "El usuario ya esta registrado");
 
         }
 
         return ResponseEntity.ok(respuesta);
     }

}