package com.example.ApplicationService.domain;


import lombok.*;
import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="DigitalDocument")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DigitalDocument implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String type;
    private Boolean isRequired;
    private String path;
    private String description;
    private String title;

}
