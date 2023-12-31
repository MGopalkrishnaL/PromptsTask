package com.Ubona.PromptsTask.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Scenario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scenarioId;
    private String scenarioName;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "Scenario_Prompts",
            joinColumns = @JoinColumn(name = "scenarioId"),
            inverseJoinColumns = @JoinColumn(name = "promptId")
    )
    private List<Prompt> promptList;
    private List<Long> sequence;

}
