package com.example.demo.repository;

import com.example.demo.entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;



public interface FeatureRepository extends JpaRepository <Feature,Integer> {

    Feature findFeatureByEmailAndAndFeatureName (String email, String featureName);
}
