package com.boonetech.agenda.protea.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record AgendamentoRequestDTO(
       @NotBlank(message = "O profissional é obrigatório") String profissional,
       @NotBlank(message = "O paciente é obrigatório") String paciente,
       @NotNull(message = "A data deve ser futura") LocalDateTime dataHora,
       @NotBlank String tenantId // ID da empresa/clínic
) {}
