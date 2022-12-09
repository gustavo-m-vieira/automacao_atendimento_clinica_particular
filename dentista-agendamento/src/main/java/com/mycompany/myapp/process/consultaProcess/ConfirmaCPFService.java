package com.mycompany.myapp.process.consultaProcess;

import com.mycompany.myapp.repository.ConsultaProcessRepository;
import com.mycompany.myapp.service.ConsultaService;
import com.mycompany.myapp.service.dto.ConsultaDTO;
import com.mycompany.myapp.service.dto.ConsultaProcessDTO;
import com.mycompany.myapp.service.mapper.ConsultaProcessMapper;
import org.akip.repository.TaskInstanceRepository;
import org.akip.service.TaskInstanceService;
import org.akip.service.dto.TaskInstanceDTO;
import org.akip.service.mapper.TaskInstanceMapper;
import org.springframework.stereotype.Component;

@Component
public class ConfirmaCPFService {

    private final TaskInstanceService taskInstanceService;

    private final ConsultaService consultaService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final ConsultaProcessRepository consultaProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final ConfirmaCPFMapper confirmaCPFMapper;

    private final ConsultaProcessMapper consultaProcessMapper;

    public ConfirmaCPFService(
        TaskInstanceService taskInstanceService,
        ConsultaService consultaService,
        TaskInstanceRepository taskInstanceRepository,
        ConsultaProcessRepository consultaProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        ConfirmaCPFMapper confirmaCPFMapper,
        ConsultaProcessMapper consultaProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.consultaService = consultaService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.consultaProcessRepository = consultaProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.confirmaCPFMapper = confirmaCPFMapper;
        this.consultaProcessMapper = consultaProcessMapper;
    }

    public ConfirmaCPFContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        ConsultaProcessDTO consultaProcess = consultaProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(confirmaCPFMapper::toConsultaProcessDTO)
            .orElseThrow();

        ConfirmaCPFContextDTO confirmaCPFContext = new ConfirmaCPFContextDTO();
        confirmaCPFContext.setTaskInstance(taskInstanceDTO);
        confirmaCPFContext.setConsultaProcess(consultaProcess);

        return confirmaCPFContext;
    }

    public ConfirmaCPFContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(ConfirmaCPFContextDTO confirmaCPFContext) {
        ConsultaDTO consultaDTO = consultaService.findOne(confirmaCPFContext.getConsultaProcess().getConsulta().getId()).orElseThrow();
        consultaDTO.setCpf(confirmaCPFContext.getConsultaProcess().getConsulta().getCpf());
        consultaService.save(consultaDTO);
    }

    public void complete(ConfirmaCPFContextDTO confirmaCPFContext) {
        save(confirmaCPFContext);
        ConsultaProcessDTO consultaProcess = consultaProcessRepository
            .findByProcessInstanceId(confirmaCPFContext.getConsultaProcess().getProcessInstance().getId())
            .map(consultaProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(confirmaCPFContext.getTaskInstance(), consultaProcess);
    }
}
