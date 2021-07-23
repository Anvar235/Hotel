package uz.pdp.task1.payload;

import lombok.Data;
import uz.pdp.task1.entity.Hotel;

@Data
public class RoomDto {

    private String number;
    private String floor;
    private String size;
    private Integer hotelId;
}
