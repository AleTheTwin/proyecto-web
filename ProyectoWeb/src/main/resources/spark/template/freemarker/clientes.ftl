<#--  <h1 style= "text-align: center;">Estos son sus clientes</h1>
  <div class="row" style="display: flex;">
  <#list clientes as cliente>
    <div class="cards col-sm" style="justify-content: space-around; padding: 5px">
      <h2 style="text-align: center" class="text-muted" >${cliente.nombreC}</h2>
      <ul class="list-unstyled mt-3 mb-5" style="margin-top: 20px; text-align: center" >
        <li>Correo: ${cliente.correo} </li>
        <li>Edad: ${cliente.edad} </li>
        <li>Sexo: ${cliente.sexo} </li>
        <li>Tipo de cliente: ${cliente.tipoCliente} </li>
      </ul>
    </div>
  </#list>
  </div>  -->
  <div>
    <h1 class= "texto1" style= "text-align: center;">Estos son sus clientes</h1>
  <div class="row" style="display: flex;">
  <#list clientes as cliente>
    <div class="cards col-sm" style="justify-content: space-around; padding: 5px;  margin: 20px;">
      <h2 style="text-align: center" class="texto" >${cliente.nombreC}</h2>
      <ul class="list-unstyled mt-3 mb-5" style="margin-top: 20px; text-align: center" >
        <li class="texto">Correo: ${cliente.correo} </li>
        <li class="texto">Edad: ${cliente.edad} </li>
        <li class="texto">Tipo de cliente: ${cliente.tipoCliente} </li>
      </ul>
    </div>
  </#list>
  </div>