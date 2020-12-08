

var bLogeo = document.getElementById('signin');
bLogeo.addEventListener('click', function () {
axios.post('http://localhost:666/login', {
    Email : document.getElementById('inputEmail').value,
    Password : document.getElementById('inputPassword').value


})
    .then(function (response) {

        console.log(response.data)
        
        
    })
    .catch(function (error) {
        console.log(error)
    })


})