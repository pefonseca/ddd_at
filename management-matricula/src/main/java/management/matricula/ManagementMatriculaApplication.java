package management.matricula;

import management.matricula.infra.config.feign.AlunoFeignClient;
import management.matricula.infra.config.feign.CursoFeignClient;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableRabbit
@SpringBootApplication
@EnableFeignClients(clients = {AlunoFeignClient.class, CursoFeignClient.class})
public class ManagementMatriculaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagementMatriculaApplication.class, args);
    }

}
