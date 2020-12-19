var sesionCookie = getCookie("Sesion")
var tipoUsuario = getCookie("tipoUsuario")
var botonCerrarSesion = document.createElement("a");

var idFree = document.getElementById("idFree");
var idBasica = document.getElementById("idBasica");
var idMedium = document.getElementById("idMedium");
var idPro = document.getElementById("idPro");
var descripcionFree = document.getElementById("descripcionFree");
var descripcionBasica = document.getElementById("descripcionBasica");
var descripcionMedium = document.getElementById("descripcionMedium");
var descripcionPro = document.getElementById("descripcionPro");
var precioFree = document.getElementById("precioFree");
var precioBasica = document.getElementById("precioBasica");
var precioMedium = document.getElementById("precioMedium");
var precioPro = document.getElementById("precioPro");

botonCerrarSesion.class = "py-2";
botonCerrarSesion.href = "#";
botonCerrarSesion.id = "cerrarSesion";
botonCerrarSesion.style = "position:relative; top: 8px;"
if(tipoUsuario == "Cliente") {
    if(sesionCookie != "") {
        botonCerrarSesion.innerHTML = "Cerrar sesion";
        document.getElementById("seccionTop").appendChild(botonCerrarSesion);
        document.getElementById("botonLogIn").innerHTML = sesionCookie;
        document.getElementById("botonLogIn").href = "/HTML/Ventanas/Cliente.html";
        document.getElementById("botonRegistrate").innerHTML = "¡Visita tu perfil!";
        document.getElementById("botonRegistrate").href = "/HTML/Ventanas/Cliente.html";
    }
} else {
    if(sesionCookie != "") {
        botonCerrarSesion.innerHTML = "Cerrar sesion";
        document.getElementById("seccionTop").appendChild(botonCerrarSesion);
        document.getElementById("botonLogIn").innerHTML = sesionCookie;
        document.getElementById("botonLogIn").href = "/HTML/Ventanas/Entrenador.html";
        document.getElementById("botonRegistrate").innerHTML = "¡Visita tu perfil!";
        document.getElementById("botonRegistrate").href = "/HTML/Ventanas/Entrenador.html";
    }
}

// axios.get('http://localhost:4567/membresias')
axios.get('https://gimnasio-sw.herokuapp.com/membresias')
.then(function (response) {
    idFree.innerHTML = response.data.id0.id;
    descripcionFree.innerHTML = response.data.id0.descripcion;
    precioFree.innerHTML = response.data.id0.precio;
    idBasica.innerHTML = response.data.id1.id;
    descripcionBasica.innerHTML = response.data.id1.descripcion;
    precioBasica.innerHTML = response.data.id1.precio;
    idMedium.innerHTML = response.data.id2.id;
    descripcionMedium.innerHTML = response.data.id2.descripcion;
    precioMedium.innerHTML = response.data.id2.precio;
    idPro.innerHTML = response.data.id3.id;
    descripcionPro.innerHTML = response.data.id3.descripcion;
    precioPro.innerHTML = response.data.id3.precio;
})
.catch(function (error) {
    console.log(error)
})


botonCerrarSesion.addEventListener('click', function () {
    deleteCookie("Sesion");
    location.href = "/HTML/Ventanas/Index.html";
})
