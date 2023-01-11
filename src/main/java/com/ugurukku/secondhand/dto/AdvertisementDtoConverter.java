package com.ugurukku.secondhand.dto;

import com.ugurukku.secondhand.models.Advertisement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdvertisementDtoConverter {

    AdvertisementDtoConverter INSTANCE = Mappers.getMapper(AdvertisementDtoConverter.class);


    AdvertisementDto convertDto(Advertisement advertisement);

}
