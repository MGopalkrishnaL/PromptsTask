package com.Ubona.PromptsTask.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServerChecking {
    @Id
    private String serverName;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "Prompt_in_server",
            joinColumns = @JoinColumn(name = "serverName"),
            inverseJoinColumns = @JoinColumn(name = "promptId")
    )
    private List<Prompt> promptsInScenario;
}
