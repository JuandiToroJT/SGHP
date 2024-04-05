package SistemaGestionHorarios.SGHP.Model;

import SistemaGestionHorarios.SGHP.Util.ReglasExcepcion;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Validated
public class DatosTarea {
    @JsonProperty("id")
    private long id = 0;

    @JsonProperty("titulo")
    private String titulo = "";

    @JsonProperty("descripcion")
    private String descripcion = "";

    @JsonProperty("fechaInicio")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate fechaInicio = null;

    @JsonProperty("fechaFin")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate fechaFin = null;

    @JsonProperty("tiempo")
    private int tiempo = 0;

    @JsonProperty("prioridad")
    private int prioridad = 0;

    public DatosTarea() {
    }

    public DatosTarea(long id, String titulo, String descripcion, LocalDate fechaInicio, LocalDate fechaFin, int tiempo, int prioridad) {
        if (titulo == null)
            titulo = "";
        if (descripcion == null)
            descripcion = "";

        this.id = id;
        this.titulo = titulo.trim();
        this.descripcion = descripcion.trim();
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tiempo = tiempo;
        this.prioridad = prioridad;
    }

    public long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public int getTiempo() {
        return tiempo;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        if (titulo == null)
            titulo = "";

        this.titulo = titulo.trim();
    }

    public void setDescripcion(String descripcion) {
        if (descripcion == null)
            descripcion = "";

        this.descripcion = descripcion.trim();
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public void validarIntegridad(){
        if (getTitulo() == null || getTitulo() == "")
            throw new ReglasExcepcion("El título es obligatorio");
        if (getDescripcion() == null || getDescripcion() == "")
            throw new ReglasExcepcion("La descripción es obligatoria");
        if (getFechaInicio() == null)
            throw new ReglasExcepcion("La fecha de inicio es obligatoria");
        if (getFechaFin() == null)
            throw new ReglasExcepcion("La fecha de finalización es obligatoria");
        if (getTiempo() <= 0)
            throw new ReglasExcepcion("El tiempo necesario en días es obligatorio");
        if (getPrioridad() == 0)
            throw new ReglasExcepcion("La prioridad es obligatoria");

        if (getTitulo().length() > 20)
            throw new ReglasExcepcion("La longitud del campo título no puede superar los 20 caracteres");
        if (getDescripcion().length() > 100)
            throw new ReglasExcepcion("La longitud del campo descripción no puede superar los 100 caracteres");
        if (getFechaInicio().isAfter(getFechaFin()))
            throw new ReglasExcepcion("La fecha de inicio no puede ser mayor a la fecha de finalización");
    }
}

