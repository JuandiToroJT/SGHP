package SistemaGestionHorarios.SGHP.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

@Validated
public class MensajeErrorItem extends RespuestaTransaccion {
  @JsonProperty("mensajeError")
  private String mensajeError = null;

  public MensajeErrorItem(String mensajeError) {
    super((long)0, false);
    this.mensajeError = mensajeError;
  }

  public String getMensajeError() {
    return mensajeError;
  }

  public void setMensajeError(String mensajeError) {
    this.mensajeError = mensajeError;
  }
}