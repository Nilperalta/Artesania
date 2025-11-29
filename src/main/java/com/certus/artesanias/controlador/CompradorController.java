package com.certus.artesanias.controlador;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.certus.artesanias.models.Carrito;
import com.certus.artesanias.models.Producto;
import com.certus.artesanias.models.Usuario;
import com.certus.artesanias.service.CarritoService;
import com.certus.artesanias.service.ProductoCliente;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/comprador")
public class CompradorController {

    private final ProductoCliente productoCliente;
    private final CarritoService carritoService;

    public CompradorController(ProductoCliente productoCliente, CarritoService carritoService) {
        this.productoCliente = productoCliente;
        this.carritoService = carritoService;
    }

    // ====================== DASHBOARD ======================
    @GetMapping
    public String homeComprador(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogeado");
        if (usuario == null) return "redirect:/login";

        model.addAttribute("usuario", usuario);
        return "comprador/dashboard-comprador";
    }

    // ====================== LISTAR PRODUCTOS ======================
    @GetMapping("/productos")
    public String listarProductos(Model model,
                                  @RequestParam(value = "agregado", required = false) String agregado,
                                  HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogeado");
        if (usuario == null) return "redirect:/login";

        List<Producto> productos = productoCliente.obtenerProductos();
        model.addAttribute("productos", productos);
        model.addAttribute("usuario", usuario);

        if (agregado != null) {
            model.addAttribute("mensaje", "Producto agregado al carrito correctamente ✅");
        }

        return "comprador/productos";
    }

    // ====================== DETALLE PRODUCTO ======================
    @GetMapping("/producto/{id}")
    public String verDetalleProducto(@PathVariable Long id, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogeado");
        if (usuario == null) return "redirect:/login";

        Producto producto = productoCliente.obtenerProductoPorId(id);
        if (producto == null) return "redirect:/comprador/productos";

        model.addAttribute("producto", producto);
        model.addAttribute("usuario", usuario);

        return "comprador/detalle-producto";
    }

    // ====================== VER CARRITO ======================
    @GetMapping("/carrito")
    public String verCarrito(HttpSession session, Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogeado");
        if (usuario == null) return "redirect:/login";

        List<Carrito> carrito = carritoService.obtenerPorUsuario(usuario.getId());
        carrito.forEach(Carrito::calcularSubtotal);

        BigDecimal total = carrito.stream()
                .map(Carrito::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("carritoItems", carrito);
        model.addAttribute("total", total);
        model.addAttribute("usuario", usuario);

        return "comprador/carrito";
    }

    // ====================== AGREGAR AL CARRITO ======================
    @PostMapping("/carrito/agregar")
    public String agregarAlCarrito(@RequestParam("productoId") Long productoId,
                                   @RequestParam("cantidad") int cantidad,
                                   HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogeado");
        if (usuario == null) return "redirect:/login";

        carritoService.agregarProducto(usuario.getId(), productoId, cantidad);

        return "redirect:/comprador/productos?agregado=true";
    }

    // ====================== ELIMINAR ITEM ======================
    @GetMapping("/carrito/eliminar/{id}")
    public String eliminarItem(@PathVariable Long id, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogeado");
        if (usuario == null) return "redirect:/login";

        carritoService.eliminar(id);

        return "redirect:/comprador/carrito";
    }

    // ====================== CHECKOUT ======================
    @GetMapping("/checkout")
    public String mostrarCheckout(HttpSession session, Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogeado");
        if (usuario == null) return "redirect:/login";

        List<Carrito> carrito = carritoService.obtenerPorUsuario(usuario.getId());
        carrito.forEach(Carrito::calcularSubtotal);

        BigDecimal total = carrito.stream()
                .map(Carrito::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("usuario", usuario);
        model.addAttribute("carritoItems", carrito);
        model.addAttribute("total", total);

        return "comprador/checkout";
    }

    // ====================== PROCESAR CHECKOUT ======================
    @PostMapping("/checkout")
    public String procesarCheckout(@RequestParam String direccion,
                                   @RequestParam String metodoPago,
                                   HttpSession session,
                                   Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogeado");
        if (usuario == null) return "redirect:/login";

        List<Carrito> carrito = carritoService.obtenerPorUsuario(usuario.getId());

        if (carrito.isEmpty()) {
            model.addAttribute("mensajeError", "Tu carrito está vacío.");
            return "comprador/checkout";
        }

        BigDecimal total = carrito.stream()
                .map(item -> item.getProducto().getPrecio().multiply(BigDecimal.valueOf(item.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("mensajeExito", "Compra realizada con éxito 🎉");
        model.addAttribute("total", total);
        model.addAttribute("carritoItems", carrito);

        carritoService.vaciar(usuario.getId());

        return "comprador/checkout";
    }

    // ====================== PERFIL ======================
    @GetMapping("/perfil")
    public String perfil(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogeado");
        if (usuario == null) return "redirect:/login";

        model.addAttribute("usuario", usuario);

        return "comprador/perfil";
    }

    // ====================== CERRAR SESIÓN ======================
    @GetMapping("/salir")
    public String salir(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
