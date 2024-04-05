package SistemaGestionHorarios.SGHP.Util;

import SistemaGestionHorarios.SGHP.Model.MensajeErrorItem;

public class ManejadorExcepciones {
    public static MensajeErrorItem AdministrarExcepcion(Exception ex){
        return new MensajeErrorItem(ex.getMessage());
    }
}
