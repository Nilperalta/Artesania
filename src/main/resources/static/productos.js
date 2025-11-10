// // Lista de productos con rutas corregidas para Spring Boot
// const productos = [
//   { id: 1, nombre: "Vasija Ceremonial Andina", descripcion: "...", precio: 130, imagen: "/images/coleccion-1-removebg-preview.png" },
//   { id: 2, nombre: "Jarro Cusqueño Tallado", descripcion: "...", precio: 115, imagen: "/images/coleccion-2-removebg-preview.png" },
//   { id: 3, nombre: "Par de Vasijas Decorativas", descripcion: "...", precio: 140, imagen: "/images/coleccion-3-removebg-preview.png" },
//   { id: 4, nombre: "Jarrones Étnicos del Altiplano", descripcion: "...", precio: 210, imagen: "/images/coleccion-4-removebg-preview.png" },
//   { id: 5, nombre: "Canastas de Palma Tejida", descripcion: "...", precio: 90, imagen: "/images/coleccion-5-removebg-preview.png" },
//   { id: 6, nombre: "Vasija Roja Arequipeña", descripcion: "...", precio: 125, imagen: "/images/coleccion-6-removebg-preview.png" },
//   { id: 7, nombre: "Cerámica Multicolor de Ayacucho", descripcion: "...", precio: 150, imagen: "/images/coleccion-7-removebg-preview.png" },
//   { id: 8, nombre: "Mini Vasija Decorativa", descripcion: "...", precio: 60, imagen: "/images/coleccion-8-removebg-preview.png" },
//   { id: 9, nombre: "Jarrón de Oro y Terracota", descripcion: "...", precio: 180, imagen: "/images/coleccion-9-removebg-preview.png" },
//   { id: 10, nombre: "Botella Ceremonial Nazca", descripcion: "...", precio: 145, imagen: "/images/coleccion-10-removebg-preview.png" },
//   { id: 11, nombre: "Vasija Mochica Ornamental", descripcion: "...", precio: 160, imagen: "/images/coleccion-11-removebg-preview.png" },
//   { id: 12, nombre: "Campanilla de Bronce Tallada", descripcion: "...", precio: 95, imagen: "/images/coleccion-12-removebg-preview.png" },
//   { id: 13, nombre: "Tazas de Cerámica Rustica", descripcion: "...", precio: 110, imagen: "/images/coleccion-13-removebg-preview.png" },
//   { id: 14, nombre: "Canasta Trenzada de Bambú", descripcion: "...", precio: 80, imagen: "/images/coleccion-14-removebg-preview.png" },
//   { id: 15, nombre: "Colgante de Amuleto Inca", descripcion: "...", precio: 70, imagen: "/images/coleccion-15-removebg-preview.png" },
//   { id: 16, nombre: "Jarrón Ceremonial Dorado", descripcion: "...", precio: 190, imagen: "/images/coleccion-16-removebg-preview.png" }
// ];

// // Renderizar productos en tarjetas
// const contenedor = document.getElementById("productos-container");

// productos.forEach(prod => {
//   contenedor.innerHTML += `
//     <div class="col-md-3 mb-4">
//       <div class="card h-100 shadow-sm">
//         <img src="${prod.imagen}" class="card-img-top" alt="${prod.nombre}">
//         <div class="card-body">
//           <h5 class="card-title">${prod.nombre}</h5>
//           <p class="card-text text-truncate">${prod.descripcion}</p>
//           <p class="text-success fw-bold">S/ ${prod.precio}</p>
//           <button class="btn btn-outline-primary btn-ver-mas" data-id="${prod.id}" data-bs-toggle="modal" data-bs-target="#productoModal">
//             Ver más
//           </button>
//         </div>
//       </div>
//     </div>
//   `;
// });

// // Modal dinámico y añadir al carrito (igual que antes)
// let productoSeleccionado = null;

// document.addEventListener("click", e => {
//   if (e.target.classList.contains("btn-ver-mas")) {
//     const id = e.target.getAttribute("data-id");
//     productoSeleccionado = productos.find(p => p.id == id);

//     document.getElementById("modalNombre").textContent = productoSeleccionado.nombre;
//     document.getElementById("modalDescripcion").textContent = productoSeleccionado.descripcion;
//     document.getElementById("modalPrecio").textContent = productoSeleccionado.precio;
//     document.getElementById("modalImagen").src = productoSeleccionado.imagen;
//     document.getElementById("modalCantidad").value = 1;
//   }
// });

// document.getElementById("btnAgregarCarrito").addEventListener("click", () => {
//   const cantidad = parseInt(document.getElementById("modalCantidad").value);
//   if (!productoSeleccionado) return;

//   let carrito = JSON.parse(localStorage.getItem("carrito")) || [];
//   const existe = carrito.find(p => p.id === productoSeleccionado.id);

//   if (existe) {
//     existe.cantidad += cantidad;
//   } else {
//     carrito.push({ ...productoSeleccionado, cantidad });
//   }

//   localStorage.setItem("carrito", JSON.stringify(carrito));
//   alert("Producto añadido al carrito ✅");
// });

// function irCarrito() {
//   window.location.href = "/comprador/carrito";
// }
