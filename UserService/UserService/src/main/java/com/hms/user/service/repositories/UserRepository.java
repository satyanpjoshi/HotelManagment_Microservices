package com.hms.user.service.repositories;

import com.hms.user.service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
    //if you want to implement any custom method or query
    //write
    
}
