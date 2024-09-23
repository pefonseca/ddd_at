package management.matricula.domain.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import management.matricula.domain.model.dto.AlunoCadastroCursoDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.annotation.Configuration;

import static management.matricula.infra.config.rabbit.CursoExchangeConfig.EXCHANGE_NAME;
import static management.matricula.infra.config.rabbit.CursoRabbitConfig.ROUTING_KEY_NAME;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class CursoRequestProducer {

    private final AmqpTemplate amqpTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void integration(AlunoCadastroCursoDTO alunoCadastroCursoDTO) {
        try {
            String message = objectMapper.writeValueAsString(alunoCadastroCursoDTO);

            amqpTemplate.convertAndSend(
                    EXCHANGE_NAME,
                    ROUTING_KEY_NAME,
                    message
            );

        } catch (JsonProcessingException e) {
            log.error("[CursoRequestProducer] -> (integration): Ocorreu um erro ao integrar com a aplicação de cursos: {}", e.getMessage());
        }
    }

}
