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
public class InfoCadastroService {

    private final TaskInstanceService taskInstanceService;

    private final ConsultaService consultaService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final ConsultaProcessRepository consultaProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final InfoCadastroMapper infoCadastroMapper;

    private final ConsultaProcessMapper consultaProcessMapper;

    public InfoCadastroService(
        TaskInstanceService taskInstanceService,
        ConsultaService consultaService,
        TaskInstanceRepository taskInstanceRepository,
        ConsultaProcessRepository consultaProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        InfoCadastroMapper infoCadastroMapper,
        ConsultaProcessMapper consultaProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.consultaService = consultaService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.consultaProcessRepository = consultaProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.infoCadastroMapper = infoCadastroMapper;
        this.consultaProcessMapper = consultaProcessMapper;
    }

    public InfoCadastroContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        ConsultaProcessDTO consultaProcess = consultaProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(infoCadastroMapper::toConsultaProcessDTO)
            .orElseThrow();

        InfoCadastroContextDTO infoCadastroContext = new InfoCadastroContextDTO();
        infoCadastroContext.setTaskInstance(taskInstanceDTO);
        infoCadastroContext.setConsultaProcess(consultaProcess);

        return infoCadastroContext;
    }

    public InfoCadastroContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(InfoCadastroContextDTO infoCadastroContext) {
        ConsultaDTO consultaDTO = consultaService.findOne(infoCadastroContext.getConsultaProcess().getConsulta().getId()).orElseThrow();
        consultaDTO.setName(infoCadastroContext.getConsultaProcess().getConsulta().getName());
        consultaDTO.setCpf(infoCadastroContext.getConsultaProcess().getConsulta().getCpf());
        consultaDTO.setEmail(infoCadastroContext.getConsultaProcess().getConsulta().getEmail());
        consultaService.save(consultaDTO);
    }

    public void complete(InfoCadastroContextDTO infoCadastroContext) {
        save(infoCadastroContext);
        ConsultaProcessDTO consultaProcess = consultaProcessRepository
            .findByProcessInstanceId(infoCadastroContext.getConsultaProcess().getProcessInstance().getId())
            .map(consultaProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(infoCadastroContext.getTaskInstance(), consultaProcess);
    }
}
