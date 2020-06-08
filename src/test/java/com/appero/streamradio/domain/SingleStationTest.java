package com.appero.streamradio.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.appero.streamradio.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class SingleStationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SingleStation.class);
        SingleStation singleStation1 = new SingleStation();
        singleStation1.setId(1L);
        SingleStation singleStation2 = new SingleStation();
        singleStation2.setId(singleStation1.getId());
        assertThat(singleStation1).isEqualTo(singleStation2);
        singleStation2.setId(2L);
        assertThat(singleStation1).isNotEqualTo(singleStation2);
        singleStation1.setId(null);
        assertThat(singleStation1).isNotEqualTo(singleStation2);
    }
}
