var bRegistro= document.getElementById('botonRegistro');

var alerta = document.getElementById('alerta');
var alertaContenido = document.getElementById("alerta-in");
alerta.style.display = 'none';


bRegistro.addEventListener('click', function () {
    alerta.style.display = 'none';
    var pass = document.getElementById('contraseñaREG').value;
    var encodedPassword = window.btoa(pass);
    var email = document.getElementById('correoREG').value;
    var passConfirm = document.getElementById('concontraseñaREG').value;
    if(document.getElementById('usuarioREG').value != "") {
        if(email != "") {
            if(pass != "") {
                if(pass==passConfirm) {
                    if(document.getElementById('sexBox').value != "Empty") {
                        if(document.getElementById('userBox').value != "Empty") {
                            console.log(document.getElementById('userBox').value)
                            axios.post('https://gimnasio-sw.herokuapp.com/registro',  {
                            // axios.post('http://localhost:4567/registro',  {
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
                                    setCookie("tipoUsuario", "Cliente");
                                    location.href = "/HTML/Ventanas/Index.html";
                                } else {
                                    alertaContenido.innerHTML = "El correo ya existe"
                                    alerta.style.display = 'inline';
                                    setCookie("Sesion", "", 0)
                                }
                                
                            })
                            .catch(function (error) {
                                console.log(error)
                            })
                        } else {
                            alertaContenido.innerHTML = "Seleccione el tipo de usuario"
                            alerta.style.display = 'inline'
                        }

                    } else {
                        alertaContenido.innerHTML = "Seleccione el sexo"
                        alerta.style.display = 'inline';
                    }
                    
                } else {
                    alertaContenido.innerHTML = "Las contraseñas no coinciden"
                    alerta.style.display = 'inline';
                }
            } else {
                alertaContenido.innerHTML = "Ingrese la contraseña"
                alerta.style.display = 'inline';
            }
        } else {
            alertaContenido.innerHTML = "Ingrese el correo"
            alerta.style.display = 'inline';
        }
        
        
    } else {
        alertaContenido.innerHTML = "Ingrese el nombre del usuario"
        alerta.style.display = 'inline';
    }
    
})
////////////////////////////////////////////////////////////////////
//                     Registro de Entrenadores                   //
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
/////////////////////////////////////////////////////////////////////



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
  