package com.Ubona.PromptsTask.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PromptDto {
    private String promptName;
    private String promptType;
    private String variableType;
    private String filename;
    private String parameterKey;
}
