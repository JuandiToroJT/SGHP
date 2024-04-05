package SistemaGestionHorarios.SGHP.Service;

import SistemaGestionHorarios.SGHP.Entity.Tareas;
import SistemaGestionHorarios.SGHP.Model.ColaPrioridad;
import SistemaGestionHorarios.SGHP.Model.DatosTarea;
import SistemaGestionHorarios.SGHP.Repository.TareasRepository;
import SistemaGestionHorarios.SGHP.Util.ReglasExcepcion;
import SistemaGestionHorarios.SGHP.Model.RespuestaTransaccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TareasService {
    @Autowired
    private TareasRepository tareasRepository;

    ColaPrioridad<DatosTarea> colaTareas = new ColaPrioridad<>();

    public List<DatosTarea> getTareas(){
        if (colaTareas.estaVacia()){
            List<Tareas> listadoTareas = tareasRepository.findAll();
            for (int i = 0; i < listadoTareas.size(); i++) {
                DatosTarea datosTarea = new DatosTarea(listadoTareas.get(i).getId(), listadoTareas.get(i).getTitulo(), listadoTareas.get(i).getDescripcion(),
                        listadoTareas.get(i).getFechaInicio(), listadoTareas.get(i).getFechaFin(), listadoTareas.get(i).getTiempo(), listadoTareas.get(i).getPrioridad());

                colaTareas.agregar(datosTarea, datosTarea.getPrioridad());
            }
        }

        return colaTareas.listado();
    }

    public RespuestaTransaccion crearTareaEvento(DatosTarea body) {
        body.validarIntegridad();

        //Insertar en base de datos
        Tareas tareas = new Tareas(body.getTitulo(), body.getDescripcion(), body.getFechaInicio(), body.getFechaFin(), body.getTiempo(), body.getPrioridad());
        tareasRepository.save(tareas);

        //Insertar en cola
        body.setId(tareas.getId());
        colaTareas.agregar(body, body.getPrioridad());

        return new RespuestaTransaccion((long)1, true);
    }

    public RespuestaTransaccion editarTareaEvento(DatosTarea body) {
        body.validarIntegridad();

        //Editar en base de datos
        Tareas tareas = new Tareas(body.getTitulo(), body.getDescripcion(), body.getFechaInicio(), body.getFechaFin(), body.getTiempo(), body.getPrioridad());
        tareas.setId(body.getId());
        tareasRepository.save(tareas);

        //Editar en cola
        colaTareas.editarPorId(body);

        return new RespuestaTransaccion((long)1, true);
    }

    public RespuestaTransaccion eliminarTareaEvento(long id) {
        //Eliminar en base de datos
        tareasRepository.deleteById(id);

        //Eliminar en cola
        colaTareas.eliminarPorId(id);

        return new RespuestaTransaccion((long)1, true);
    }
}
