//
var alerta = document.getElementById("alerta")
alerta.style.display = 'none';
var actualizar = document.getElementById('actualizar');
var sesion = getCookie("Sesion")
var nombreCliente = document.getElementById("nombreCliente")
var nombreCliente2 = document.getElementById("nombreCliente2")
var nombreConfig = document.getElementById("nombreConfig")
var emailConfig = document.getElementById("emailConfig");
var contraseñaConfig = document.getElementById("passwordConfig");
var contraseñaConfirmConfig = document.getElementById("passwordConfirmConfig");
var edadConfig = document.getElementById("edadConfig");
var sexoConfig = document.getElementById("sexoConfig");
var tipoConfig = document.getElementById("tipoConfig");
var membresiaMenu = document.getElementById("membresiaMenu");
var password;

var rutinas = document.getElementById("rutinas")

axios.post('https://gimnasio-sw.herokuapp.com/membresiasFTL',  {
 //axios.post('http://localhost:4567/membresiasFTL',  {
    Email: sesion
})
.then(function (response) {
    console.log(response.data)
    membresiaMenu.innerHTML = response.data;    
})
.catch(function (error) {
    console.log(error)
})

// axios.post('http://localhost:4567/getDatos',  {
axios.post('https://gimnasio-sw.herokuapp.com/getDatos',  {
      Email: sesion
})
.then(function (response) {
    nombreCliente.innerHTML = "Hola  " + response.data.nombreC;
    nombreCliente2.innerHTML = response.data.nombreC;
    nombreConfig.value = response.data.nombreC;
    emailConfig.value = response.data.correo;
    edadConfig.value = response.data.edad;
    password = response.data.password;
})
.catch(function (error) {
    console.log(error)
})

actualizar.addEventListener('click', function () {
  var actualizaPass = "SI";
  if(nombreConfig.value != "") {
    if(emailConfig.value != "") {
      if(contraseñaConfig.value != "" && contraseñaConfirmConfig.value == "") {
        alerta.innerHTML = "Confirme su contraseña";
      } else if(contraseñaConfig.value == "" && contraseñaConfirmConfig.value != "") {
        alerta.innerHTML = "Ingrese su contraseña";
      } else if(contraseñaConfig.value == "" && contraseñaConfirmConfig.value == "") {
        contraseñaConfig.value = password;
        contraseñaConfirmConfig.value = password;
        actualizaPass = "NO"
      }
      if(contraseñaConfig.value == contraseñaConfirmConfig.value) {
        if(actualizaPass == "SI") {
          contraseñaConfig.value = window.btoa(contraseñaConfig.value);
        }
        alerta.style.display = 'none'
        // axios.post('http://localhost:4567/actualizarCliente',  {
        axios.post('https://gimnasio-sw.herokuapp.com/actualizarCliente',  {
              Email: sesion,
              Correo: emailConfig.value,
              Password: contraseñaConfig.value,
              Nombre: nombreConfig.value,
              Edad: edadConfig.value,
              Sexo: sexoConfig.value,
              Tipo: tipoConfig.value,
              SeActualiza: actualizaPass
        })
        .then(function (response) {
            if(response.data == "0") {
              alerta.innerHTML = "Correo no disponible";
              alerta.style.display = 'inline';
            } else if(response.data == "1") {
              setCookie("Sesion", emailConfig.value, 0)
              alerta.class = "alert alert-success";
              alerta.innerHTML = "Actualización correcta";
              alerta.style.display = 'inline';
              location.href="/HTML/Ventanas/Cliente.html";
            }
        })
        .catch(function (error) {
            console.log(error)
        })
      } else {
        alerta.innerHTML = "Las contraeñas no coinciden";
        alerta.style.display = 'inline';
      }
    } else {
      alerta.innerHTML = "Ingrese un correo electrónico";
      alerta.style.display = 'inline';
    }
  } else {
    alerta.innerHTML = "Ingrese un nombre de usuario";
    alerta.style.display = 'inline';
  }
});

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

function sleep(milliseconds) {
  const date = Date.now();
  let currentDate = null;
  do {
    currentDate = Date.now();
  } while (currentDate - date < milliseconds);
}

function desplegarMembresia(){
  var precio = document.getElementById("precioMembresia");
  var descripcion = document.getElementById("descripcionMembresia");
  var id = document.getElementById("idMembresia");
  axios.post('https://gimnasio-sw.herokuapp.com/membresiaByEmail',  {
  //  axios.post('http://localhost:4567/membresiaByEmail',  {
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
  
  document.getElementById('rutinas').style.display="none";
  document.getElementById('configuracion').style.display="none";
  document.getElementById('membresias').style.display="flex";
}



function desplegarConfiguracion(){
  var precio = document.getElementById("precioMembresia");
  var descripcion = document.getElementById("descripcionMembresia");
  var id = document.getElementById("idMembresia");
  axios.post('https://gimnasio-sw.herokuapp.com/membresiaByEmail',  {
  // axios.post('http://localhost:4567/membresiaByEmail',  {
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
  
  document.getElementById('rutinas').style.display="none";
  document.getElementById('membresias').style.display="none";
  document.getElementById('configuracion').style.display="flex";
}



function desplegarRutinas(){
  var id = document.getElementById("rutinas");
 axios.post('https://gimnasio-sw.herokuapp.com/rutinas',  {
  //  axios.post('http://localhost:4567/rutinas',  {
      Email: sesion
  })
  .then(function (response) {
    id.innerHTML = response.data
  })
  .catch(function (error) {
      console.log(error)
  })
  
  document.getElementById('configuracion').style.display="none";
  document.getElementById('membresias').style.display="none";
  document.getElementById('rutinas').style.display="inline";
}





function closeSidebar() {
  if (sidebarOpen) {
    sidebar.classList.remove("sidebar_responsive");
    sidebarOpen = false;
  }
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
//funcion de selección de membresía

function seleccionarMembresia(id) {
  axios.post('https://gimnasio-sw.herokuapp.com/updateMembresiaCliente',  {
  //  axios.post('http://localhost:4567/updateMembresiaCliente',  {
      Email: sesion,
      Id: id
  })
  .then(function (response) {
    location.href = "/HTML/Ventanas/Cliente.html"
  })
  .catch(function (error) {
      console.log(error)
  })
}
