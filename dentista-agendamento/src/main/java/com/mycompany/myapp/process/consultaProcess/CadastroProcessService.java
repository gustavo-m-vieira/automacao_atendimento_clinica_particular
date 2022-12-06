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
public class CadastroProcessService {

    private final TaskInstanceService taskInstanceService;

    private final ConsultaService consultaService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final ConsultaProcessRepository consultaProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final CadastroProcessMapper cadastroProcessMapper;

    private final ConsultaProcessMapper consultaProcessMapper;

    public CadastroProcessService(
        TaskInstanceService taskInstanceService,
        ConsultaService consultaService,
        TaskInstanceRepository taskInstanceRepository,
        ConsultaProcessRepository consultaProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        CadastroProcessMapper cadastroProcessMapper,
        ConsultaProcessMapper consultaProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.consultaService = consultaService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.consultaProcessRepository = consultaProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.cadastroProcessMapper = cadastroProcessMapper;
        this.consultaProcessMapper = consultaProcessMapper;
    }

    public CadastroProcessContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        ConsultaProcessDTO consultaProcess = consultaProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(cadastroProcessMapper::toConsultaProcessDTO)
            .orElseThrow();

        CadastroProcessContextDTO cadastroProcessContext = new CadastroProcessContextDTO();
        cadastroProcessContext.setTaskInstance(taskInstanceDTO);
        cadastroProcessContext.setConsultaProcess(consultaProcess);

        return cadastroProcessContext;
    }

    public CadastroProcessContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(CadastroProcessContextDTO cadastroProcessContext) {
        ConsultaDTO consultaDTO = consultaService.findOne(cadastroProcessContext.getConsultaProcess().getConsulta().getId()).orElseThrow();
        consultaDTO.setCpf(cadastroProcessContext.getConsultaProcess().getConsulta().getCpf());
        consultaService.save(consultaDTO);
    }

    public void complete(CadastroProcessContextDTO cadastroProcessContext) {
        save(cadastroProcessContext);
        ConsultaProcessDTO consultaProcess = consultaProcessRepository
            .findByProcessInstanceId(cadastroProcessContext.getConsultaProcess().getProcessInstance().getId())
            .map(consultaProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(cadastroProcessContext.getTaskInstance(), consultaProcess);
    }
}
