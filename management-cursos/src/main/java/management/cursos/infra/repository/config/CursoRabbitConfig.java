package management.cursos.infra.repository.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration
@RequiredArgsConstructor
public class CursoRabbitConfig {

    private final DirectExchange directExchange;

    public static final String ROUTING_KEY_NAME = "curso-route-key";
    public static final String QUEUE_NAME = "curso-queue";

    @Bean
    public Queue queue() {
        log.info("[CursoRabbitConfig] -> (queue): Configurando a fila '{}' como durável.", QUEUE_NAME);
        log.info("[CursoRabbitConfig] -> (queue): Fila '{}' configurada com sucesso.", QUEUE_NAME);
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    @Bean
    public Binding binding() {
        log.info("[CursoRabbitConfig] -> (binding): Configurando o Binding entre a fila '{}' e o DirectExchange '{}'", QUEUE_NAME, directExchange.getName());
        log.info("[CursoRabbitConfig] -> (binding): Binding configurado com a chave de roteamento '{}'.", ROUTING_KEY_NAME);
        return BindingBuilder.bind(queue()).to(directExchange).with(ROUTING_KEY_NAME);
    }
}
