package com.appero.streamradio.web.rest;

import com.appero.streamradio.service.SingleStationService;
import com.appero.streamradio.service.dto.SingleStationDTO;
import com.appero.streamradio.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link com.appero.streamradio.domain.SingleStation}.
 */
@RestController
@RequestMapping("/api")
public class SingleStationResource {
    private final Logger log = LoggerFactory.getLogger(SingleStationResource.class);

    private static final String ENTITY_NAME = "singleStation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SingleStationService singleStationService;

    public SingleStationResource(SingleStationService singleStationService) {
        this.singleStationService = singleStationService;
    }

    /**
     * {@code POST  /single-stations} : Create a new singleStation.
     *
     * @param singleStationDTO the singleStationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new singleStationDTO, or with status {@code 400 (Bad Request)} if the singleStation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/single-stations")
    public ResponseEntity<SingleStationDTO> createSingleStation(@Valid @RequestBody SingleStationDTO singleStationDTO)
        throws URISyntaxException {
        log.debug("REST request to save SingleStation : {}", singleStationDTO);
        if (singleStationDTO.getId() != null) {
            throw new BadRequestAlertException("A new singleStation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SingleStationDTO result = singleStationService.save(singleStationDTO);
        return ResponseEntity
            .created(new URI("/api/single-stations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /single-stations} : Updates an existing singleStation.
     *
     * @param singleStationDTO the singleStationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated singleStationDTO,
     * or with status {@code 400 (Bad Request)} if the singleStationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the singleStationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/single-stations")
    public ResponseEntity<SingleStationDTO> updateSingleStation(@Valid @RequestBody SingleStationDTO singleStationDTO)
        throws URISyntaxException {
        log.debug("REST request to update SingleStation : {}", singleStationDTO);
        if (singleStationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SingleStationDTO result = singleStationService.save(singleStationDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, singleStationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /single-stations} : get all the singleStations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of singleStations in body.
     */
    @GetMapping("/single-stations")
    public List<SingleStationDTO> getAllSingleStations() {
        log.debug("REST request to get all SingleStations");
        return singleStationService.findAll();
    }

    /**
     * {@code GET  /single-stations/:id} : get the "id" singleStation.
     *
     * @param id the id of the singleStationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the singleStationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/single-stations/{id}")
    public ResponseEntity<SingleStationDTO> getSingleStation(@PathVariable Long id) {
        log.debug("REST request to get SingleStation : {}", id);
        Optional<SingleStationDTO> singleStationDTO = singleStationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(singleStationDTO);
    }

    /**
     * {@code DELETE  /single-stations/:id} : delete the "id" singleStation.
     *
     * @param id the id of the singleStationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/single-stations/{id}")
    public ResponseEntity<Void> deleteSingleStation(@PathVariable Long id) {
        log.debug("REST request to delete SingleStation : {}", id);
        singleStationService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
