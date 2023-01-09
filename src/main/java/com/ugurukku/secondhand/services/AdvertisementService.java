package com.ugurukku.secondhand.services;

import com.ugurukku.secondhand.repositories.AdvertisementElasticSearchRepository;

public class AdvertisementService {

    private final AdvertisementElasticSearchRepository repository;

    public AdvertisementService(AdvertisementElasticSearchRepository repository) {
        this.repository = repository;
    }



}
