package com.ugurukku.secondhand;

import com.ugurukku.secondhand.dto.UserDto;
import com.ugurukku.secondhand.models.UserInformation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class TestSupport {

    public  List<UserInformation> generateUsers() {
        return IntStream.range(0, 5).mapToObj(i -> new UserInformation(
                (long) i,
                i + "@ukku.az",
                "firstName" + i,
                "lastName" + i,
                "AZ10" + i,
                new Random(2).nextBoolean()
        )).collect(Collectors.toList());
    }

    public  List<UserDto> generateUserDtoList(List<UserInformation> userInformationList) {
        return
                userInformationList
                        .stream().map(
                                from -> new UserDto(
                                        from.getEmail(),
                                        from.getFirstName(),
                                        from.getLastName(),
                                        from.getPostCode(),
                                        from.getActive())).collect(Collectors.toList());

    }

}
