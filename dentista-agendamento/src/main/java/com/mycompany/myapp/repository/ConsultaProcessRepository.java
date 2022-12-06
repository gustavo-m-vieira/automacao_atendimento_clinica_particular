package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ConsultaProcess;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ConsultaProcess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConsultaProcessRepository extends JpaRepository<ConsultaProcess, Long> {
    Optional<ConsultaProcess> findByProcessInstanceId(Long processInstanceId);
}
