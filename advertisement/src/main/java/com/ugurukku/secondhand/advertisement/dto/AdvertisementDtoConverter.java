package com.ugurukku.secondhand.advertisement.dto;

import com.ugurukku.secondhand.advertisement.models.Advertisement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface AdvertisementDtoConverter {

    AdvertisementDtoConverter INSTANCE = Mappers.getMapper(AdvertisementDtoConverter.class);


    AdvertisementDto convertDto(Advertisement advertisement);

}
