package ru.skypro.lessons.springboot.springboot.dto;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;
import ru.skypro.lessons.springboot.springboot.pojo.Role;
import ru.skypro.lessons.springboot.springboot.pojo.User;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class UserDTO {

    private Integer id;
    private String username;
    private String password;
    private boolean enabled;
    private Role role;

    public UserDTO fromUser(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setEnabled(user.isEnabled());
        userDTO.setRole(user.getRole());


        return userDTO;
    }
    public User toUser(){
        User user = new User();
        user.setId(this.getId());
        user.setUsername(this.getUsername());
        user.setPassword(this.getPassword());
        user.setEnabled(this.isEnabled());
        user.setRole(this.getRole());
        return user;
    }

}
