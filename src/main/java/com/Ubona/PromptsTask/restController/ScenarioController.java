package com.Ubona.PromptsTask.restController;

import com.Ubona.PromptsTask.dto.ScenarioDTO;
import com.Ubona.PromptsTask.model.Scenario;
import com.Ubona.PromptsTask.service.ScenarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScenarioController {
    private final ScenarioService scenarioService;

    @PostMapping("saveScenario")
    public ResponseEntity<Scenario> saveScenario(@RequestBody ScenarioDTO scenarioDTO) {
        return scenarioService.saveScenario(scenarioDTO);
    }
    @GetMapping("getAllScenarios")
    public ResponseEntity<List<Scenario>> getAllScenarios() {
        return scenarioService.getAllScenarios();
    }
}
