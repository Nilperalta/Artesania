let menu = document.querySelector('#menu-btn');
let navbar = document.querySelector('.navbar');

menu.onclick=() =>{
    menu.classList.toggle('fa-times');
    navbar.classList.toggle('active');
}

window.onscroll=() =>{
    menu.classList.remove('fa-times');
    navbar.classList.remove('active');
}



//Validación del registro personal

const nombreRP = document.getElementById('nombre');
const apellidoRP = document.getElementById('apellido');
const pantallaNombreError = document.getElementById('nombre-error');
const pantallaApellidoError = document.getElementById('apellido-error');
const emailRP = document.getElementById('registro-personal-email');
const passwordRP = document.getElementById('registro-personal-password');
const pantallaEmaiErrorRP= document.getElementById('registro-personal-email-error');
const pantallaPasswordErroRP= document.getElementById('registro-personal-password-error');
const btnRP = document.getElementById('btn-RP')



btnRP.addEventListener('click', (e)=>{
    e.preventDefault();

    let valido = true

    // Validación del nombre
    if (nombreRP.value.trim() === "") {
        pantallaNombreError.textContent = "Ingresa un nombre";
        valido = false;
    }else{
        pantallaNombreError.textContent = "";
    }

    // Validación del apellido
    if (apellidoRP.value.trim() === "") {
        pantallaApellidoError.textContent = "Ingresa un apellido";
        valido = false;
    }else{
        pantallaApellidoError.textContent = "";
    }

    // Validación del correo

    const emailRegexRP = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

     if (!emailRegexRP.test(emailRP.value.trim())) {
        pantallaEmaiErrorRP.textContent = "Ingresa un email válido (ejemplo@correo.com)";
        valido = false;
    } else {
        pantallaEmaiErrorRP.textContent = "";
    }

    // Validación de contraseña segura
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


    if(valido) {
        console.log("Formulario válido, puedes continuar con el login ✅");
    }





})













