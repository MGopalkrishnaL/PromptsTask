package com.Ubona.PromptsTask.service;

import com.Ubona.PromptsTask.Repository.ServerRepository;
import com.Ubona.PromptsTask.model.Prompt;
import com.Ubona.PromptsTask.model.ServerChecking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ServerService {
    private final ServerRepository serverRepository;
    public boolean ifPresentInServer(Long promptId,String serviceName){
        ServerChecking checking = serverRepository.findByServerName(serviceName);
        List<Prompt> prompts = checking.getPromptsInScenario();
        for(Prompt prompt: prompts){
            if(Objects.equals(prompt.getPromptId(), promptId)){
                return true;
            }
        }
        return false;
    }

    public ServerChecking getServerByName(String serverName) {
        return serverRepository.findByServerName(serverName);
    }
}
