<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="es_AR"/>
<section id="empleados">
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de Empleados</h4>
                    </div>
                    <table class="table table-striped table-dark">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>Nombre</th>
                                <th>Apellido</th>
                                <th>Area</th>
                                <th>Sueldo</th>
                                <th>Telefono</th>
                                <th>Accion</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="empleado" items="${empleados}">
                                <tr>
                                    <td>${empleado.idempleado}</td>
                                    <td>${empleado.nombre}</td>
                                    <td>${empleado.apellido}</td>
                                    <td>${empleado.area}</td>
                                    <td><fmt:formatNumber value="${empleado.sueldo}" type="currency"/></td>
                                    <td>${empleado.telefono}</td>
                                    <td>
                                        <!-- NO OLVIDAR COMPLETAR HREF -->
                                        <a href="" class="btn btn-secondary">
                                            <i class="fas fa-angle-double-right"></i>
                                            Editar
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-center bg-danger text-white mb-3">
                    <div class="card-body">
                        <h3>Cantidad de empleados</h3>
                        <!--<!-- NO OLVIDAR MODIFICAR EL METODO DEL DISPLAY -->
                        <h4 class="display-4">${cantidadTelefonos}</h4>
                    </div>
                </div>

                <div class="card text-center bg-success text-white mb-3">
                    <div class="card-body">
                        <h3>Sueldo total de los empleados</h3>
                        <h4 class="display-4"><fmt:formatNumber value="${sueldoTotal}" type="currency"/></h4>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="/WEB-INF/paginas/operaciones/agregarEmpleado.jsp"/>