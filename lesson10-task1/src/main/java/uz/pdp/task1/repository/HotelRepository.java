package uz.pdp.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task1.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
}
