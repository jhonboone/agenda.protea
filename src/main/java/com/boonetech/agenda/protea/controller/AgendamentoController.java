package com.boonetech.agenda.protea.controller;

import com.boonetech.agenda.protea.business.service.AgendamentoService;
import com.boonetech.agenda.protea.dto.AgendamentoRequestDTO;
import com.boonetech.agenda.protea.infraestrucutre.entity.Agendamento;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/agendamentoss")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService service;

    @PostMapping
    public ResponseEntity<Agendamento> save(@RequestBody @Valid AgendamentoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @GetMapping("/{tenantId}")
    public ResponseEntity<List<Agendamento>> listar(@PathVariable String tenantId) {
        return ResponseEntity.ok(service.buscarPorUnidade(tenantId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, String dto) {
        service.delete(id, dto);
        return  ResponseEntity.noContent().build();
    }
}
