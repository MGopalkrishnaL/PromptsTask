package com.Ubona.PromptsTask.service;

import com.Ubona.PromptsTask.PromptsTaskApplication;
import com.Ubona.PromptsTask.Repository.PromptRepository;
import com.Ubona.PromptsTask.Repository.ServerRepository;
import com.Ubona.PromptsTask.dto.PromptDto;
import com.Ubona.PromptsTask.model.Prompt;
import com.Ubona.PromptsTask.model.ServerChecking;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PromptService {
    private final PromptRepository promptRepository;
    private final ServerService serverService;
    public List<Prompt> findAllPrompts() {
        return promptRepository.findAll();
    }

    public ResponseEntity<String> savePrompt(PromptDto promptData) {
        Prompt prompt = new Prompt();
        prompt.setValueType(promptData.getPromptType());
        if(promptData.getPromptType().equals("prompt")){
            prompt.setPromptName(promptData.getPromptName());
            if(promptData.getFilename()!=null){
                prompt.setFileStatus(true);
                prompt.setFileName(promptData.getFilename());
            }else {
                prompt.setFileStatus(false);
            }
            prompt.setFilePresentInServer(false);
        }else{
            prompt.setPromptName(prompt.getPromptName());
            prompt.setParameterKey(promptData.getParameterKey());
            prompt.setVariableType(promptData.getVariableType());
        }
        prompt.setFilePresentInServer(false);
        promptRepository.save(prompt);
        return new ResponseEntity<>("prompt Saved Successfully", HttpStatus.CREATED);
    }
    public ResponseEntity<String> deletePrompt(Long id) {
        Prompt prompt = promptRepository.findByPromptId(id);
        promptRepository.delete(prompt);
        return new ResponseEntity<>("Prompt deleted successfully",HttpStatus.OK);
    }

    public ResponseEntity<List<String>> getAllPromptsName() {
        List<Prompt> prompts = promptRepository.findAll();
        List<String> promptNames = new ArrayList<>();
        for(Prompt prompt:prompts){
            if(prompt.getValueType().equals("prompt")){
                promptNames.add(prompt.getPromptName());
            }else{
                String name = prompt.getParameterKey();
                String promptName = '#'+name+'#';
                promptNames.add(promptName);
            }
        }
        return new ResponseEntity<>(promptNames,HttpStatus.OK);
    }

    public ResponseEntity<List<Prompt>> getAllPromptsInServer(String serverName) {
        ServerChecking serverChecking = serverService.getServerByName(serverName);
        List<Prompt> AllPrompts = promptRepository.findAll();
        List<Prompt> promptList = serverChecking.getPromptsInScenario();
        List<Prompt> prompts = new ArrayList<>();
        for(Prompt promptInServer:promptList){
            for(Prompt promptInAllPrompt:AllPrompts){
                if(promptInServer.equals(promptInAllPrompt)){
                    promptInAllPrompt.setFilePresentInServer(true);
                }else {
                    promptInAllPrompt.setFilePresentInServer(false);
                }
            }
        }
        return new ResponseEntity<>(prompts,HttpStatus.OK);
    }

    public Prompt findPromptById(Long promptId) {
        return promptRepository.findByPromptId(promptId);
    }
}
