let menu = document.querySelector('#menu-btn');
let navbar = document.querySelector('.navbar');

menu.onclick = () => {
    menu.classList.toggle('fa-times');
    navbar.classList.toggle('active');
};

window.onscroll = () => {
    menu.classList.remove('fa-times');
    navbar.classList.remove('active');
};


// --- Validación del registro empresa ---

const nombreRE = document.getElementById('empresa-nombre');
const emailRE = document.getElementById('empresa-email');
const passwordRE = document.getElementById('empresa-password');
const pantallaNombreEmpresaError = document.getElementById('empresa-nombre-error');
const pantallaEmailEmpresaError = document.getElementById('empresa-email-error');
const pantallaPasswordErroRE = document.getElementById('empresa-password-error');
const btnRE = document.getElementById('btn-RE');

// Seleccionamos el formulario directamente (sin ID)
const formulario = document.querySelector('form');

btnRE.addEventListener('click', (e) => {
    e.preventDefault();

    let valido = true;

    // Validación del nombre
    if (nombreRE.value.trim() === "") {
        pantallaNombreEmpresaError.textContent = "Ingresa un nombre";
        valido = false;
    } else {
        pantallaNombreEmpresaError.textContent = "";
    }

    // Validación del correo
    const emailRegexRE = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegexRE.test(emailRE.value.trim())) {
        pantallaEmailEmpresaError.textContent = "Ingresa un email válido (ejemplo@correo.com)";
        valido = false;
    } else {
        pantallaEmailEmpresaError.textContent = "";
    }

    // Validación de contraseña
    const passwordREVa = passwordRE.value.trim();
    const passwordRegexRE = /^(?=.*[A-Z])(?=.*[!@#$%^&*])/; // Al menos una mayúscula y un caracter especial

    if (passwordREVa.length < 8) {
        pantallaPasswordErroRE.textContent = "La contraseña debe tener al menos 8 caracteres";
        valido = false;
    } else if (!passwordRegexRE.test(passwordREVa)) {
        pantallaPasswordErroRE.textContent = "Debe incluir al menos 1 mayúscula y 1 caracter especial";
        valido = false;
    } else {
        pantallaPasswordErroRE.textContent = "";
    }

    // Si todo es válido, enviamos el formulario
    if (valido) {
        console.log("Formulario válido, enviando registro de empresa ✅");
        formulario.submit(); // ✅ usa el <form> encontrado
    }
});
