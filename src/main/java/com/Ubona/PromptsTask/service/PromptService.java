package com.Ubona.PromptsTask.service;

import com.Ubona.PromptsTask.Repository.PromptRepository;
import com.Ubona.PromptsTask.model.Prompt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PromptService {
    private final PromptRepository promptRepository;

    public List<Prompt> savePrompt(List<String> promptList) {
        System.out.println(promptList);
        List<Prompt> prompts = new ArrayList<>();
        for (String name : promptList) {
            if (promptRepository.existsByValue(name)) {
                Prompt prompt = promptRepository.findByValue(name);
                if(!prompts.contains(prompt)){
                    prompts.add(prompt);
                }
            }
        }
        return prompts;
    }
}
