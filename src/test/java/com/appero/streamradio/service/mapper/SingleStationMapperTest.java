package com.appero.streamradio.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SingleStationMapperTest {
    private SingleStationMapper singleStationMapper;

    @BeforeEach
    public void setUp() {
        singleStationMapper = new SingleStationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(singleStationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(singleStationMapper.fromId(null)).isNull();
    }
}
