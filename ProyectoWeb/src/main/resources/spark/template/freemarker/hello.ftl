
<#--  <div calss="container-fluid">
  <h1>Rutinas</h1>
  <button href="https://www.google.com" >${email}</button>

  <p>Pagina de rutinas</p>

  <ul>
    <#list rutinas as rutina>
      <button>id de la rutina:  ${rutina.id} , descripcion:  ${rutina.descripcion}</button>
    </#list>
  </ul>
</div>  -->
  <h1 style= "text-align: center;">Estas son sus rutinas</h1>
  <#list rutinas as rutina>
    <div class="cards">
      <h2 style="text-align: center" class="text-muted">${rutina.id}</h2>
      <ul class="list-unstyled mt-3 mb-5" style="position: relative; text-align: center" >
        <li id="descripcionMembresia">${rutina.descripcion}</li>
      </ul>
    </div>
  </#list>


