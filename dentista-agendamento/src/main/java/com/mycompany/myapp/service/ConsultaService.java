package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Consulta;
import com.mycompany.myapp.repository.ConsultaRepository;
import com.mycompany.myapp.service.dto.ConsultaDTO;
import com.mycompany.myapp.service.mapper.ConsultaMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Consulta}.
 */
@Service
@Transactional
public class ConsultaService {

    private final Logger log = LoggerFactory.getLogger(ConsultaService.class);

    private final ConsultaRepository consultaRepository;

    private final ConsultaMapper consultaMapper;

    public ConsultaService(ConsultaRepository consultaRepository, ConsultaMapper consultaMapper) {
        this.consultaRepository = consultaRepository;
        this.consultaMapper = consultaMapper;
    }

    /**
     * Save a consulta.
     *
     * @param consultaDTO the entity to save.
     * @return the persisted entity.
     */
    public ConsultaDTO save(ConsultaDTO consultaDTO) {
        log.debug("Request to save Consulta : {}", consultaDTO);
        Consulta consulta = consultaMapper.toEntity(consultaDTO);
        consulta = consultaRepository.save(consulta);
        return consultaMapper.toDto(consulta);
    }

    /**
     * Partially update a consulta.
     *
     * @param consultaDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ConsultaDTO> partialUpdate(ConsultaDTO consultaDTO) {
        log.debug("Request to partially update Consulta : {}", consultaDTO);

        return consultaRepository
            .findById(consultaDTO.getId())
            .map(
                existingConsulta -> {
                    consultaMapper.partialUpdate(existingConsulta, consultaDTO);
                    return existingConsulta;
                }
            )
            .map(consultaRepository::save)
            .map(consultaMapper::toDto);
    }

    /**
     * Get all the consultas.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ConsultaDTO> findAll() {
        log.debug("Request to get all Consultas");
        return consultaRepository.findAll().stream().map(consultaMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one consulta by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ConsultaDTO> findOne(Long id) {
        log.debug("Request to get Consulta : {}", id);
        return consultaRepository.findById(id).map(consultaMapper::toDto);
    }

    /**
     * Delete the consulta by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Consulta : {}", id);
        consultaRepository.deleteById(id);
    }
}
