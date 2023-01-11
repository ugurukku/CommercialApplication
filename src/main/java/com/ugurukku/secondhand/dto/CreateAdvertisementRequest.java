package com.ugurukku.secondhand.dto;

import java.util.Set;

public record CreateAdvertisementRequest(
        String title,
        String description,
        Double price,
        Long userId,
        Set<String> hashtags) {


}
