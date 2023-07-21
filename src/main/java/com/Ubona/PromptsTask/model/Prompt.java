package com.Ubona.PromptsTask.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    private String valueType;
    private String fileName;
    private String promptName;
    private String parameterKey;
    private String variableType;
    private boolean fileStatus;
    private boolean filePresentInServer;
}
