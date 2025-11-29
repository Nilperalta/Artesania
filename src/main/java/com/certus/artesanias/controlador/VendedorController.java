package com.certus.artesanias.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.certus.artesanias.models.Usuario;
import com.certus.artesanias.models.Vendedor;
import com.certus.artesanias.repository.ProductoRepository;
import com.certus.artesanias.repository.UsuarioRepository;
import com.certus.artesanias.repository.VendedorRepository;

@Controller
public class VendedorController {

     private final VendedorRepository vendedorRepository;

    public VendedorController(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    @GetMapping("/vendedor")
    public String dashboard() {
        return "dashboard-vendedor";
    }
    
    @GetMapping("/vendedor/ventas")
    public String misVentas() {
        return "vendedor-misventas";
    }
    
@Autowired
    private ProductoRepository productoRepository;
    @GetMapping("/vendedor/productos")
    public String misProductos(Model model) {

    // por ahora sin login, toma todos los productos
    model.addAttribute("productos", productoRepository.findAll());

    return "vendedor-misproductos";
}
  
@Autowired
private UsuarioRepository usuarioRepository;

@GetMapping("/vendedor/perfil")
public String miPerfil(@AuthenticationPrincipal UserDetails userDetails, Model model) {
    Usuario usuario = usuarioRepository.findByEmail(userDetails.getUsername());
    Vendedor vendedor = vendedorRepository.findByUsuarioId(usuario.getId())
                                         .orElse(null);
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

    @PostMapping("/vendedor/perfil/guardar")
public String guardarPerfil(@ModelAttribute Vendedor vendedor, 
                            @AuthenticationPrincipal UserDetails userDetails) {

    Usuario usuario = usuarioRepository.findByEmail(userDetails.getUsername());

    // Asegurar que se mantiene el vínculo
    vendedor.setUsuario(usuario);

    vendedorRepository.save(vendedor);

    return "redirect:/vendedor/perfil";
}

}