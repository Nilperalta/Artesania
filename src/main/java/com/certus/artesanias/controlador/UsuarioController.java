package com.certus.artesanias.controlador;

import com.certus.artesanias.dto.CreateUsuarioRequest;
import com.certus.artesanias.dto.UpdateUsuarioRequest;
import com.certus.artesanias.dto.UsuarioDTO;
import com.certus.artesanias.models.Usuario;
import com.certus.artesanias.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/api/users")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // GET: Listar todos
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
        try {
            List<Usuario> usuarios = usuarioService.getAllUsuarios();

            List<UsuarioDTO> dtos = usuarios.stream()
                .map(u -> new UsuarioDTO(
                    u.getId(),
                    u.getNombre(),
                    u.getEmail(),
                    u.getRol(),
                    u.getActivo(),
                    u.getFechaRegistro() != null 
                        ? u.getFechaRegistro().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                        : "--"
                ))
                .collect(Collectors.toList());

            return ResponseEntity.ok(dtos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET: Obtener uno por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerPorIdDTO(id));
    }

    // POST: Crear nuevo
    @PostMapping
    public ResponseEntity<UsuarioDTO> crear(@RequestBody CreateUsuarioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioService.crear(request));
    }

    // PUT: Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizar(@PathVariable Long id, @RequestBody UpdateUsuarioRequest request) {
        return ResponseEntity.ok(usuarioService.actualizar(id, request));
    }

    // DELETE: Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // PATCH: Activar/Desactivar
    @PatchMapping("/{id}/toggle")
    public ResponseEntity<UsuarioDTO> toggleEstado(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.toggleEstado(id));
    }
}