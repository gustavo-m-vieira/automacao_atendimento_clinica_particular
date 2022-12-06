package com.mycompany.myapp.process.consultaProcess;

import com.mycompany.myapp.domain.Consulta;
import com.mycompany.myapp.domain.ConsultaProcess;
import com.mycompany.myapp.service.dto.ConsultaDTO;
import com.mycompany.myapp.service.dto.ConsultaProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class })
public interface CadastroProcessMapper {
    @Mapping(target = "processInstance", source = "processInstance", qualifiedByName = "loadTaskContext")
    ConsultaProcessDTO toConsultaProcessDTO(ConsultaProcess consultaProcess);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "cpf", source = "cpf")
    ConsultaDTO toConsultaDTO(Consulta consulta);
}