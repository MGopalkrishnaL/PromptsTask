package com.Ubona.PromptsTask.restController;

import com.Ubona.PromptsTask.model.Prompt;
import com.Ubona.PromptsTask.model.ServerChecking;
import com.Ubona.PromptsTask.service.PromptService;
import com.Ubona.PromptsTask.service.ServerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ServerController {
    private final PromptService promptService;
    private  final ServerService serverService;
    @PostMapping("addPromptToTheServer")
    public ResponseEntity<String> AddPromptToServer(Long promptId,String serverName){
        Prompt prompt = promptService.findPromptById(promptId);
        ServerChecking serverChecking = serverService.getServerByName(serverName);
        List<Prompt> prompts = serverChecking.getPromptsInScenario();
        prompts.add(prompt);
        serverChecking.setPromptsInScenario(prompts);
        return new ResponseEntity<>("Prompt added to the server successfully", HttpStatus.CREATED);
    }
}
