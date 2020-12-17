<html>
<head>
  <title>Rutinas</title>
</head>
<body>
  <h1>Rutinas</h1>
  <button href="https://www.google.com" >${email}</button>

  <p>Pagina de rutinas</p>

  <ul>
    <#list rutinas as rutina>
      <button>id de la rutina:  ${rutina.id} , descripcion:  ${rutina.descripcion}</button>
    </#list>
  </ul>

</body>
</html>