var bRegistro= document.getElementById('botonRegistro');
bRegistro.addEventListener('click', function () {
    var pass = document.getElementById('contraseñaREG').value;
    var passConfirm = document.getElementById('concontraseñaREG').value;
    if(pass==passConfirm) {
        console.log("La contraseña coincide")
        axios.post('https://gimnasio-sw.herokuapp.com/registro',  {
            Email: document.getElementById('correoREG').value,
            Password: document.getElementById('contraseñaREG').value,
            Edad: document.getElementById('edadREG').value,
            Sexo: document.getElementById('sexBox').value,
            NombreC: document.getElementById('usuarioREG').value,
            TipoCliente: document.getElementById('userBox').value
        })
        .then(function (response) {
            console.log(response.data);
            if(response.data==document.getElementById('correoREG"').value){
                location.href = "/HTML/Ventanas/Index.html";
            }
            
        })
        .catch(function (error) {
            console.log(error)
        })
    } else {
        console.log("La contraseña no coincide")
    }
})