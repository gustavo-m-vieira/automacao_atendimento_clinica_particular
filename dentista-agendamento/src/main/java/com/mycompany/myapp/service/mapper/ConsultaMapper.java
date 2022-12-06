package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ConsultaDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Consulta} and its DTO {@link ConsultaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ConsultaMapper extends EntityMapper<ConsultaDTO, Consulta> {}
