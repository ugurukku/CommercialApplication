package com.ugurukku.secondhand.dto;


import java.util.Date;
import java.util.Set;

public record AdvertisementDto(
        String id,
        String title,
        String description,
        Double price,

        Date lastModified,

        Date creationDate,
        Long userId,
        Set<String> hashtags

) {
}
