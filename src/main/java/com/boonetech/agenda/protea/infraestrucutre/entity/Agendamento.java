package com.boonetech.agenda.protea.infraestrucutre.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "agendamentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Nome do profissional é obrigatórios")
    private String nomeProfissional;

    @NotNull(message = "Nome do paciente é obrigatório")
    private String nomePaciente;

    @NotNull(message = "Data e hora são obrigatórios")
    @Future(message = "Agendamento deve ser para uma data futura")
    private LocalDateTime dataHoraAgendamento;

    @Column(nullable = false)
    private String tenantId; // Chave de isolamento
}
