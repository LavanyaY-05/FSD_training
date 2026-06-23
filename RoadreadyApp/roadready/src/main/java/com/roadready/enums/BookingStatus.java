package com.roadready.enums;

public enum BookingStatus {

        PENDING,    // booking created, payment not done
        CONFIRMED,  // payment done
        ACTIVE,     // car picked up
        COMPLETED,  // car returned
        CANCELLED   // cancelled by customer or admin

}
