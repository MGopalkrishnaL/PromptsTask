package com.Ubona.PromptsTask.model;

import jakarta.persistence.*;
import lombok.*;

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
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Scenario_Prompts",
            joinColumns = @JoinColumn(name = "scenarioId"),
            inverseJoinColumns = @JoinColumn(name = "promptId")
    )
    private List<Prompt> promptList;
}
