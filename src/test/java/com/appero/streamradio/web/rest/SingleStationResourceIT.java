package com.appero.streamradio.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.appero.streamradio.StreamRadioOnlineApp;
import com.appero.streamradio.domain.SingleStation;
import com.appero.streamradio.domain.User;
import com.appero.streamradio.repository.SingleStationRepository;
import com.appero.streamradio.service.SingleStationService;
import com.appero.streamradio.service.dto.SingleStationDTO;
import com.appero.streamradio.service.mapper.SingleStationMapper;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link SingleStationResource} REST controller.
 */
@SpringBootTest(classes = StreamRadioOnlineApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SingleStationResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final String DEFAULT_LICENSE = "AAAAAAAAAA";
    private static final String UPDATED_LICENSE = "BBBBBBBBBB";

    @Autowired
    private SingleStationRepository singleStationRepository;

    @Autowired
    private SingleStationMapper singleStationMapper;

    @Autowired
    private SingleStationService singleStationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSingleStationMockMvc;

    private SingleStation singleStation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SingleStation createEntity(EntityManager em) {
        SingleStation singleStation = new SingleStation().name(DEFAULT_NAME).url(DEFAULT_URL).license(DEFAULT_LICENSE);
        // Add required entity
        User user = UserResourceIT.createEntity(em);
        em.persist(user);
        em.flush();
        singleStation.setUser(user);
        return singleStation;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SingleStation createUpdatedEntity(EntityManager em) {
        SingleStation singleStation = new SingleStation().name(UPDATED_NAME).url(UPDATED_URL).license(UPDATED_LICENSE);
        // Add required entity
        User user = UserResourceIT.createEntity(em);
        em.persist(user);
        em.flush();
        singleStation.setUser(user);
        return singleStation;
    }

    @BeforeEach
    public void initTest() {
        singleStation = createEntity(em);
    }

    @Test
    @Transactional
    public void createSingleStation() throws Exception {
        int databaseSizeBeforeCreate = singleStationRepository.findAll().size();
        // Create the SingleStation
        SingleStationDTO singleStationDTO = singleStationMapper.toDto(singleStation);
        restSingleStationMockMvc
            .perform(
                post("/api/single-stations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(singleStationDTO))
            )
            .andExpect(status().isCreated());

        // Validate the SingleStation in the database
        List<SingleStation> singleStationList = singleStationRepository.findAll();
        assertThat(singleStationList).hasSize(databaseSizeBeforeCreate + 1);
        SingleStation testSingleStation = singleStationList.get(singleStationList.size() - 1);
        assertThat(testSingleStation.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSingleStation.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testSingleStation.getLicense()).isEqualTo(DEFAULT_LICENSE);
    }

    @Test
    @Transactional
    public void createSingleStationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = singleStationRepository.findAll().size();

        // Create the SingleStation with an existing ID
        singleStation.setId(1L);
        SingleStationDTO singleStationDTO = singleStationMapper.toDto(singleStation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSingleStationMockMvc
            .perform(
                post("/api/single-stations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(singleStationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SingleStation in the database
        List<SingleStation> singleStationList = singleStationRepository.findAll();
        assertThat(singleStationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = singleStationRepository.findAll().size();
        // set the field null
        singleStation.setName(null);

        // Create the SingleStation, which fails.
        SingleStationDTO singleStationDTO = singleStationMapper.toDto(singleStation);

        restSingleStationMockMvc
            .perform(
                post("/api/single-stations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(singleStationDTO))
            )
            .andExpect(status().isBadRequest());

        List<SingleStation> singleStationList = singleStationRepository.findAll();
        assertThat(singleStationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUrlIsRequired() throws Exception {
        int databaseSizeBeforeTest = singleStationRepository.findAll().size();
        // set the field null
        singleStation.setUrl(null);

        // Create the SingleStation, which fails.
        SingleStationDTO singleStationDTO = singleStationMapper.toDto(singleStation);

        restSingleStationMockMvc
            .perform(
                post("/api/single-stations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(singleStationDTO))
            )
            .andExpect(status().isBadRequest());

        List<SingleStation> singleStationList = singleStationRepository.findAll();
        assertThat(singleStationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLicenseIsRequired() throws Exception {
        int databaseSizeBeforeTest = singleStationRepository.findAll().size();
        // set the field null
        singleStation.setLicense(null);

        // Create the SingleStation, which fails.
        SingleStationDTO singleStationDTO = singleStationMapper.toDto(singleStation);

        restSingleStationMockMvc
            .perform(
                post("/api/single-stations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(singleStationDTO))
            )
            .andExpect(status().isBadRequest());

        List<SingleStation> singleStationList = singleStationRepository.findAll();
        assertThat(singleStationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSingleStations() throws Exception {
        // Initialize the database
        singleStationRepository.saveAndFlush(singleStation);

        // Get all the singleStationList
        restSingleStationMockMvc
            .perform(get("/api/single-stations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(singleStation.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)))
            .andExpect(jsonPath("$.[*].license").value(hasItem(DEFAULT_LICENSE)));
    }

    @Test
    @Transactional
    public void getSingleStation() throws Exception {
        // Initialize the database
        singleStationRepository.saveAndFlush(singleStation);

        // Get the singleStation
        restSingleStationMockMvc
            .perform(get("/api/single-stations/{id}", singleStation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(singleStation.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL))
            .andExpect(jsonPath("$.license").value(DEFAULT_LICENSE));
    }

    @Test
    @Transactional
    public void getNonExistingSingleStation() throws Exception {
        // Get the singleStation
        restSingleStationMockMvc.perform(get("/api/single-stations/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSingleStation() throws Exception {
        // Initialize the database
        singleStationRepository.saveAndFlush(singleStation);

        int databaseSizeBeforeUpdate = singleStationRepository.findAll().size();

        // Update the singleStation
        SingleStation updatedSingleStation = singleStationRepository.findById(singleStation.getId()).get();
        // Disconnect from session so that the updates on updatedSingleStation are not directly saved in db
        em.detach(updatedSingleStation);
        updatedSingleStation.name(UPDATED_NAME).url(UPDATED_URL).license(UPDATED_LICENSE);
        SingleStationDTO singleStationDTO = singleStationMapper.toDto(updatedSingleStation);

        restSingleStationMockMvc
            .perform(
                put("/api/single-stations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(singleStationDTO))
            )
            .andExpect(status().isOk());

        // Validate the SingleStation in the database
        List<SingleStation> singleStationList = singleStationRepository.findAll();
        assertThat(singleStationList).hasSize(databaseSizeBeforeUpdate);
        SingleStation testSingleStation = singleStationList.get(singleStationList.size() - 1);
        assertThat(testSingleStation.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSingleStation.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testSingleStation.getLicense()).isEqualTo(UPDATED_LICENSE);
    }

    @Test
    @Transactional
    public void updateNonExistingSingleStation() throws Exception {
        int databaseSizeBeforeUpdate = singleStationRepository.findAll().size();

        // Create the SingleStation
        SingleStationDTO singleStationDTO = singleStationMapper.toDto(singleStation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSingleStationMockMvc
            .perform(
                put("/api/single-stations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(singleStationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SingleStation in the database
        List<SingleStation> singleStationList = singleStationRepository.findAll();
        assertThat(singleStationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSingleStation() throws Exception {
        // Initialize the database
        singleStationRepository.saveAndFlush(singleStation);

        int databaseSizeBeforeDelete = singleStationRepository.findAll().size();

        // Delete the singleStation
        restSingleStationMockMvc
            .perform(delete("/api/single-stations/{id}", singleStation.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SingleStation> singleStationList = singleStationRepository.findAll();
        assertThat(singleStationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
