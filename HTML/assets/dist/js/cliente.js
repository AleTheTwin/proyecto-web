//
var sesion = getCookie("Sesion")
var nombreCliente = document.getElementById("nombreCliente")
var nombreCliente2 = document.getElementById("nombreCliente2")

axios.post('https://gimnasio-sw.herokuapp.com/getNombreSesion',  {
      Email: sesion
})
.then(function (response) {
    nombreCliente.innerHTML = "Hola  " + response.data;
    nombreCliente2.innerHTML = response.data;
})
.catch(function (error) {
    console.log(error)
})

// Sidebar Toggle Codes;

var sidebarOpen = false;
var sidebar = document.getElementById("sidebar");
var sidebarCloseIcon = document.getElementById("sidebarIcon");

function toggleSidebar() {
    
      if (!sidebarOpen) {
    sidebar.classList.add("sidebar_responsive");
    sidebarOpen = true;
  }  
    
  
}

function desplegarMembresia(){
  var precio = document.getElementById("precioMembresia");
  var descripcion = document.getElementById("descripcionMembresia");
  var id = document.getElementById("idMembresia");
  axios.post('https://gimnasio-sw.herokuapp.com/membresiaByEmail',  {
      Email: sesion
  })
  .then(function (response) {
    precio.innerHTML = response.data.precio;
    descripcion.innerHTML = response.data.descripcion;
    id.innerHTML = response.data.id;
  })
  .catch(function (error) {
      console.log(error)
  })
  
  
  
  document.getElementById('membresias').style.display="flex";
}


function closeSidebar() {
  if (sidebarOpen) {
    sidebar.classList.remove("sidebar_responsive");
    sidebarOpen = false;
  }
}

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


var number = document.getElementById('inputEdadCLI');

// No acepta Letras
function isNumber(e) {
  evt = (evt) ? evt : window.event;
  let charCode = (evt.which) ? evt.which : evt.keyCode;
  if ((charCode > 31 && (charCode < 48 || charCode > 57)) && charCode !== 46) {
    evt.preventDefault();
  } else {
    return true;
  }
}

// No puede ser mayor a 80
  function less80(e) {
  if(document.getElementById('inputEdadCLI').value > 80){
    alert("La edad es mayor a 80")
    document.getElementById('inputEdadCLI').value=0;
  }
}
