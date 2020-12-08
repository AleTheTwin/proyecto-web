

var bLogeo = document.getElementById('signin');
bLogeo.addEventListener('click', function () {
axios.post('https://gimnasio-sw.herokuapp.com/login',  {
    Email: document.getElementById('inputEmail').value,
    Password: document.getElementById('inputPassword').value
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


})