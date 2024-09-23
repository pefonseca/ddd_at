package management.cursos.domain.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import management.cursos.infra.repository.AlunoNotaRepository;
import management.cursos.infra.repository.config.response.AlunoCadastroCursoDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CursoRequestConsumer {

    private final AlunoNotaRepository repository;

    @RabbitListener(queues = "curso-queue")
    public void cadastrarAlunoId(@Payload Message message) {
        var messageRequest = message.getPayload();

        if(messageRequest instanceof String) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                var alunoCadastroCursoDTO = objectMapper.readValue((String) messageRequest, AlunoCadastroCursoDTO.class);

                repository.save(alunoCadastroCursoDTO.toEntity());

            }catch (JsonProcessingException e) {
                log.error("[AuditLogServiceImpl] -> (logAudit): Erro ao desserializar a mensagem: {}", e.getMessage());
            }
        } else {
            log.error("[AuditLogServiceImpl] -> (logAudit): Tipo de payload inesperado: {}", messageRequest.getClass().getName());
        }
    }
}
