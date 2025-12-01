package com.certus.artesanias.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.certus.artesanias.models.Usuario;
import com.certus.artesanias.models.Vendedor;
import com.certus.artesanias.models.Producto;
import com.certus.artesanias.repository.ProductoRepository;
import com.certus.artesanias.repository.UsuarioRepository;
import com.certus.artesanias.repository.VendedorRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/vendedor")
public class VendedorController {

     private final VendedorRepository vendedorRepository;

    public VendedorController(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    @GetMapping("")
    public String dashboard() {
        return "dashboard-vendedor";
    }
    
    @GetMapping("/ventas")
    public String misVentas() {
        return "vendedor-misventas";
    }
    
@Autowired
    private ProductoRepository productoRepository;
    @GetMapping("/productos")
    public String misProductos(Model model) {

    // por ahora sin login, toma todos los productos
    model.addAttribute("productos", productoRepository.findAll());

    return "vendedor-misproductos";
}

    // Guardar (crear o actualizar) producto
    @PostMapping("/productos/guardar")
    public String guardarProducto(@ModelAttribute Producto producto) {
        // si no hay imagen se puede asignar una por defecto
        if (producto.getImagen() == null || producto.getImagen().trim().isEmpty()) {
            producto.setImagen("images/producto-1.png");
        }
        productoRepository.save(producto);
        return "redirect:/vendedor/productos?success";
    }

    // Eliminar producto
    @PostMapping("/productos/{id}/eliminar")
    public String eliminarProducto(@PathVariable Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return "redirect:/vendedor/productos?deleted";
        }
        return "redirect:/vendedor/productos?error";
    }
  
@Autowired
private UsuarioRepository usuarioRepository;

@GetMapping("/perfil")
public String miPerfil(@AuthenticationPrincipal UserDetails userDetails, Model model) {
    // si no hay autenticación, redirigir al login (evita NPE y 500)
    if (userDetails == null) {
        return "redirect:/login";
    }

    

    Usuario usuario = usuarioRepository.findByEmail(userDetails.getUsername());
    if (usuario == null) {
        return "redirect:/login";
    }

    // Buscar vendedor por el id del usuario. Si no existe, devolver un objeto vacío para la vista
    Vendedor vendedor = vendedorRepository.findByUsuario_Id(usuario.getId()).orElseGet(() -> {
        Vendedor v = new Vendedor();
        v.setUsuario(usuario);
        v.setNombre(usuario.getNombre());
        v.setCorreo(usuario.getEmail());
        v.setTelefono("");
        v.setDireccion("");
        v.setDescripcion("");
        v.setFoto("/images/Logo-artesania.png");
        return v;
    });

    model.addAttribute("vendedor", vendedor);
    return "vendedor-miperfil";
}

    @PostMapping("/actualizar")
    public String actualizarPerfil(@ModelAttribute("vendedor") Vendedor vendedorActualizado) {

        Vendedor vendedorBD = vendedorRepository.findById(1L).orElse(null);

        if (vendedorBD != null) {
            vendedorBD.setNombre(vendedorActualizado.getNombre());
            vendedorBD.setCorreo(vendedorActualizado.getCorreo());
            vendedorBD.setTelefono(vendedorActualizado.getTelefono());
            vendedorBD.setDireccion(vendedorActualizado.getDireccion());
            vendedorBD.setDescripcion(vendedorActualizado.getDescripcion());
            vendedorRepository.save(vendedorBD);
        }

        return "redirect:/vendedor/perfil?success";
    }

    @PostMapping("/perfil/guardar")
    public String guardarPerfil(@ModelAttribute Vendedor vendedor, 
                                @AuthenticationPrincipal UserDetails userDetails) {

        if (userDetails == null) {
            return "redirect:/login";
        }

        Usuario usuario = usuarioRepository.findByEmail(userDetails.getUsername());
        if (usuario == null) {
            return "redirect:/login";
        }

        // Actualizar si existe, o crear uno nuevo
        vendedorRepository.findByUsuario_Id(usuario.getId()).ifPresentOrElse(existing -> {
            existing.setNombre(vendedor.getNombre());
            existing.setCorreo(vendedor.getCorreo());
            existing.setTelefono(vendedor.getTelefono());
            existing.setDireccion(vendedor.getDireccion());
            existing.setDescripcion(vendedor.getDescripcion());
            existing.setFoto(vendedor.getFoto());
            vendedorRepository.save(existing);
        }, () -> {
            vendedor.setUsuario(usuario);
            vendedorRepository.save(vendedor);
        });

        return "redirect:/vendedor/perfil?success";
    }


    @GetMapping("/salir")
        public String salir(HttpSession session) {
         session.invalidate(); // Cierra la sesión
            return "redirect:/login"; // Te lleva a la página de login
    }

}