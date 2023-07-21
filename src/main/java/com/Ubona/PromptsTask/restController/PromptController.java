package com.Ubona.PromptsTask.restController;

import com.Ubona.PromptsTask.dto.PromptDto;
import com.Ubona.PromptsTask.model.Prompt;
import com.Ubona.PromptsTask.service.PromptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:8081/")
@RequiredArgsConstructor
public class PromptController {
    private final PromptService promptService;

    @GetMapping("promptList")
    public List<Prompt> promptList(){
        return promptService.findAllPrompts();
    }

    @PostMapping("/createPrompt")
    public ResponseEntity<String> savePrompt(@RequestBody PromptDto prompt){
        return promptService.savePrompt(prompt);
    }

    @GetMapping("/listOfPromptNames")
    public ResponseEntity<List<String>> promptNameList(){
        return promptService.getAllPromptsName();
    }
    @GetMapping("/listOfPromptInServer")
    public ResponseEntity<List<Prompt>> promptList(@RequestParam String serverName){
        return promptService.getAllPromptsInServer(serverName);
    }


}
