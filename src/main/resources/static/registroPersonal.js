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


// --- Validación del registro personal ---

const nombreRP = document.getElementById('nombre');
const emailRP = document.getElementById('registro-personal-email');
const passwordRP = document.getElementById('registro-personal-password');
const pantallaNombreError = document.getElementById('nombre-error');
const pantallaEmaiErrorRP = document.getElementById('registro-personal-email-error');
const pantallaPasswordErroRP = document.getElementById('registro-personal-password-error');
const btnRP = document.getElementById('btn-RP');

// Seleccionamos el formulario directamente (sin ID)
const formulario = document.querySelector('form');

btnRP.addEventListener('click', (e) => {
    e.preventDefault();

    let valido = true;

    // Validar nombre
    if (nombreRP.value.trim() === "") {
        pantallaNombreError.textContent = "Ingresa un nombre";
        valido = false;
    } else {
        pantallaNombreError.textContent = "";
    }

    // Validar correo
    const emailRegexRP = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegexRP.test(emailRP.value.trim())) {
        pantallaEmaiErrorRP.textContent = "Ingresa un email válido (ejemplo@correo.com)";
        valido = false;
    } else {
        pantallaEmaiErrorRP.textContent = "";
    }

    // Validar contraseña
    const passwordRPEva = passwordRP.value.trim();
    const passwordRegexRP = /^(?=.*[A-Z])(?=.*[!@#$%^&*])/; 

    if (passwordRPEva.length < 8) {
        pantallaPasswordErroRP.textContent = "La contraseña debe tener al menos 8 caracteres";
        valido = false;
    } else if (!passwordRegexRP.test(passwordRPEva)) {
        pantallaPasswordErroRP.textContent = "Debe incluir al menos 1 mayúscula y 1 caracter especial";
        valido = false;
    } else {
        pantallaPasswordErroRP.textContent = "";
    }

    // Si todo está bien, enviamos el formulario
    if (valido) {
        console.log("Formulario válido, enviando registro personal ✅");
        formulario.submit(); // 👈 Enviar al backend (Spring)
    }
});
