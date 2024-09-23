package management.matricula.infra.config.feign;

import management.matricula.infra.config.feign.response.CursoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "curso-service", url = "http://localhost:8081")
public interface CursoFeignClient {

    @GetMapping(value = "/api/v1/curso/{id}", produces = "application/json")
    ResponseEntity<CursoResponseDTO> findById(@PathVariable(value = "id") Long id);

}
