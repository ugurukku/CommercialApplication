package com.ugurukku.secondhand.repositories;

import com.ugurukku.secondhand.models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails,Long> {


}
