package com.Ubona.PromptsTask.service;

import com.Ubona.PromptsTask.Repository.ScenarioRepository;
import com.Ubona.PromptsTask.dto.ScenarioDTO;
import com.Ubona.PromptsTask.model.Prompt;
import com.Ubona.PromptsTask.model.Scenario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScenarioService {
    private final ScenarioRepository scenarioRepository;
    private final PromptService promptService;

    public ResponseEntity<Scenario> saveScenario(ScenarioDTO scenarioDTO) {
        if (scenarioRepository.existsByScenarioName(scenarioDTO.getScenarioName())) {
            Scenario scenario = scenarioRepository.findByScenarioName(scenarioDTO.getScenarioName());
            return new ResponseEntity<>(scenario, HttpStatus.FOUND);
        }
        Scenario scenario = new Scenario();
        scenario.setScenarioName(scenarioDTO.getScenarioName());
        List<String> promptList = scenarioDTO.getPromptList();
        List<Prompt> prompts = promptService.savePrompt(promptList);
        scenario.setPromptList(prompts);
        scenarioRepository.save(scenario);
        return new ResponseEntity<>(scenario, HttpStatus.CREATED);
    }

    public List<Scenario> getAllScenarios() {
        return scenarioRepository.findAll();
    }

    public ResponseEntity<String> saveScenario2(String[] arr, String scenarioName) throws Exception {
        String dataOutPut = "";
        Scenario scenario = new Scenario();
        if (scenarioRepository.existsByScenarioName(scenarioName)) {
            scenario = scenarioRepository.findByScenarioName(scenarioName);
            dataOutPut = promptGenerator(scenario.getPromptList());
            return new ResponseEntity<>(dataOutPut, HttpStatus.OK);
        }
        List<Prompt> promptList = promptService.findAllPrompts();
        List<Prompt> promptsInScenario = new ArrayList<>();
        for (String str : arr) {
            for (Prompt prompt : promptList) {
                if (prompt.getValue().equals(str)) {
                    promptsInScenario.add(prompt);
                }
            }
        }
        scenario.setScenarioName(scenarioName);
        scenario.setPromptList(promptsInScenario);
        dataOutPut = promptGenerator(promptsInScenario);
        return new ResponseEntity<>(dataOutPut, HttpStatus.OK);
    }

    public String promptGenerator(List<Prompt> promptList) throws Exception {
        StringBuilder outPutAudiFile = new StringBuilder();
        String fileLocation = "/home/gopal2001/Music/";
        String extension = ".wav";
        for (Prompt prompt : promptList) {
            if (prompt.getValueType().equals("prompt")) {
                String promptName = fileLocation +prompt.getName() + extension;
                FileInputStream fileInputStream = new FileInputStream(promptName);
                byte[] bytes = fileInputStream.readAllBytes();
                String encoded = Base64.getEncoder().encodeToString(bytes);
                outPutAudiFile.append(encoded);
            } else {
                String value = prompt.getValue();
                String encoded = Base64.getEncoder().encodeToString(value.getBytes());
                outPutAudiFile.append(encoded);
            }
        }
        return outPutAudiFile.toString();
    }
}
