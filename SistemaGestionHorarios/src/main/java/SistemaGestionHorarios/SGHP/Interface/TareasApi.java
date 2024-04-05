package SistemaGestionHorarios.SGHP.Interface;

import SistemaGestionHorarios.SGHP.Model.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface TareasApi {
    @ApiOperation(value = "Obtener tareas", nickname = "obtenerTareas", notes = "Obtiene el listado de tareas organizadas dependiendo de la prioridad y tiempo", response = DatosTarea.class, responseContainer = "List", tags = {"Tareas"})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Listado de tareas", response = DatosTarea.class, responseContainer = "List"), @ApiResponse(code = 400, message = "Inconsistencia de la petición", response = MensajeErrorItem.class)})
    @RequestMapping(value = "/tareas/listar", produces = {"application/json"}, method = RequestMethod.GET)
    ResponseEntity<?> getTareas();

    @ApiOperation(value = "Crea una nueva tarea/evento", nickname = "crearTareaEvento", notes = "Crea una nueva tarea o evento", response = RespuestaTransaccion.class, tags = {"Tareas"})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Respuesta del proceso", response = RespuestaTransaccion.class), @ApiResponse(code = 400, message = "Inconsistencia de la petición", response = MensajeErrorItem.class)})
    @RequestMapping(value = "/tareas/adicionar", produces = {"application/json"}, method = RequestMethod.POST)
    ResponseEntity<RespuestaTransaccion> crearTareaEvento(@ApiParam(value = "Datos de la tarea", required = true) @RequestBody DatosTarea body);

    @ApiOperation(value = "Edita una tarea/evento", nickname = "editarTareaEvento", notes = "Edita una tarea o evento ya existente", response = RespuestaTransaccion.class, tags = {"Tareas"})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Respuesta del proceso", response = RespuestaTransaccion.class), @ApiResponse(code = 400, message = "Inconsistencia de la petición", response = MensajeErrorItem.class)})
    @RequestMapping(value = "/tareas/editar", produces = {"application/json"}, method = RequestMethod.PUT)
    ResponseEntity<RespuestaTransaccion> editarTareaEvento(@ApiParam(value = "Datos de la tarea", required = true) @RequestBody DatosTarea body);

    @ApiOperation(value = "Elimina una tarea/evento", nickname = "eliminarTareaEvento", notes = "Elimina una tarea o evento", response = RespuestaTransaccion.class, tags = {"Tareas"})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Respuesta del proceso", response = RespuestaTransaccion.class), @ApiResponse(code = 400, message = "Inconsistencia de la petición", response = MensajeErrorItem.class)})
    @RequestMapping(value = "/tareas/{id}/eliminar", produces = {"application/json"}, method = RequestMethod.DELETE)
    ResponseEntity<RespuestaTransaccion> eliminarTareaEvento(@ApiParam(value = "Id de la tarea/evento", required = true) @PathVariable("id") long id);
}
