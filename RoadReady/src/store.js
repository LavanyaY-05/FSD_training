import { configureStore } from "@reduxjs/toolkit";
import { bookingReducer } from "./store/reducer/BookingReducer";

export const store = configureStore({
  reducer: {
    bookings: bookingReducer,      // registering the reducer
  }
})
