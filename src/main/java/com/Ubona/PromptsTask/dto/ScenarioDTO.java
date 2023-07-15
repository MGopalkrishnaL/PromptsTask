package com.Ubona.PromptsTask.dto;

import lombok.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScenarioDTO {
    private String agentName;
    private String scenarioName;
    private List<String> promptList;
}

