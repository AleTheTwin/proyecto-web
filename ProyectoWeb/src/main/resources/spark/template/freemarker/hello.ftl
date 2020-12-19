
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

  <#list rutinas as rutina>
    <div class="cards" style="position: relative;">
      <h2 style="text-align: center" class="text-muted" id="idMembresia" >${rutina.id}</h2>
      <ul class="list-unstyled mt-3 mb-5" style="margin-top: 20px; text-align: center" >
        <li id="descripcionMembresia">${rutina.descripcion}</li>
      </ul>
    </div>
  </#list>


