package com.ugurukku.secondhand.advertisement.services;


import com.ugurukku.secondhand.advertisement.dto.AdvertisementDto;
import com.ugurukku.secondhand.advertisement.dto.AdvertisementDtoConverter;
import com.ugurukku.secondhand.advertisement.dto.CreateAdvertisementRequest;
import com.ugurukku.secondhand.advertisement.models.Advertisement;
import com.ugurukku.secondhand.advertisement.repositories.AdvertisementRepository;
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
