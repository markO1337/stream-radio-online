package com.appero.streamradio.repository;

import com.appero.streamradio.domain.SingleStation;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SingleStation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SingleStationRepository extends JpaRepository<SingleStation, Long> {
    @Query("select singleStation from SingleStation singleStation where singleStation.user.login = ?#{principal.username}")
    List<SingleStation> findByUserIsCurrentUser();
}
