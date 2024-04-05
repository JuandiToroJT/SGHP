package SistemaGestionHorarios.SGHP.Controller;

import SistemaGestionHorarios.SGHP.Interface.TareasApi;
import SistemaGestionHorarios.SGHP.Model.*;
import io.swagger.annotations.Api;
import SistemaGestionHorarios.SGHP.Service.TareasService;
import SistemaGestionHorarios.SGHP.Util.ManejadorExcepciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "Tareas", tags = "Tareas")
public class TareasApiController implements TareasApi {
    @Autowired
    private TareasService tareasService;

    public ResponseEntity<?> getTareas() {
        try {
            List<DatosTarea> listadoTareas = tareasService.getTareas();
            return new ResponseEntity<>(listadoTareas, HttpStatus.OK);
        }
        catch (Exception ex){
            MensajeErrorItem mensajeErrorItem = ManejadorExcepciones.AdministrarExcepcion(ex);
            return new ResponseEntity<>(mensajeErrorItem, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<RespuestaTransaccion> crearTareaEvento(DatosTarea body) {
        try {
            RespuestaTransaccion respuesta = tareasService.crearTareaEvento(body);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }
        catch (Exception ex){
            MensajeErrorItem mensajeErrorItem = ManejadorExcepciones.AdministrarExcepcion(ex);
            return new ResponseEntity<>(mensajeErrorItem, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<RespuestaTransaccion> editarTareaEvento(DatosTarea body) {
        try {
            RespuestaTransaccion respuesta = tareasService.editarTareaEvento(body);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }
        catch (Exception ex){
            MensajeErrorItem mensajeErrorItem = ManejadorExcepciones.AdministrarExcepcion(ex);
            return new ResponseEntity<>(mensajeErrorItem, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<RespuestaTransaccion> eliminarTareaEvento(long id) {
        try {
            RespuestaTransaccion respuesta = tareasService.eliminarTareaEvento(id);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }
        catch (Exception ex){
            MensajeErrorItem mensajeErrorItem = ManejadorExcepciones.AdministrarExcepcion(ex);
            return new ResponseEntity<>(mensajeErrorItem, HttpStatus.BAD_REQUEST);
        }
    }
}
