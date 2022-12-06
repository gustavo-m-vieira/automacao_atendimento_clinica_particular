package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.ConsultaProcess;
import com.mycompany.myapp.repository.ConsultaProcessRepository;
import com.mycompany.myapp.repository.ConsultaRepository;
import com.mycompany.myapp.service.dto.ConsultaProcessDTO;
import com.mycompany.myapp.service.mapper.ConsultaProcessMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.akip.domain.ProcessInstance;
import org.akip.service.ProcessInstanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ConsultaProcess}.
 */
@Service
@Transactional
public class ConsultaProcessService {

    public static final String BPMN_PROCESS_DEFINITION_ID = "ConsultaProcess";

    private final Logger log = LoggerFactory.getLogger(ConsultaProcessService.class);

    private final ProcessInstanceService processInstanceService;

    private final ConsultaRepository consultaRepository;

    private final ConsultaProcessRepository consultaProcessRepository;

    private final ConsultaProcessMapper consultaProcessMapper;

    public ConsultaProcessService(
        ProcessInstanceService processInstanceService,
        ConsultaRepository consultaRepository,
        ConsultaProcessRepository consultaProcessRepository,
        ConsultaProcessMapper consultaProcessMapper
    ) {
        this.processInstanceService = processInstanceService;
        this.consultaRepository = consultaRepository;
        this.consultaProcessRepository = consultaProcessRepository;
        this.consultaProcessMapper = consultaProcessMapper;
    }

    /**
     * Save a consultaProcess.
     *
     * @param consultaProcessDTO the entity to save.
     * @return the persisted entity.
     */
    public ConsultaProcessDTO create(ConsultaProcessDTO consultaProcessDTO) {
        log.debug("Request to save ConsultaProcess : {}", consultaProcessDTO);

        ConsultaProcess consultaProcess = consultaProcessMapper.toEntity(consultaProcessDTO);

        //Saving the domainEntity
        consultaRepository.save(consultaProcess.getConsulta());

        //Creating the process instance in the Camunda and setting it in the process entity
        ProcessInstance processInstance = processInstanceService.create(
            BPMN_PROCESS_DEFINITION_ID,
            "Consulta#" + consultaProcess.getConsulta().getId(),
            consultaProcess
        );
        consultaProcess.setProcessInstance(processInstance);

        //Saving the process entity
        consultaProcess = consultaProcessRepository.save(consultaProcess);
        return consultaProcessMapper.toDto(consultaProcess);
    }

    /**
     * Get all the consultaProcesss.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ConsultaProcessDTO> findAll() {
        log.debug("Request to get all ConsultaProcesss");
        return consultaProcessRepository
            .findAll()
            .stream()
            .map(consultaProcessMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one consultaProcess by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ConsultaProcessDTO> findOne(Long id) {
        log.debug("Request to get ConsultaProcess : {}", id);
        return consultaProcessRepository.findById(id).map(consultaProcessMapper::toDto);
    }

    /**
     * Get one consultaProcess by id.
     *
     * @param processInstanceId the id of the processInstance associated to the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ConsultaProcessDTO> findByProcessInstanceId(Long processInstanceId) {
        log.debug("Request to get ConsultaProcess by  processInstanceId: {}", processInstanceId);
        return consultaProcessRepository.findByProcessInstanceId(processInstanceId).map(consultaProcessMapper::toDto);
    }
}
