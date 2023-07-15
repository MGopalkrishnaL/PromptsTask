package com.Ubona.PromptsTask.restController;

import com.Ubona.PromptsTask.dto.Scenario2Dto;
import com.Ubona.PromptsTask.dto.ScenarioDTO;
import com.Ubona.PromptsTask.model.Scenario;
import com.Ubona.PromptsTask.service.ScenarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:8081/")
public class ScenarioController {
    private final ScenarioService scenarioService;

    @PostMapping("saveScenario")
    public ResponseEntity<Scenario> saveScenario(@RequestBody ScenarioDTO scenarioDTO) {
        return scenarioService.saveScenario(scenarioDTO);
    }

    @GetMapping("getAllScenarios")
    public List<Scenario> getAllScenarios() {
        return scenarioService.getAllScenarios();
    }

    @PostMapping("saveTheScenario")
    public ResponseEntity<String> scenarioSave(@RequestBody Scenario2Dto scenario2Dto) throws Exception {
        String[] arr = scenario2Dto.scenario.split(",");
        return scenarioService.saveScenario2(arr, scenario2Dto.getScenarioName());
    }
}
