package com.ugurukku.secondhand;

import com.ugurukku.secondhand.dto.UserDto;
import com.ugurukku.secondhand.models.Users;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class TestSupport {

    public static Long userId = 100L;

    public List<Users> generateUsers() {
        return IntStream.range(0, 5).mapToObj(i -> new Users(
                (long) i,
                i + "@ukku.az",
                "firstName" + i,
                "lastName" + i,
                "AZ10" + i,
                new Random(2).nextBoolean()
        )).collect(Collectors.toList());
    }

    public List<UserDto> generateUserDtoList(List<Users> usersList) {
        return
                usersList
                        .stream().map(
                                from -> new UserDto(
                                        from.getEmail(),
                                        from.getFirstName(),
                                        from.getLastName(),
                                        from.getPostCode(),
                                        from.getActive(),
                                        new ArrayList<>())).collect(Collectors.toList());

    }

    public Users generateUserInformation() {
        return new Users("ugur@com", "Ugur", "Kerimov", "AZ1010", true);
    }

    public UserDto generateUserDto(Users users) {
        return new UserDto(
                users.getEmail(),
                users.getFirstName(),
                users.getLastName(),
                users.getPostCode(),
                users.getActive(),
                new ArrayList<>());
    }

}
