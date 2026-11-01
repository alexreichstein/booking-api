package com.example.booking_api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

// @Entity talar om för JPA att den här klassen ska bli en tabell i databasen
@Entity
// @Table bestämmer tabellens namn, annars skulle den heta "booking"
@Table(name = "bookings")
public class Booking {

    // @Id markerar primärnyckeln, alltså det unika id:t för varje rad
    // @GeneratedValue gör att databasen själv räknar upp id:t (1, 2, 3...)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // nullable = false betyder att kolumnen inte får vara tom i databasen
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    // LocalDateTime innehåller både datum och tid, t.ex. 2026-11-15T18:30
    // JPA gör om fältnamnet dateTime till kolumnen date_time automatiskt
    @Column(nullable = false)
    private LocalDateTime dateTime;

    // Blir kolumnen number_of_people
    @Column(nullable = false)
    private int numberOfPeople;

    // EnumType.STRING sparar statusen som text ("PENDING") i databasen.
    // Utan detta sparas den som en siffra (0, 1, 2), vilket är svårt att läsa.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status;

    // JPA kräver en tom konstruktor för att kunna skapa objekt
    // när den läser rader från databasen
    public Booking() {
    }

    // Getters och setters – används av JPA för att läsa och sätta värdena,
    // och av Spring för att göra om objektet till och från JSON
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }

    public int getNumberOfPeople() { return numberOfPeople; }
    public void setNumberOfPeople(int numberOfPeople) { this.numberOfPeople = numberOfPeople; }

    public BookingStatus getStatus() { return status; }
    public void setStatus(BookingStatus status) { this.status = status; }
}