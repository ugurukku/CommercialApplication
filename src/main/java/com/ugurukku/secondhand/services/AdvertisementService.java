package com.ugurukku.secondhand.services;

import com.ugurukku.secondhand.dto.AdvertisementDto;
import com.ugurukku.secondhand.dto.CreateAdvertisementRequest;
import com.ugurukku.secondhand.repositories.AdvertisementRepository;
import org.springframework.stereotype.Service;

@Service
public class AdvertisementService {

    private final AdvertisementRepository repository;

    public AdvertisementService(AdvertisementRepository repository) {
        this.repository = repository;
    }

//    public AdvertisementDto createAdvertisement(CreateAdvertisementRequest request){

//    }


}
