package com.example.demo.controller;

import com.example.demo.entity.Feature;
import com.example.demo.exception.FeatureNotModifiedException;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.repository.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
public class FeatureController {

    @Autowired
    private FeatureRepository featureRepository;

    @PostMapping("/feature")
    ResponseEntity<Feature> createFeature(@Valid @RequestBody Feature feature) {
        Feature existingFeature = featureRepository.findFeatureByEmailAndAndFeatureName(feature.getEmail(),feature.getFeatureName());
        if(existingFeature != null) {
            throw new FeatureNotModifiedException("Record Not Modified");
        }
        else{
            featureRepository.save(feature);
            return new ResponseEntity<Feature>( HttpStatus.OK);
        }
    }

    @GetMapping("/feature")
    Map<String, Boolean> findFeatureByEmailAndFeatureName(@RequestParam String email, @RequestParam String featureName ) {

        Feature feature = featureRepository.findFeatureByEmailAndAndFeatureName(email,featureName);
        if(feature == null) {
            throw new RecordNotFoundException("Invalid record : " + email);
        }
        else{
            Map<String, Boolean> result = Collections.singletonMap("canAccess", feature.getEnable());
            return result;
        }
    }
}
