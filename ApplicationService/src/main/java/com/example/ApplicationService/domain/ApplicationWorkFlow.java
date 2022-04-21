package com.example.ApplicationService.domain;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="ApplicationWorkFlow")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationWorkFlow implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "EmployeeID")
    private String employeeID;

    @Column(name = "CreateDate")
    private String createDate;

    @Column(name = "LastModificationDate")
    private String lastModificationDate;

    @Column(name = "Status")
    private String status;

    @Column(name = "Comment")
    private String comment;
}
