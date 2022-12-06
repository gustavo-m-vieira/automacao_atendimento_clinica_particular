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
public class SelConsultaService {

    private final TaskInstanceService taskInstanceService;

    private final ConsultaService consultaService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final ConsultaProcessRepository consultaProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final SelConsultaMapper selConsultaMapper;

    private final ConsultaProcessMapper consultaProcessMapper;

    public SelConsultaService(
        TaskInstanceService taskInstanceService,
        ConsultaService consultaService,
        TaskInstanceRepository taskInstanceRepository,
        ConsultaProcessRepository consultaProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        SelConsultaMapper selConsultaMapper,
        ConsultaProcessMapper consultaProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.consultaService = consultaService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.consultaProcessRepository = consultaProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.selConsultaMapper = selConsultaMapper;
        this.consultaProcessMapper = consultaProcessMapper;
    }

    public SelConsultaContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        ConsultaProcessDTO consultaProcess = consultaProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(selConsultaMapper::toConsultaProcessDTO)
            .orElseThrow();

        SelConsultaContextDTO selConsultaContext = new SelConsultaContextDTO();
        selConsultaContext.setTaskInstance(taskInstanceDTO);
        selConsultaContext.setConsultaProcess(consultaProcess);

        return selConsultaContext;
    }

    public SelConsultaContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(SelConsultaContextDTO selConsultaContext) {
        ConsultaDTO consultaDTO = consultaService.findOne(selConsultaContext.getConsultaProcess().getConsulta().getId()).orElseThrow();
        consultaDTO.setName(selConsultaContext.getConsultaProcess().getConsulta().getName());
        consultaDTO.setCpf(selConsultaContext.getConsultaProcess().getConsulta().getCpf());
        consultaDTO.setDate(selConsultaContext.getConsultaProcess().getConsulta().getDate());
        consultaDTO.setEmail(selConsultaContext.getConsultaProcess().getConsulta().getEmail());
        consultaDTO.setPrice(selConsultaContext.getConsultaProcess().getConsulta().getPrice());
        consultaDTO.setDurationInMinutes(selConsultaContext.getConsultaProcess().getConsulta().getDurationInMinutes());
        consultaService.save(consultaDTO);
    }

    public void complete(SelConsultaContextDTO selConsultaContext) {
        save(selConsultaContext);
        ConsultaProcessDTO consultaProcess = consultaProcessRepository
            .findByProcessInstanceId(selConsultaContext.getConsultaProcess().getProcessInstance().getId())
            .map(consultaProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(selConsultaContext.getTaskInstance(), consultaProcess);
    }
}
