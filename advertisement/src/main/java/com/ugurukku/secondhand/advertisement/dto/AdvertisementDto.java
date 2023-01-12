package com.ugurukku.secondhand.advertisement.dto;


import java.util.Set;

public record AdvertisementDto(
        String title,
        String description,
        Double price,

        Long userId,
        Set<String> hashtags

) {
}
