
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



  <#--  <h1 style= "text-align: center;">aaa</h1>
  <div class="row">
  <#list membresias as membresia>
    <div class="cards col-sm">
      <h2 style="text-align: center" class="text-muted" >${membresia.id}</h2>
      <h1 style="text-align: center " class="title_Cards" >$
        <small>${membresia.descripcion}</small>
        <small class="text-muted">/ mes</small>
      </h1>
      <ul class="list-unstyled mt-3 mb-5" style="margin-top: 20px; text-align: center" >
        <li>${membresia.precio}</li>
        <#if ${membresia.id} == ${selected}>
          
        <#else>
          <a id="botonSeleccionar">Seleccionar</a>
        </#if>
      </ul>
    </div>
  </#list>
  </div>  -->

  <h1 style= "text-align: center;">aaa</h1>
  <div class="row">
  <#list membresias as membresia>
    <div class="cards col-sm" style="display: flex; justify-content: space-around;  ">
      <h2 style="text-align: center" class="text-muted" >${membresia.id}</h2>
      <h1 style="text-align: center " class="title_Cards" >$
        <small>${membresia.descripcion}</small>
        <small class="text-muted">/ mes</small>
      </h1>
      <ul class="list-unstyled mt-3 mb-5" style="margin-top: 20px; text-align: center" >
        <li>${membresia.precio}</li>
        <button class="btn btn-success" id=membresia${membresia_index} type="button">Seleccionar</button>
      </ul>
    </div>
  </#list>
  </div>
 


