package uz.pdp.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task1.entity.Hotel;
import uz.pdp.task1.entity.Room;
import uz.pdp.task1.payload.RoomDto;
import uz.pdp.task1.repository.HotelRepository;
import uz.pdp.task1.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    HotelRepository hotelRepository;

    @PostMapping("/addRoom")
    public String addRoom(@RequestBody RoomDto roomDto) {
        Room room = new Room();
        room.setNumber(roomDto.getNumber());
        room.setFloor(roomDto.getFloor());
        room.setSize(roomDto.getSize());
        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        if (!optionalHotel.isPresent())
            return "Hotel not found";
        room.setHotel(optionalHotel.get());
        roomRepository.save(room);
        return "Room saved";
    }

    @GetMapping("/administrator")
    public Page<Room> getRoom(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return roomRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Integer id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        return optionalRoom.orElseGet(Room::new);
    }

    @DeleteMapping("/{id}")
    public String deletedRoom(@PathVariable Integer id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()) {
            roomRepository.deleteById(id);
            return "Room deleted";
        }
        return "Room not found";
    }

    @PutMapping("/{id}")
    public String editRoom(@PathVariable Integer id, @RequestBody RoomDto roomDto) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (!optionalRoom.isPresent())
            return "Room not found";
        Room editRoom = optionalRoom.get();
        editRoom.setNumber(roomDto.getNumber());
        editRoom.setFloor(roomDto.getFloor());
        editRoom.setSize(roomDto.getSize());
        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        if (!optionalHotel.isPresent())
            return "Hotel not found, at first add Hotel, after that try again";
        editRoom.setHotel(optionalHotel.get());
        roomRepository.save(editRoom);
        return "Room edited";
    }
}
