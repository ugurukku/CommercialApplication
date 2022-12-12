package com.ugurukku.secondhand.repositories;

import com.ugurukku.secondhand.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
