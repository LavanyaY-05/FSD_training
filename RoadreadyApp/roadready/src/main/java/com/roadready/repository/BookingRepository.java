package com.roadready.repository;

import com.roadready.dto.StatDto;
import com.roadready.enums.BookingStatus;
import com.roadready.model.Agent;
import com.roadready.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {


    @Query("""
            select b from Booking b where b.customer.user.username = ?1
            and(?2 is NULL or b.bookingStatus = ?2)
            """)
    List<Booking> findBookings(String username, BookingStatus status);

    @Query("""
            select b.bookingStatus as name , count(b.id) as total
            from Booking b
            group by b.bookingStatus
            order by b.updatedAt DESC
            """)
    List<StatDto> getStats();


    @Query("""
            
            select b from Booking b 
            where b.agent is null  AND b.bookingStatus IN ('CONFIRMED')
            and b.pickupDateTime >= ?1
            
            """)
    Page<Booking> findUnassignedBookings(LocalDateTime today, Pageable pageable);


    @Query("""
                SELECT a FROM Agent a 
                WHERE a.location = (
                    SELECT b.car.location FROM Booking b WHERE b.id = ?1
                )
            """)
    List<Agent> findAgents(int bookingId);

    @Query("""
            select b from Booking b
            where b.agent.user.username =?2
            and b.bookingStatus = 'CONFIRMED'
            and b.pickupDateTime >= ?1
            order by b.pickupDateTime ASC
            """)
    Page<Booking> getPreRent(LocalDateTime today, String name, Pageable pageable);


    @Query("""
            select b from Booking b
            where b.agent.user.username =?2
            and b.bookingStatus = 'ACTIVE'
            and b.dropDownDateTime>= ?1
            order by b.dropDownDateTime ASC
            """)
    Page<Booking> getPostRent(LocalDateTime today, String name, Pageable pageable);

    @Query("""
            select b.agent.user.username as name , count(b.id) as total
            from Booking b
            group by b.agent.id
            """)
    List<StatDto> getAgentStats();

}
