package com.boonetech.agenda.protea.infraestrucutre.repository;


import com.boonetech.agenda.protea.infraestrucutre.entity.Agendamento;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findAllByTenantId(String tenantId);

    boolean existsByDataHoraAndProfissionalAndTenantId(LocalDateTime data, String prof, String tenantId);
}
