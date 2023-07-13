package com.Ubona.PromptsTask.service;

import com.Ubona.PromptsTask.Repository.ScenarioRepository;
import com.Ubona.PromptsTask.dto.ScenarioDTO;
import com.Ubona.PromptsTask.model.Prompt;
import com.Ubona.PromptsTask.model.Scenario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScenarioService {
    private final ScenarioRepository scenarioRepository;
    private final PromptService promptService;
    public ResponseEntity<Scenario> saveScenario(ScenarioDTO scenarioDTO) {
        if(scenarioRepository.existsByScenarioName(scenarioDTO.getScenarioName())){
            Scenario scenario =scenarioRepository.findByScenarioName(scenarioDTO.getScenarioName());
            return new ResponseEntity<>(scenario,HttpStatus.FOUND);
        }
        Scenario scenario = new Scenario();
        scenario.setScenarioName(scenarioDTO.getScenarioName());
        List<String> promptList = scenarioDTO.getPromptList();
        List<Prompt> prompts = promptService.savePrompt(promptList);
        scenario.setPromptList(prompts);
        scenarioRepository.save(scenario);
        return new ResponseEntity<>(scenario, HttpStatus.CREATED);
    }

    public ResponseEntity<List<Scenario>> getAllScenarios() {
        List<Scenario> scenarioList = scenarioRepository.findAll();
        return new ResponseEntity<>(scenarioList,HttpStatus.OK);
    }
}
