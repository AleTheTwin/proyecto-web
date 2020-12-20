<div class="form-floating mb-3 container-fluid">
    <select class="form-select" aria-label="Default select example" id="clienteARutina">
    <#list clientes as cliente>
        <option value=${cliente.correo}>${cliente.correo}</option>
    </#list>
    </select>
</div>
