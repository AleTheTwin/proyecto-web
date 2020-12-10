var bRegistro= document.getElementById('botonRegistro');
bRegistro.addEventListener('click', function () {
    var pass = document.getElementById('contraseñaREG').value;
    var encodedPassword = window.btoa(pass);
    var email = document.getElementById('correoREG').value;
    var passConfirm = document.getElementById('concontraseñaREG').value;
    if(pass==passConfirm) {
        console.log("La contraseña coincide")
        axios.post('https://gimnasio-sw.herokuapp.com/registro',  {
            Email: document.getElementById('correoREG').value,
            Password: encodedPassword,
            Edad: document.getElementById('edadREG').value,
            Sexo: document.getElementById('sexBox').value,
            NombreC: document.getElementById('usuarioREG').value,
            TipoCliente: document.getElementById('userBox').value
        })
        .then(function (response) {
            console.log(response.data);
            if(response.data==email){
                setCookie("Sesion", response.data, 0)
                location.href = "/HTML/Ventanas/Index.html";
            } else {
                setCookie("Sesion", "", 0)
            }
            
        })
        .catch(function (error) {
            console.log(error)
        })
    } else {
        console.log("La contraseña no coincide")
    }
})

function delCookie(nombre){
    valor="";
    expiracion="";
    var d = new Date();
    d.setTime(d.getTime()+expiracion*24*60*60*1000);
    var expira = "expieres="+d.toUTCString();
    document.cookie = nombre+ "=" + valor +";" + expira +";path=/";
}      
function setCookie(nombre, valor, expiracion){
    var d = new Date();
    d.setTime(d.getTime()+expiracion*24*60*60*1000);
    var expira = "expieres="+d.toUTCString();
    document.cookie = nombre+ "=" + valor +";" + expira +";path=/";
}
function getCookie(nombre){
    var nom= nombre +"=";
    var array = document.cookie.split(";");
    for(var i=0; i<array.length; i++){
        var c = array[i];
        while (c.charAt(0)==" "){
            c= c.substring(1);
        }
        if(c.indexOf(nombre)==0){
            return c.substring(nombre.length + 1, c.length);
        }
    }
    return  "";
}