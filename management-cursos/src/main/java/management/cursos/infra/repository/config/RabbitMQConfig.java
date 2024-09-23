package management.cursos.infra.repository.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RabbitMQConfig {

    public static final String CURSO_REQUEST_QUEUE = "curso-request-queue";

    public Queue cursoRequestQueue() {
        log.info("[RabbitMQConfig] -> (cursoRequestQueue): Configurando a fila '{}'.", CURSO_REQUEST_QUEUE);
        log.info("[RabbitMQConfig] -> (cursoRequestQueue): Fila '{}' configurada com sucesso.", CURSO_REQUEST_QUEUE);
        return new Queue(CURSO_REQUEST_QUEUE);
    }
}
