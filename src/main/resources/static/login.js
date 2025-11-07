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


/* --- Validación del login --- */

const emailLogin = document.getElementById('email');
const passLogin = document.getElementById('password');
const btnLogin = document.getElementById('btn-Login');
const pantallaEmailError = document.getElementById('email-error');
const pantallaPassError = document.getElementById('password-error');

btnLogin.addEventListener('click', (e) => {
    e.preventDefault(); // detener el envío automático mientras validamos

    let valido = true;

    // Validación del email
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(emailLogin.value.trim())) {
        pantallaEmailError.textContent = "Ingresa un email válido (ejemplo@correo.com)";
        valido = false;
    } else {
        pantallaEmailError.textContent = "";
    }

    // Validación de contraseña
    const password = passLogin.value.trim();
    const passwordRegex = /^(?=.*[A-Z])(?=.*[!@#$%^&*])/;

    if (password.length < 8) {
        pantallaPassError.textContent = "La contraseña debe tener al menos 8 caracteres";
        valido = false;
    } else if (!passwordRegex.test(password)) {
        pantallaPassError.textContent = "Debe incluir al menos 1 mayúscula y 1 caracter especial";
        valido = false;
    } else {
        pantallaPassError.textContent = "";
    }

    // Si es válido, enviar formulario al backend
    if (valido) {
        console.log("Formulario válido, enviando al backend ✅");
        document.querySelector("form").submit(); // 👈 envía el formulario completo
    }
});
