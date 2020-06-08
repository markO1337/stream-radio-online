package com.appero.streamradio.service.mapper;

import com.appero.streamradio.domain.*;
import com.appero.streamradio.service.dto.SingleStationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SingleStation} and its DTO {@link SingleStationDTO}.
 */
@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface SingleStationMapper extends EntityMapper<SingleStationDTO, SingleStation> {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    SingleStationDTO toDto(SingleStation singleStation);

    @Mapping(source = "userId", target = "user")
    SingleStation toEntity(SingleStationDTO singleStationDTO);

    default SingleStation fromId(Long id) {
        if (id == null) {
            return null;
        }
        SingleStation singleStation = new SingleStation();
        singleStation.setId(id);
        return singleStation;
    }
}
