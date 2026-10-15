package com.example.touristupdate.repository;

import com.example.touristupdate.model.Tourist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TouristRepository extends JpaRepository<Tourist, Long> {}
