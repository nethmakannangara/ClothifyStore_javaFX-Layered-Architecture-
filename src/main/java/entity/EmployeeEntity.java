package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "employee")
@Table(name = "employee")
public class EmployeeEntity {
    @Id
    private String employeeId;
    private String name;
    private String address;
    private String contactNo;
    private String email;
    private String password;
    private String gender;
}
