package com.example.touristupdate.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tourists")
public class Tourist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String visitedDestination;
    private String contactDetails;

    public Tourist() {}

    public Tourist(String name, String visitedDestination, String contactDetails) {
        this.name = name;
        this.visitedDestination = visitedDestination;
        this.contactDetails = contactDetails;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getVisitedDestination() { return visitedDestination; }
    public void setVisitedDestination(String visitedDestination) { this.visitedDestination = visitedDestination; }
    public String getContactDetails() { return contactDetails; }
    public void setContactDetails(String contactDetails) { this.contactDetails = contactDetails; }
}
