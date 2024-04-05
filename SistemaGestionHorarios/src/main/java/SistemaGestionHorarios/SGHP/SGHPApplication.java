package SistemaGestionHorarios.SGHP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "SistemaGestionHorarios.SGHP.Entity")
public class SGHPApplication {
    public static void main(String[] args) {
        SpringApplication.run(SGHPApplication.class, args);
    }
}
