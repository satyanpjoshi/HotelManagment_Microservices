package com.hms.booking.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class User {

    private Long userId;
    private String name;
    private String email;
    private String about;

}
