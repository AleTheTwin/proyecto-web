var bLogeo = document.getElementById('registrar');
bLogeo.addEventListener('click', function () {
    var pass = document.getElementById('inputPassword').value;
    var passConfirm = document.getElementById('inputPasswordConfirm').value;
    if(pass==passConfirm) {
        console.log("La contraseña coincide")
        axios.post('https://gimnasio-sw.herokuapp.com/registro',  {
            Email: document.getElementById('inputEmail').value,
            Password: document.getElementById('inputPassword').value,
            Edad: document.getElementById('inputEdad').value,
            Sexo: document.getElementById('inputSexo').value,
            NombreC: document.getElementById('inputNombreC').value,
            TipoCliente: document.getElementById('inputTipoC').value
        })
        .then(function (response) {
            console.log(response.data);
            if(response.data==document.getElementById('inputEmail').value){
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