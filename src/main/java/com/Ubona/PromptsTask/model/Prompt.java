package com.Ubona.PromptsTask.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Prompt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long promptId;
    private String promptType;
    private String value;

}
