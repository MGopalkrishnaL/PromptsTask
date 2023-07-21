package com.Ubona.PromptsTask.Repository;

import com.Ubona.PromptsTask.model.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScenarioRepository extends JpaRepository<Scenario, Integer> {

    boolean existsByScenarioName(String scenarioName);

    Scenario findByScenarioName(String scenarioName);

    Scenario findByScenarioId(Integer scenarioId);

    void deleteByScenarioId(Integer scenarioId);
}
