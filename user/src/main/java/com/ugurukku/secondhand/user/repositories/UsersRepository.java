package com.ugurukku.secondhand.user.repositories;

import com.ugurukku.secondhand.user.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {


    Optional<Users> findUserByEmail(String email);


}
