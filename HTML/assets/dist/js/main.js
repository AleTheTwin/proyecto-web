var sesionCookie = getCookie("Sesion")
var tipoUsuario = getCookie("tipoUsuario")
var botonCerrarSesion = document.createElement("a");
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



botonCerrarSesion.addEventListener('click', function () {
    deleteCookie("Sesion");
    location.href = "/HTML/Ventanas/Index.html";
})

function deleteCookie(correo){
    valor="";
    expiracion="";
    var d = new Date();
    d.setTime(d.getTime()+expiracion*24*60*60*1000);
    var expira = "expieres="+d.toUTCString();
    document.cookie = correo+ "=" + valor +";" + expira +";path=/";
}

function setCookie(correo, valor, expiracion){
    var d = new Date();
    d.setTime(d.getTime()+expiracion*24*60*60*1000);
    var expira = "expieres="+d.toUTCString();
    document.cookie = correo+ "=" + valor +";" + expira +";path=/";
}

function getCookie(correo){
    var nom= correo +"=";
    var array = document.cookie.split(";");
    for(var i=0; i<array.length; i++){
        var c = array[i];
        while (c.charAt(0)==" "){
            c= c.substring(1);
        }
        if(c.indexOf(correo)==0){
            return c.substring(correo.length + 1, c.length);
        }
    }
    return  "";
}