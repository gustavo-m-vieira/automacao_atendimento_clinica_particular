package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.ConsultaRepository;
import com.mycompany.myapp.service.ConsultaService;
import com.mycompany.myapp.service.dto.ConsultaDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Consulta}.
 */
@RestController
@RequestMapping("/api")
public class ConsultaResource {

    private final Logger log = LoggerFactory.getLogger(ConsultaResource.class);

    private final ConsultaService consultaService;

    private final ConsultaRepository consultaRepository;

    public ConsultaResource(ConsultaService consultaService, ConsultaRepository consultaRepository) {
        this.consultaService = consultaService;
        this.consultaRepository = consultaRepository;
    }

    /**
     * {@code GET  /consultas} : get all the consultas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of consultas in body.
     */
    @GetMapping("/consultas")
    public List<ConsultaDTO> getAllConsultas() {
        log.debug("REST request to get all Consultas");
        return consultaService.findAll();
    }

    /**
     * {@code GET  /consultas/:id} : get the "id" consulta.
     *
     * @param id the id of the consultaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the consultaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/consultas/{id}")
    public ResponseEntity<ConsultaDTO> getConsulta(@PathVariable Long id) {
        log.debug("REST request to get Consulta : {}", id);
        Optional<ConsultaDTO> consultaDTO = consultaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(consultaDTO);
    }
}
