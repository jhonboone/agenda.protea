package com.boonetech.agenda.protea;

import com.boonetech.agenda.protea.business.service.AgendamentoService;
import com.boonetech.agenda.protea.dto.AgendamentoRequestDTO;
import com.boonetech.agenda.protea.infraestrucutre.repository.AgendamentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AgendamentoServiceTest {

    @Mock
    private AgendamentoRepository repository;

    @InjectMocks
    private AgendamentoService service;

    @Test
    void deveSalvarAgendamentoComSucesso() {
        // Construir (Given)
        var dto = new AgendamentoRequestDTO("Aplicador Heron", "Helena Vanderlei", LocalDateTime.now().plusDays(1), "unidade-01");

        // Operar (When)
        service.save(dto);

        // Verificar (Then)
        verify(repository, times(1)).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoHorarioEstiverOcupado() {
        // Construir (Given)
        var dto = new AgendamentoRequestDTO("Dr. Silva", "João", LocalDateTime.now().plusDays(1), "unidade-01");
        when(repository.existsByDataHoraAgendamentoAndNomeProfissionalAndTenantId(
                dto.dataHora(),
                dto.profissional(),
                dto.tenantId()
        )).thenReturn(false);

        // Operar e Verificar (When & Then)
        assertThrows(RuntimeException.class, () -> service.save(dto));
    }
}