package SistemaGestionHorarios.SGHP.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.temporal.ChronoUnit;

public class ColaPrioridad<T> {
    private Nodo<T> frente;
    private Nodo<T> fondo;

    public ColaPrioridad() {
        this.frente = null;
        this.fondo = null;
    }

    public void agregar(T dato, int prioridad) {
        long diferenciaDias = ChronoUnit.DAYS.between(LocalDate.now(), ((DatosTarea)dato).getFechaFin()) - ((DatosTarea)dato).getTiempo();
        if (prioridad == 1 && diferenciaDias <= 3 && diferenciaDias > 0){
            prioridad = 2;
        }
        else if ((prioridad == 1 || prioridad == 2) && diferenciaDias <= 0){
            prioridad = 3;
        }

        ((DatosTarea)dato).setPrioridad(prioridad);

        Nodo<T> nuevoNodo = new Nodo<>(dato, prioridad);
        if (estaVacia() || prioridad > frente.prioridad) {
            nuevoNodo.siguiente = frente;
            frente = nuevoNodo;
        } else {
            Nodo<T> actual = frente;
            while (actual.siguiente != null && actual.siguiente.prioridad >= prioridad) {
                actual = actual.siguiente;
            }
            nuevoNodo.siguiente = actual.siguiente;
            actual.siguiente = nuevoNodo;
        }
        if (fondo == null) {
            fondo = frente;
        }
    }

    public void editarPorId(T nuevoElemento) {
        long idBuscado = ((DatosTarea)nuevoElemento).getId();
        eliminarPorId(idBuscado);
        agregar(nuevoElemento, ((DatosTarea)nuevoElemento).getPrioridad());
    }

    public void eliminarPorId(long id) {
        if (estaVacia()) {
            throw new IllegalStateException("La cola de prioridad está vacía");
        }

        Nodo<T> actual = frente;
        Nodo<T> anterior = null;

        while (actual != null) {
            if (((DatosTarea)actual.dato).getId() == id) {
                if (anterior == null) {
                    frente = actual.siguiente;
                    if (frente == null) {
                        fondo = null;
                    }
                } else {
                    anterior.siguiente = actual.siguiente;
                    if (actual.siguiente == null) {
                        fondo = anterior;
                    }
                }
                return;
            }
            anterior = actual;
            actual = actual.siguiente;
        }

        throw new IllegalArgumentException("No se encontró ningún elemento con el ID especificado");
    }

    public T remover() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola de prioridad está vacía");
        }
        T dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            fondo = null;
        }
        return dato;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public List<T> listado(){
        List<T> lista = new ArrayList<>();

        Nodo<T> actual = frente;
        while (actual != null) {
            lista.add(actual.dato);
            actual = actual.siguiente;
        }

        return lista;
    }
}

class Nodo<T> {
    T dato;
    int prioridad;
    Nodo<T> siguiente;

    public Nodo(T dato, int prioridad) {
        this.dato = dato;
        this.prioridad = prioridad;
        this.siguiente = null;
    }
}