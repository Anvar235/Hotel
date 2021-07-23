package uz.pdp.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task1.entity.Hotel;
import uz.pdp.task1.repository.HotelRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    HotelRepository hotelRepository;

    @PostMapping
    public String addHotel(@RequestBody Hotel hotel) {
        Hotel addHotel = new Hotel();
        addHotel.setName(hotel.getName());
        hotelRepository.save(addHotel);
        return "Hotel saved";
    }

    @GetMapping
    public List<Hotel> getHotel() {
        return hotelRepository.findAll();
    }

    @GetMapping("/{id}")
    public Hotel getById(@PathVariable Integer id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        return optionalHotel.orElseGet(Hotel::new);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Integer id){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()) {
            hotelRepository.deleteById(id);
            return "Hotel deleted";
        }
        return "Hotel not found";
    }

    @PutMapping("/{id}")
    public String editHotel(@PathVariable Integer id, @RequestBody Hotel hotel){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()){
            Hotel editHotel = optionalHotel.get();
            editHotel.setName(hotel.getName());
            return "Hotel edited";
        }
        return "Hotel not found";
    }
}
