package com.example.booking_api.repository;

import com.example.booking_api.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

// Ett interface som ärver JpaRepository får färdiga metoder som
// findAll, findById, save, existsById och deleteById.
// Spring skapar själv en implementation av interfacet när appen startar.
//
// <Booking, Long> betyder:
//   Booking – vilken entitet repositoryt hanterar
//   Long    – vilken typ id:t har (samma som i Booking-klassen)
public interface BookingRepository extends JpaRepository<Booking, Long> {
}