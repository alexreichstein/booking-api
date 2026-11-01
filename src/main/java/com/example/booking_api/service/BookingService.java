package com.example.booking_api.service;

import com.example.booking_api.model.Booking;
import com.example.booking_api.model.BookingStatus;
import com.example.booking_api.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// @Service talar om för Spring att detta är en service-klass.
// Spring skapar ett objekt av den vid start och kan skicka in det där det behövs.
@Service
public class BookingService {

    // Repositoryt som servicen använder för att prata med databasen.
    // final betyder att det sätts en gång och aldrig byts ut.
    private final BookingRepository repository;

    // Dependency injection: Spring skickar in BookingRepository automatiskt
    public BookingService(BookingRepository repository) {
        this.repository = repository;
    }

    // Hämtar alla bokningar (används av GET /bookings)
    public List<Booking> findAll() {
        return repository.findAll();
    }

    // Hämtar en bokning (används av GET /bookings/{id}).
    // Optional är tom om ingen bokning med det id:t finns.
    public Optional<Booking> findById(Long id) {
        return repository.findById(id);
    }

    // Skapar en ny bokning (används av POST /bookings)
    public Booking create(Booking booking) {
        // Id:t ska alltid sättas av databasen, aldrig av klienten.
        // Utan detta skulle en klient kunna skicka med ett id och skriva över en befintlig bokning.
        booking.setId(null);

        // Om ingen status skickas med blir bokningen PENDING
        if (booking.getStatus() == null) {
            booking.setStatus(BookingStatus.PENDING);
        }

        // save sparar bokningen och returnerar den med sitt nya id
        return repository.save(booking);
    }

    // Uppdaterar en befintlig bokning (används av PUT /bookings/{id}).
    // Returnerar en tom Optional om bokningen inte finns.
    public Optional<Booking> update(Long id, Booking updated) {
        // map körs bara om bokningen hittades.
        // existing är bokningen som redan ligger i databasen.
        return repository.findById(id).map(existing -> {
            existing.setName(updated.getName());
            existing.setEmail(updated.getEmail());
            existing.setDateTime(updated.getDateTime());
            existing.setNumberOfPeople(updated.getNumberOfPeople());

            // Behåll nuvarande status om ingen ny skickas med
            if (updated.getStatus() != null) {
                existing.setStatus(updated.getStatus());
            }

            // Eftersom existing redan har ett id blir save en uppdatering, inte en ny rad
            return repository.save(existing);
        });
    }

    // Tar bort en bokning (används av DELETE /bookings/{id}).
    // Returnerar false om bokningen inte fanns, så att controllern kan svara 404.
    public boolean delete(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}