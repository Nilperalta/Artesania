// Lista de productos (igual que antes)
const productos = [
  { id: 1, nombre: "Vasija Ceremonial Andina", descripcion: "Vasija de cerámica con diseños geométricos incas en tonos tierra, representando la tradición alfarera del sur andino.", precio: 130, imagen: "images/coleccion-1-removebg-preview.png" },
  { id: 2, nombre: "Jarro Cusqueño Tallado", descripcion: "Pieza artesanal de cerámica roja con grabados florales inspirados en la iconografía cusqueña tradicional.", precio: 115, imagen: "images/coleccion-2-removebg-preview.png" },
  { id: 3, nombre: "Par de Vasijas Decorativas", descripcion: "Conjunto de vasijas pequeñas con acabados pulidos y motivos lineales en color arcilla y negro.", precio: 140, imagen: "images/coleccion-3-removebg-preview.png" },
  { id: 4, nombre: "Jarrones Étnicos del Altiplano", descripcion: "Juego de tres jarrones pintados a mano con figuras tradicionales del altiplano, ideales para decoración interior.", precio: 210, imagen: "images/coleccion-4-removebg-preview.png" },
  { id: 5, nombre: "Canastas de Palma Tejida", descripcion: "Conjunto de canastas artesanales elaboradas con palma natural teñida en verde, típicas del arte amazónico peruano.", precio: 90, imagen: "images/coleccion-5-removebg-preview.png" },
  { id: 6, nombre: "Vasija Roja Arequipeña", descripcion: "Cerámica en tonos rojizos y negros con detalles finos en relieve, inspirada en la alfarería arequipeña.", precio: 125, imagen: "images/coleccion-6-removebg-preview.png" },
  { id: 7, nombre: "Cerámica Multicolor de Ayacucho", descripcion: "Pieza única con colores vibrantes que representa la alegría y diversidad del arte ayacuchano.", precio: 150, imagen: "images/coleccion-7-removebg-preview.png" },
  { id: 8, nombre: "Mini Vasija Decorativa", descripcion: "Pequeña vasija de tonos naranjas y blancos, perfecta para adornar espacios pequeños o vitrinas.", precio: 60, imagen: "images/coleccion-8-removebg-preview.png" },
  { id: 9, nombre: "Jarrón de Oro y Terracota", descripcion: "Cerámica artística con detalles dorados y grabados incisos, símbolo de la fusión entre tradición y elegancia.", precio: 180, imagen: "images/coleccion-9-removebg-preview.png" },
  { id: 10, nombre: "Botella Ceremonial Nazca", descripcion: "Pieza inspirada en los motivos de la cultura Nazca, con formas geométricas y tonos ocres.", precio: 145, imagen: "images/coleccion-10-removebg-preview.png" },
  { id: 11, nombre: "Vasija Mochica Ornamental", descripcion: "Hermosa vasija con iconografía mochica en tonos rojizos y amarillos, elaborada artesanalmente.", precio: 160, imagen: "images/coleccion-11-removebg-preview.png" },
  { id: 12, nombre: "Campanilla de Bronce Tallada", descripcion: "Campanilla metálica con figura tradicional, trabajada a mano con técnicas de fundición artesanal.", precio: 95, imagen: "images/coleccion-12-removebg-preview.png" },
  { id: 13, nombre: "Tazas de Cerámica Rustica", descripcion: "Juego de tazas hechas a mano con esmalte natural, representando la calidez de la cerámica tradicional peruana.", precio: 110, imagen: "images/coleccion-13-removebg-preview.png" },
  { id: 14, nombre: "Canasta Trenzada de Bambú", descripcion: "Canasta artesanal tejida con fibras naturales, ideal para almacenamiento o decoración rústica.", precio: 80, imagen: "images/coleccion-14-removebg-preview.png" },
  { id: 15, nombre: "Colgante de Amuleto Inca", descripcion: "Amuleto decorativo en tonos rojos y dorados, símbolo de protección y prosperidad.", precio: 70, imagen: "images/coleccion-15-removebg-preview.png" },
  { id: 16, nombre: "Jarrón Ceremonial Dorado", descripcion: "Elegante jarrón de acabado dorado con relieves inspirados en la iconografía precolombina.", precio: 190, imagen: "images/coleccion-16-removebg-preview.png" }
];

const contenedor = document.getElementById("productos-container");
const contadorCarrito = document.getElementById("contadorCarrito");
const toast = document.getElementById("toast");
let productoSeleccionado = null;

// Mostrar toast
function mostrarToast(mensaje) {
  toast.textContent = mensaje;
  toast.classList.add("show");
  setTimeout(() => toast.classList.remove("show"), 2500);
}

// Actualizar contador del carrito
function actualizarContador() {
  const carrito = JSON.parse(localStorage.getItem("carrito")) || [];
  let totalItems = carrito.reduce((sum, item) => sum + item.cantidad, 0);
  contadorCarrito.textContent = totalItems;
}

// Renderizar productos
productos.forEach(prod => {
  contenedor.innerHTML += `
    <div class="col-md-3 mb-4">
      <div class="card h-100 shadow-sm">
        <img src="${prod.imagen}" class="card-img-top" alt="${prod.nombre}">
        <div class="card-body">
          <h5 class="card-title">${prod.nombre}</h5>
          <p class="card-text text-truncate">${prod.descripcion}</p>
          <p class="text-success fw-bold">S/ ${prod.precio}</p>
          <button class="btn btn-outline-primary btn-ver-mas" data-id="${prod.id}" data-bs-toggle="modal" data-bs-target="#productoModal">
            Ver más
          </button>
        </div>
      </div>
    </div>
  `;
});

// Abrir modal
document.addEventListener("click", e => {
  if (e.target.classList.contains("btn-ver-mas")) {
    const id = e.target.getAttribute("data-id");
    productoSeleccionado = productos.find(p => p.id == id);
    document.getElementById("modalNombre").textContent = productoSeleccionado.nombre;
    document.getElementById("modalDescripcion").textContent = productoSeleccionado.descripcion;
    document.getElementById("modalPrecio").textContent = productoSeleccionado.precio;
    document.getElementById("modalImagen").src = productoSeleccionado.imagen;
    document.getElementById("modalCantidad").value = 1;
  }
});

// Agregar al carrito
document.getElementById("btnAgregarCarrito").addEventListener("click", () => {
  const cantidad = parseInt(document.getElementById("modalCantidad").value);
  if (!productoSeleccionado || cantidad < 1) return;

  let carrito = JSON.parse(localStorage.getItem("carrito")) || [];
  const existe = carrito.find(p => p.id === productoSeleccionado.id);

  if (existe) {
    existe.cantidad += cantidad;
  } else {
    carrito.push({ ...productoSeleccionado, cantidad });
  }

  localStorage.setItem("carrito", JSON.stringify(carrito));
  mostrarToast(`✅ Se agregaron ${cantidad} x ${productoSeleccionado.nombre} al carrito`);
  actualizarContador(); // 🔥 Actualiza contador después de agregar
});

// Inicializar contador al cargar página
actualizarContador();
