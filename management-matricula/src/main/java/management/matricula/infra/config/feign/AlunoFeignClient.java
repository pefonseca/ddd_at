package management.matricula.infra.config.feign;

import management.matricula.infra.config.feign.response.AlunoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "aluno-service", url = "http://localhost:8080")
public interface AlunoFeignClient {

    @GetMapping(value = "/api/v1/aluno/{id}", produces = "application/json")
    ResponseEntity<AlunoResponseDTO> findById(@PathVariable(value = "id") Long id);

}
