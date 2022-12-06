package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ConsultaProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ConsultaProcess} and its DTO {@link ConsultaProcessDTO}.
 */
@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class, ConsultaMapper.class })
public interface ConsultaProcessMapper extends EntityMapper<ConsultaProcessDTO, ConsultaProcess> {
    @Mapping(target = "processInstance", source = "processInstance")
    @Mapping(target = "consulta", source = "consulta")
    ConsultaProcessDTO toDto(ConsultaProcess s);
}
