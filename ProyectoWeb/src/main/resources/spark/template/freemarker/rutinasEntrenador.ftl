
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
  <h1 style= "texto1: center;">Estas son sus rutinas 
  <button type="button" class="btn btn-success" onclick="llenarModal()" data-bs-toggle="modal" data-bs-target="#nuevaRutina">Nueva rutina</button>
  </h1>
  
  <div class="row">
  <#list rutinas as rutina>
    <div class="cards col-sm"  style=" margin: 20px; padding: 1px">
      <h2 style="text-align: center" class="texto">${rutina.id}</h2>
      <ul class="list-unstyled mt-3 mb-5" style="position: relative; text-align: center" >
        <li class="texto">${rutina.descripcion}</li>
        <button style="margin-top:3%" type="button" class="btn btn-success" onclick="editarRutina('${rutina.id}')" data-bs-toggle="modal" data-bs-target="#editarRutina">Editar</button>
        <button style="margin-top:3%" type="button" class="btn btn-danger"  onclick="guardarIdRutina('${rutina.id}')" data-bs-toggle="modal" data-bs-target="#borrarModal">Borrar</button>
      </ul>
    </div>
  </#list>
  </div>
 


