package com.Ubona.PromptsTask.Repository;

import com.Ubona.PromptsTask.model.Prompt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromptRepository extends JpaRepository<Prompt,Long> {
    boolean existsByValue(String name);

    Prompt findByValue(String name);
}
