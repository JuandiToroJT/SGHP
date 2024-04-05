package SistemaGestionHorarios.SGHP.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

@Validated
public class RespuestaTransaccion {
  @JsonProperty("numeroRegistro")
  private Long numeroRegistro = null;
  @JsonProperty("success")
  private Boolean success = null;

  public RespuestaTransaccion(Long numeroRegistro, Boolean success) {
    this.numeroRegistro = numeroRegistro;
    this.success = success;
  }

  public Long getNumeroRegistro() {
    return numeroRegistro;
  }

  public void setNumeroRegistro(Long numeroRegistro) {
    this.numeroRegistro = numeroRegistro;
  }

  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }
}