package com.boonetech.agenda.protea.business.service;

import com.boonetech.agenda.protea.dto.AgendamentoRequestDTO;
import com.boonetech.agenda.protea.infraestrucutre.entity.Agendamento;
import com.boonetech.agenda.protea.infraestrucutre.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tools.jackson.databind.util.BeanUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository repository;

    //POST
    public Agendamento save(AgendamentoRequestDTO dto) {
        if (repository.existsByDataHoraAndProfissionalAndTenantId(
                dto.dataHora(),
                dto.profissional(),
                dto.tenantId())) {
            throw new RuntimeException("Horário do profissional desta unidade está ocupado");
        }

        Agendamento entity = new Agendamento();
        BeanUtils.copyProperties(dto, entity); // Copia dados DTO > Entity
        return repository.save(entity);
    }

    //GET
    public List<Agendamento> buscarPorUnidade(String dto) {
        return repository.findAllByTenantId(dto);
    }

    //DELETE
    public void delete(Long id, String dto) {
        repository.deleteById(id);
    }
}
