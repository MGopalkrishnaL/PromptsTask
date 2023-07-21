package com.Ubona.PromptsTask.service;

import com.Ubona.PromptsTask.Repository.ScenarioRepository;
import com.Ubona.PromptsTask.dto.ResponseDto;
import com.Ubona.PromptsTask.model.Prompt;
import com.Ubona.PromptsTask.model.Scenario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ScenarioService {
    private final ScenarioRepository scenarioRepository;
    private final PromptService promptService;

    public List<ResponseDto> getAllScenarios() {
        List<Scenario> scenarioList= scenarioRepository.findAll();
        List<ResponseDto> responseDtos = new ArrayList<>();
        for(Scenario scenario : scenarioList){
            ResponseDto responseDto = new ResponseDto();
            responseDto.setScenarioId(scenario.getScenarioId());
            responseDto.setScenarioName(scenario.getScenarioName());
            Map<Long,Prompt> promptIdWithPrompt= new LinkedHashMap<>();
            for(Prompt prompt: scenario.getPromptList()){
                promptIdWithPrompt.put(prompt.getPromptId(),prompt);
            }
            responseDto.setPromptList(promptIdWithPrompt);
            responseDtos.add(responseDto);
        }
        return responseDtos;
    }

    public ResponseEntity<String> savePrompts(List<String> promptList, String scenarioName) throws Exception {
        List<Prompt> prompts = promptService.findAllPrompts();
        List<Prompt> prompts1 = new ArrayList<>();
        String dataOutPut = "";
        Scenario scenario = new Scenario();
        if (scenarioRepository.existsByScenarioName(scenarioName)) {
            scenario = scenarioRepository.findByScenarioName(scenarioName);
            return new ResponseEntity<>(dataOutPut, HttpStatus.OK);
        }
        List<Long> sequenceList = new ArrayList<>();
        for (String str : promptList) {
            for (Prompt prompt : prompts) {
                if (prompt.getFileName().equals(str)||prompt.getParameterKey().equals(str)) {
                    prompts1.add(prompt);
                    sequenceList.add(prompt.getPromptId());
                }
            }
        }
        scenario.setScenarioName(scenarioName);
        scenario.setPromptList(prompts1);
        scenario.setSequence(sequenceList);
        scenarioRepository.save(scenario);
        return new ResponseEntity<>(dataOutPut, HttpStatus.OK);

    }

    public String promptGenerator(List<Long> sequenceList) throws Exception {
        StringBuilder outPutAudiFile = new StringBuilder();
        String fileLocation = "/home/gopal2001/Music/";
        String extension = ".wav";
        List<Prompt> promtList = promptService.findAllPrompts();
        List<Prompt> sequencePrompt = new ArrayList<>();
        for (Long promptId : sequenceList) {
            for (Prompt prompt : promtList) {
                if (prompt.getPromptId() == promptId) {
                    if (prompt.getValueType().equals("prompt")) {
                        String promptName = fileLocation + prompt.getFileName() + extension;
                        FileInputStream fileInputStream = new FileInputStream(promptName);
                        byte[] bytes = fileInputStream.readAllBytes();
                        String encoded = Base64.getEncoder().encodeToString(bytes);
                        outPutAudiFile.append(encoded);
                    } else {
                        String value = prompt.getParameterKey();
                        String encoded = Base64.getEncoder().encodeToString(value.getBytes());
                        outPutAudiFile.append(encoded);
                    }
                }
            }
        }
        return outPutAudiFile.toString();
    }

    public ResponseEntity<String> getScenarioById(Integer id) throws Exception {
        List<Scenario> scenario = scenarioRepository.findAll();
        Scenario scenario1 = null;
        for (Scenario scenario2 : scenario) {
            if (Objects.equals(scenario2.getScenarioId(), id)) {
                scenario1 = scenario2;
            }
        }
        String data = promptGenerator(scenario1.getSequence());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteScenario(Integer scenarioId) {

        scenarioRepository.deleteByScenarioId(scenarioId);
        return new ResponseEntity<>("scenario deleted successfully", HttpStatus.OK);
    }

    public ResponseEntity<String> updateScenario(Integer scenarioId, String
            scenarioName, List<String> promptList) {
        Scenario scenario = scenarioRepository.findByScenarioId(scenarioId);
        scenario.setScenarioId(scenarioId);
        scenario.setScenarioName(scenarioName);
        List<Prompt> promptList1 = promptService.findAllPrompts();
        List<Prompt> updatedPromptList = new ArrayList<>();
        List<Long> updatedScequence = new ArrayList<>();
        for (String str : promptList) {
            for (Prompt prompt : promptList1) {
                if (str.equals(prompt.getFileName())) {
                    updatedPromptList.add(prompt);
                    updatedScequence.add(prompt.getPromptId());
                }
            }
        }
        scenario.setPromptList(updatedPromptList);
        scenario.setSequence(updatedScequence);
        scenarioRepository.save(scenario);
        return new ResponseEntity<>("scenario is updated Scuessfully", HttpStatus.OK);
    }

    public Scenario getScenario(Integer scenarioId) {
        return scenarioRepository.findByScenarioId(scenarioId);
    }

    public ResponseEntity<String> saveSceanrio(String[] arr,String scenarioName) {
        Scenario scenario = new Scenario();
        scenario.setScenarioName(scenarioName);
        List<Prompt> prompts = promptService.findAllPrompts();
        List<Prompt> promptList = new ArrayList<>();
        List<Long> sequence = new ArrayList<>();
        for(String str : arr){
            for(Prompt prompt: prompts){
                if(str.startsWith("#")){
                    String word = removeHashSymbol(str);
                    if(word.equals(prompt.getParameterKey())){
                        promptList.add(prompt);
                        sequence.add(prompt.getPromptId());
                    }
                }else{
                    if(str.equals(prompt.getFileName())){
                        promptList.add(prompt);
                        sequence.add(prompt.getPromptId());
                    }
                }
            }
        }
        scenario.setSequence(sequence);
        scenario.setPromptList(promptList);
        scenarioRepository.save(scenario);
        return new ResponseEntity<>("Scenario saved Successfully",HttpStatus.CREATED);
    }
    public static String removeHashSymbol(String inputString) {
        return inputString.replace("#", "");
    }
}
