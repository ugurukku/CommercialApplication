package com.ugurukku.secondhand.repositories;

import com.ugurukku.secondhand.models.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertisementRepository extends JpaRepository<Advertisement,String> {
}
