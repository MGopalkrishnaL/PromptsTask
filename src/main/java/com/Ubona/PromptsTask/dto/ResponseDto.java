package com.Ubona.PromptsTask.dto;

import com.Ubona.PromptsTask.model.Prompt;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    private Integer scenarioId;
    private String scenarioName;
    private Map<Long, Prompt> promptList;
}
