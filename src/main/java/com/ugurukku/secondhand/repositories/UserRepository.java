package com.ugurukku.secondhand.repositories;

import com.ugurukku.secondhand.models.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInformation, Long> {


    Optional<UserInformation> findUserByEmail(String email);


}
