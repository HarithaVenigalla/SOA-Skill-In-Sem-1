package com.example.touristupdate.service;

import com.example.touristupdate.model.Tourist;
import com.example.touristupdate.repository.TouristRepository;
import org.springframework.stereotype.Service;

@Service
public class TouristService {
    private final TouristRepository repository;

    public TouristService(TouristRepository repository) {
        this.repository = repository;
    }

    public Tourist updateTourist(Long id, Tourist updated) {
        Tourist existing = repository.findById(id)
            .orElseThrow(() -> new TouristNotFoundException("Tourist not found with id: " + id));

        existing.setName(updated.getName());
        existing.setVisitedDestination(updated.getVisitedDestination());
        existing.setContactDetails(updated.getContactDetails());
        return repository.save(existing);
    }
}
