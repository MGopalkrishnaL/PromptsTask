package com.Ubona.PromptsTask.Repository;

import com.Ubona.PromptsTask.model.ServerChecking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerRepository extends JpaRepository<ServerChecking,String> {
    ServerChecking findByServerName(String serviceName);
}
