package com.santyman.hermes.repositories;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.santyman.hermes.entities.Booking;
import com.santyman.hermes.entities.BookingStatus;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    

    public List<Booking> findAll();

    Optional<Booking> findByPaymentToken(String paymenToken);

    Optional<Booking> findByReferenceCode(String referenceCode);

    @Query("SELECT booking FROM Booking booking WHERE booking.status=?1")
    public List<Booking> findByStatus(@Param("status") BookingStatus status);

    @Query("SELECT booking FROM Booking booking WHERE booking.user.email=?1")
    public List<Booking> finByUser(String userName);

    @Query("SELECT booking FROM Booking booking WHERE booking.user.id=?1")
    public Set<Booking> findByUserId(Long id);

    @Query("SELECT booking FROM Booking booking WHERE booking.status=?1 AND booking.user.email=?2")
    public List<Booking> finByUserAndStatus(@Param("status") BookingStatus status, String email);

    @Query("SELECT booking FROM Booking booking WHERE booking.flight.id=?1")
    public Set<Booking> findByFlight(Long id);
}
