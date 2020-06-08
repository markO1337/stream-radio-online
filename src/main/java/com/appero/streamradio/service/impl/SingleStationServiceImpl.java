package com.appero.streamradio.service.impl;

import com.appero.streamradio.domain.SingleStation;
import com.appero.streamradio.repository.SingleStationRepository;
import com.appero.streamradio.service.SingleStationService;
import com.appero.streamradio.service.dto.SingleStationDTO;
import com.appero.streamradio.service.mapper.SingleStationMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SingleStation}.
 */
@Service
@Transactional
public class SingleStationServiceImpl implements SingleStationService {
    private final Logger log = LoggerFactory.getLogger(SingleStationServiceImpl.class);

    private final SingleStationRepository singleStationRepository;

    private final SingleStationMapper singleStationMapper;

    public SingleStationServiceImpl(SingleStationRepository singleStationRepository, SingleStationMapper singleStationMapper) {
        this.singleStationRepository = singleStationRepository;
        this.singleStationMapper = singleStationMapper;
    }

    /**
     * Save a singleStation.
     *
     * @param singleStationDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SingleStationDTO save(SingleStationDTO singleStationDTO) {
        log.debug("Request to save SingleStation : {}", singleStationDTO);
        SingleStation singleStation = singleStationMapper.toEntity(singleStationDTO);
        singleStation = singleStationRepository.save(singleStation);
        return singleStationMapper.toDto(singleStation);
    }

    /**
     * Get all the singleStations.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<SingleStationDTO> findAll() {
        log.debug("Request to get all SingleStations");
        return singleStationRepository.findAll().stream().map(singleStationMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one singleStation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SingleStationDTO> findOne(Long id) {
        log.debug("Request to get SingleStation : {}", id);
        return singleStationRepository.findById(id).map(singleStationMapper::toDto);
    }

    /**
     * Delete the singleStation by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SingleStation : {}", id);
        singleStationRepository.deleteById(id);
    }
}
