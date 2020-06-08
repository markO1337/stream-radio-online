package com.appero.streamradio.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.appero.streamradio.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class SingleStationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SingleStationDTO.class);
        SingleStationDTO singleStationDTO1 = new SingleStationDTO();
        singleStationDTO1.setId(1L);
        SingleStationDTO singleStationDTO2 = new SingleStationDTO();
        assertThat(singleStationDTO1).isNotEqualTo(singleStationDTO2);
        singleStationDTO2.setId(singleStationDTO1.getId());
        assertThat(singleStationDTO1).isEqualTo(singleStationDTO2);
        singleStationDTO2.setId(2L);
        assertThat(singleStationDTO1).isNotEqualTo(singleStationDTO2);
        singleStationDTO1.setId(null);
        assertThat(singleStationDTO1).isNotEqualTo(singleStationDTO2);
    }
}
