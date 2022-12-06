package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import org.akip.service.dto.ProcessInstanceDTO;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.ConsultaProcess} entity.
 */
public class ConsultaProcessDTO implements Serializable {

    private Long id;

    private ProcessInstanceDTO processInstance;

    private ConsultaDTO consulta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProcessInstanceDTO getProcessInstance() {
        return processInstance;
    }

    public void setProcessInstance(ProcessInstanceDTO processInstance) {
        this.processInstance = processInstance;
    }

    public ConsultaDTO getConsulta() {
        return consulta;
    }

    public void setConsulta(ConsultaDTO consulta) {
        this.consulta = consulta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ConsultaProcessDTO)) {
            return false;
        }

        ConsultaProcessDTO consultaProcessDTO = (ConsultaProcessDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, consultaProcessDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ConsultaProcessDTO{" +
            "id=" + getId() +
            ", processInstance=" + getProcessInstance() +
            ", consulta=" + getConsulta() +
            "}";
    }
}
