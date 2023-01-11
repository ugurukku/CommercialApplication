package com.ugurukku.secondhand.services;

import com.ugurukku.secondhand.dto.AdvertisementDto;
import com.ugurukku.secondhand.dto.AdvertisementDtoConverter;
import com.ugurukku.secondhand.dto.CreateAdvertisementRequest;
import com.ugurukku.secondhand.models.Advertisement;
import com.ugurukku.secondhand.repositories.AdvertisementRepository;
import org.springframework.stereotype.Service;

@Service
public class AdvertisementService {

    private final AdvertisementRepository repository;

    private final AdvertisementDtoConverter converter;

    public AdvertisementService(AdvertisementRepository repository, AdvertisementDtoConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public AdvertisementDto createAdvertisement(CreateAdvertisementRequest request) {

        return converter
                .convertDto(
                        repository.save(new Advertisement(
                                request.title(),
                                request.description(),
                                request.price(),
                                request.userId(),
                                request.hashtags())));
    }


}
