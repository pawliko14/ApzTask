package com.apzumi.rest.modifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ModifierService {

    @Autowired
    ModifierRepository modifierRepository;


    public Modifier save(Modifier modifier) {
        return  modifierRepository.save(modifier);
    }




}
