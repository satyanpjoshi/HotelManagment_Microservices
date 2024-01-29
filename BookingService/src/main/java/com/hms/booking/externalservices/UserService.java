package com.hms.booking.externalservices;


import com.hms.booking.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
@FeignClient(name = "USER-SERVICE")
public interface UserService {


    @GetMapping("/users/{userId}")
    Optional<User> getUser(@PathVariable("userId") Long userId);

}
