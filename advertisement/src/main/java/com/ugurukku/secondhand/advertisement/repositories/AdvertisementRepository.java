package com.ugurukku.secondhand.advertisement.repositories;


import com.ugurukku.secondhand.advertisement.models.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "advertisementRepository")
public interface AdvertisementRepository extends JpaRepository<Advertisement,String> {
}
