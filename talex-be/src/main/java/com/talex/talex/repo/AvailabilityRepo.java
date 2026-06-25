package com.talex.talex.repo;

import com.talex.talex.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailabilityRepo extends JpaRepository<Availability, Long> {
    void deleteByIdAndUser_Username(Long availabilityId, String username);
}
