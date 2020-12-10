

var bLogeo = document.getElementById('signin');
var encodedPassword = window.btoa(document.getElementById('inputPassword').value)
bLogeo.addEventListener('click', function () {
    axios.post('https://gimnasio-sw.herokuapp.com/login',  {
        Email: document.getElementById('inputEmail').value,
        Password:encodedPassword
    })
    .then(function (response) {
        console.log(response.data);
        if(response.data==document.getElementById('inputEmail').value){
            setCookie("Sesion", response.data, 0)
            location.href = "/HTML/Ventanas/Index.html";
        }
        else{
            setCookie("Sesion", "", 0)
        }
        
    })
    .catch(function (error) {
        console.log(error)
    })
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