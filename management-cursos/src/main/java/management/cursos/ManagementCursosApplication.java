package management.cursos;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableRabbit
@SpringBootApplication
public class ManagementCursosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagementCursosApplication.class, args);
    }

}
