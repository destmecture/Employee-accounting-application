package ru.skypro.lessons.springboot.springboot.pojo;

import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "employee")

public class Employee {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer salary;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "position_id")
    private Position position;

}
