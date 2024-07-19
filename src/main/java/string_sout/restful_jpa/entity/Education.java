package string_sout.restful_jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "educations")
public class Education {

    @Id
    private String id;

    private String school;

    private String major;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    private Double gpa;

    @Column(name = "description")
    private String desc;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName ="id")
    private User user;
}
