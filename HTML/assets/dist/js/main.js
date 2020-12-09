var sesionCookie = getCookie("Sesion")
var botonCerrarSesion = document.createElement("a");
botonCerrarSesion.class = "py-2";
botonCerrarSesion.href = "#";
botonCerrarSesion.id = "cerrarSesion";
botonCerrarSesion.style = "position:relative; top: 8px;"
if(sesionCookie != "") {
    //aquí desactivas el botón login y registro en cambio pone botón de usuario
    botonCerrarSesion.innerHTML = "Cerrar sesion";
    document.getElementById("seccionTop").appendChild(botonCerrarSesion);
    document.getElementById("botonLogIn").innerHTML = sesionCookie;
    document.getElementById("botonLogIn").href = "#";
    document.getElementById("botonRegistrate").innerHTML = "¡Visita tu perfil!";
    document.getElementById("botonRegistrate").href = "#";
}

axios.get("http://localhost:4567/hello")
.then(function (response) {
    var prueba = document.createElement("div");
    prueba.appendChild(response.data)
    document.getElementById("prueba").appendChild(prueba)
    console.log(response)
})
.catch(function (error) {
    console.log(error)
})


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