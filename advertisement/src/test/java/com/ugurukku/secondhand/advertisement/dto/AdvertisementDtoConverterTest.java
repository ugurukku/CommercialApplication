package com.ugurukku.secondhand.advertisement.dto;


import com.ugurukku.secondhand.advertisement.models.Advertisement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

class AdvertisementDtoConverterTest {

    @Test
    public void testConvert() {

        Advertisement advertisement = new Advertisement("title",
                "description",
                123.0,
                987L,
                Set.of("hashtag1", "hashtag2"));

        AdvertisementDto advertisementDto = AdvertisementDtoConverter.INSTANCE.convertDto(advertisement);

        Assertions.assertEquals(advertisementDto.title(),advertisement.getTitle());
        Assertions.assertEquals(advertisementDto.description(),advertisement.getDescription());
        Assertions.assertEquals(advertisementDto.price(),advertisement.getPrice());
        Assertions.assertEquals(advertisementDto.userId(),advertisement.getUserId());
        Assertions.assertEquals(advertisementDto.hashtags(),advertisement.getHashtags());


    }

}