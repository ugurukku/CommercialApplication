package com.ugurukku.secondhand.user.repositories;

import com.ugurukku.secondhand.user.models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails,Long> {


}
