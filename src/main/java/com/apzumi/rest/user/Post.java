package com.apzumi.rest.user;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.javafx.beans.IDProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name="Post")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {


    @Column(updatable = false)
    private String userId;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  long id;

    private String title;
    private String body;

}
