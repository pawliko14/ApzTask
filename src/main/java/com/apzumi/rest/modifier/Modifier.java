package com.apzumi.rest.modifier;

import com.apzumi.rest.user.Post;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name="Modifier")
public class Modifier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;
    private boolean EnableToUpdate;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "id")
    Post post;

}
