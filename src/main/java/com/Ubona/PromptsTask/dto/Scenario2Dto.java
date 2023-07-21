package com.Ubona.PromptsTask.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Scenario2Dto {
    public List<String> promptList;
    public String scenarioName;
}
