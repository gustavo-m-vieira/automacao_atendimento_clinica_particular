package com.mycompany.myapp.process.consultaProcess;

import com.mycompany.myapp.service.dto.ConsultaProcessDTO;
import org.akip.service.dto.TaskInstanceDTO;

public class InfoCadastroContextDTO {

    private ConsultaProcessDTO consultaProcess;
    private TaskInstanceDTO taskInstance;

    public ConsultaProcessDTO getConsultaProcess() {
        return consultaProcess;
    }

    public void setConsultaProcess(ConsultaProcessDTO consultaProcess) {
        this.consultaProcess = consultaProcess;
    }

    public TaskInstanceDTO getTaskInstance() {
        return taskInstance;
    }

    public void setTaskInstance(TaskInstanceDTO taskInstance) {
        this.taskInstance = taskInstance;
    }
}
