package ru.skypro.lessons.springboot.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.skypro.lessons.springboot.springboot.pojo.Position;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class PositionDTO {
    private int positionDTO_id;
    private String positionDTO_name;

    public static PositionDTO fromPosition(Position position){
        return new PositionDTO()
                .setPositionDTO_id(position.getPosition_id())
                .setPositionDTO_name(position.getPosition_name());
    }
    public Position toPosition(){
        Position position = new Position();
        position.setPosition_id(this.getPositionDTO_id());
        position.setPosition_name(this.getPositionDTO_name());
        return position;
    }
}
