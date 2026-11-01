package com.example.booking_api.controller;

import com.example.booking_api.model.Booking;
import com.example.booking_api.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

// @RestController: klassen tar emot HTTP-requests och svaren skickas som JSON
@RestController
// Alla endpoints i klassen börjar med /bookings
@RequestMapping("/bookings")
public class BookingController {

    // Servicen som innehåller logiken för bokningar
    private final BookingService service;

    // Dependency injection: Spring skickar in BookingService automatiskt
    public BookingController(BookingService service) {
        this.service = service;
    }

    // GET /bookings
    // Returnerar alla bokningar. En lista returneras direkt, och då blir statusen 200 OK.
    @GetMapping
    public List<Booking> getAll() {
        return service.findAll();
    }

    // GET /bookings/{id}
    // {id} i adressen plockas ut med @PathVariable.
    // Finns bokningen svarar vi 200 OK med bokningen, annars 404 Not Found.
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getById(@PathVariable Long id) {
        return service.findById(id)
                // Om Optional innehåller en bokning: 200 OK med bokningen
                .map(ResponseEntity::ok)
                // Om Optional är tom: 404 Not Found
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /bookings
    // @RequestBody gör om JSON i requesten till ett Booking-objekt.
    // Svarar 201 Created med den skapade bokningen.
    @PostMapping
    public ResponseEntity<Booking> create(@RequestBody Booking booking) {
        Booking saved = service.create(booking);

        // created() ger statuskod 201 och lägger till en Location-header
        // som talar om var den nya bokningen finns, t.ex. /bookings/5
        return ResponseEntity
                .created(URI.create("/bookings/" + saved.getId()))
                .body(saved);
    }

    // PUT /bookings/{id}
    // Uppdaterar bokningen med det id:t.
    // Svarar 200 OK med den uppdaterade bokningen, eller 404 om den inte finns.
    @PutMapping("/{id}")
    public ResponseEntity<Booking> update(@PathVariable Long id, @RequestBody Booking booking) {
        return service.update(id, booking)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /bookings/{id}
    // Svarar 204 No Content om bokningen togs bort, annars 404 Not Found.
    // Void betyder att svaret inte har någon body.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}