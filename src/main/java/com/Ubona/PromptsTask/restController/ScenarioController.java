package com.Ubona.PromptsTask.restController;

import com.Ubona.PromptsTask.dto.ResponseDto;
import com.Ubona.PromptsTask.dto.Scenario2Dto;
import com.Ubona.PromptsTask.model.Scenario;
import com.Ubona.PromptsTask.service.ScenarioService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:8081/")
public class ScenarioController {
    private final ScenarioService scenarioService;

    @GetMapping("getAllScenarios")
    public List<ResponseDto> getAllScenarios() {
        return scenarioService.getAllScenarios();
    }

    @GetMapping("getScenarioById/{scenarioId}")
    public Scenario getScenarioById(@PathVariable Integer scenarioId) {
        return scenarioService.getScenario(scenarioId);
    }

    @PostMapping("saveThePrompt")
    public ResponseEntity<String> scenarioCreation(@RequestBody Scenario2Dto scenario2Dto) throws Exception {
        return scenarioService.savePrompts(scenario2Dto.promptList, scenario2Dto.scenarioName);
    }

    @GetMapping("scenarioId/{scenarioId}")
    public ResponseEntity<String> playTheScenario(@PathVariable Integer scenarioId) throws Exception {
        return scenarioService.getScenarioById(scenarioId);
    }

    @Transactional
    @DeleteMapping("deleteScenario/{scenarioId}")
    public ResponseEntity<String> deleteScenario(@PathVariable Integer scenarioId) {
        return scenarioService.deleteScenario(scenarioId);
    }

    @PutMapping("updateScenario/{scenarioId}")
    public ResponseEntity<String> updateScenario(@PathVariable Integer scenarioId, @RequestBody Scenario2Dto scenario2Dto) {
        return scenarioService.updateScenario(scenarioId, scenario2Dto.getScenarioName(), scenario2Dto.getPromptList());
    }
    @PostMapping("/saveScenario")
    public ResponseEntity<String> saveTheScenario(@RequestParam String data,@RequestParam String scenarioName){
        String [] arr = data.split(",");
        return scenarioService.saveSceanrio(arr,scenarioName);
    }

}
