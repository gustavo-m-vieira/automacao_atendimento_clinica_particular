package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.ConsultaProcessService;
import com.mycompany.myapp.service.dto.ConsultaProcessDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.ConsultaProcess}.
 */
@RestController
@RequestMapping("/api")
public class ConsultaProcessResource {

    private final Logger log = LoggerFactory.getLogger(ConsultaProcessResource.class);

    private static final String ENTITY_NAME = "consultaProcess";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ConsultaProcessService consultaProcessService;

    public ConsultaProcessResource(ConsultaProcessService consultaProcessService) {
        this.consultaProcessService = consultaProcessService;
    }

    /**
     * {@code POST  /consulta-processes} : Create a new consultaProcess.
     *
     * @param consultaProcessDTO the consultaProcessDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new consultaProcessDTO, or with status {@code 400 (Bad Request)} if the consultaProcess has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/consulta-processes")
    public ResponseEntity<ConsultaProcessDTO> create(@RequestBody ConsultaProcessDTO consultaProcessDTO) throws URISyntaxException {
        log.debug("REST request to save ConsultaProcess : {}", consultaProcessDTO);
        if (consultaProcessDTO.getId() != null) {
            throw new BadRequestAlertException("A new consultaProcess cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ConsultaProcessDTO result = consultaProcessService.create(consultaProcessDTO);
        return ResponseEntity
            .created(new URI("/api/consulta-processes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /consulta-processes} : get all the consultaProcesss.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of consultaProcesss in body.
     */
    @GetMapping("/consulta-processes")
    public List<ConsultaProcessDTO> getAllConsultaProcesss() {
        log.debug("REST request to get all ConsultaProcesss");
        return consultaProcessService.findAll();
    }

    /**
     * {@code GET  /consulta-processes/:id} : get the "id" consultaProcess.
     *
     * @param processInstanceId the id of the consultaProcessDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the consultaProcessDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/consulta-processes/{processInstanceId}")
    public ResponseEntity<ConsultaProcessDTO> getByProcessInstanceId(@PathVariable Long processInstanceId) {
        log.debug("REST request to get ConsultaProcess by processInstanceId : {}", processInstanceId);
        Optional<ConsultaProcessDTO> consultaProcessDTO = consultaProcessService.findByProcessInstanceId(processInstanceId);
        return ResponseUtil.wrapOrNotFound(consultaProcessDTO);
    }
}
