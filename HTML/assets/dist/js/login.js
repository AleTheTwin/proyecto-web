var bLogeo = document.getElementById('signin');
var alerta = document.getElementById('alerta');
var checkbox;
var encodedPassword;
alerta.style.display = 'none';

bLogeo.addEventListener('click', function () {
    encodedPassword = window.btoa(document.getElementById('inputPassword').value)
    checkbox = document.getElementById('flexCheckDefault');
    //var passEncode = window.btoa('pass');
    // axios.post('http://localhost:4567/registroEntrenador',  {
    //     Email: 'entrenador@gmail.com',
    //     Password: passEncode,
    //     NombreE: 'Manu',
    //     TipoEntrenador: 'Noob'
    // })
    // .then(function (response) {
    //     console.log(response.data);
    //     if(response.data==email){
    //         setCookie("Sesion", response.data, 0)
    //         location.href = "/HTML/Ventanas/Index.html";
    //     } else {
    //         alertaContenido.innerHTML = "El correo ya existe"
    //         alerta.style.display = 'inline';
    //         setCookie("Sesion", "", 0)
    //     }
    //     
    // })
    // .catch(function (error) {
    //     console.log(error)
    // })
    if(checkbox.checked) {
        axios.post('http://localhost:4567/loginEntrenador',  {
            // axios.post('https://gimnasio-sw.herokuapp.com/loginEntrenador',  {
            Email: document.getElementById('inputEmail').value,
            Password: encodedPassword
        })
        .then(function (response) {
            console.log(response.data);
            if(response.data==document.getElementById('inputEmail').value){
                setCookie("Sesion", response.data, 0)
                setCookie("tipoUsuario", "Entrenador")
                location.href = "/HTML/Ventanas/Index.html";
            }
            else{
                alerta.style.display = 'inline';
                document.getElementById('inputPassword').value = "";
                setCookie("Sesion", "", 0)
            }
            
        })
        .catch(function (error) {
            console.log(error)
        })
    } else {
        axios.post('http://localhost:4567/login',  {
        // axios.post('https://gimnasio-sw.herokuapp.com/login',  {
            Email: document.getElementById('inputEmail').value,
            Password: encodedPassword
        })
        .then(function (response) {
            console.log(response.data);
            if(response.data==document.getElementById('inputEmail').value){
                setCookie("Sesion", response.data, 0)
                setCookie("tipoUsuario", "Cliente")
                location.href = "/HTML/Ventanas/Index.html";
            }
            else{
                alerta.style.display = 'inline';
                document.getElementById('inputPassword').value = "";
                setCookie("Sesion", "", 0)
            }
            
        })
        .catch(function (error) {
            console.log(error)
        })
    }
    alerta.style.display = 'none';
    
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