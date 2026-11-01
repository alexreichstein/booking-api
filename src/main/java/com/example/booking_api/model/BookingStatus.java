package com.example.booking_api.model;

// En enum är en typ med en fast lista av tillåtna värden.
// En bokning kan bara ha en av dessa tre statusar.
public enum BookingStatus {
    // Bokningen är gjord men inte bekräftad än
    PENDING,
    // Bokningen är bekräftad
    CONFIRMED,
    // Bokningen är avbokad
    CANCELLED
}