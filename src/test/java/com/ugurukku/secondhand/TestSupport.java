package com.ugurukku.secondhand;

import com.ugurukku.secondhand.models.UserInformation;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestSupport {

    public static List<UserInformation> generateUsers() {
        return IntStream.range(0, 5).mapToObj(i -> new UserInformation(
                (long) i,
                i + "@ukku.az",
                "firstName" + i,
                "lastName" + i,
                "AZ10" + i,
                new Random(2).nextBoolean()
        )).collect(Collectors.toList());
    }

}
