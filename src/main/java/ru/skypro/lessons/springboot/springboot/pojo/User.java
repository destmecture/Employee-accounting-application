package ru.skypro.lessons.springboot.springboot.pojo;

import jakarta.persistence.*;
import lombok.*;
import ru.skypro.lessons.springboot.springboot.pojo.Role;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    private boolean enabled;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_id")
    private Role role;
}
