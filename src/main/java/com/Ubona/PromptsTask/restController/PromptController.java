package com.Ubona.PromptsTask.restController;

import com.Ubona.PromptsTask.model.Prompt;
import com.Ubona.PromptsTask.service.PromptService;
import lombok.RequiredArgsConstructor;
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



}
