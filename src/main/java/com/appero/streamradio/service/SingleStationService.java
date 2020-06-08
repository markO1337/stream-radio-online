package com.appero.streamradio.service;

import com.appero.streamradio.service.dto.SingleStationDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.appero.streamradio.domain.SingleStation}.
 */
public interface SingleStationService {
    /**
     * Save a singleStation.
     *
     * @param singleStationDTO the entity to save.
     * @return the persisted entity.
     */
    SingleStationDTO save(SingleStationDTO singleStationDTO);

    /**
     * Get all the singleStations.
     *
     * @return the list of entities.
     */
    List<SingleStationDTO> findAll();

    /**
     * Get the "id" singleStation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SingleStationDTO> findOne(Long id);

    /**
     * Delete the "id" singleStation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
