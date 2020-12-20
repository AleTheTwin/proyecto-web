<#list clientes as cliente>
<div class="form-check">
  <input class="form-check-input" type="checkbox" value="" id=${cliente_index}>
  <label class="form-check-label" for="flexCheckDefault">
    ${cliente.nombreC}
  </label>
</div>
</#list>