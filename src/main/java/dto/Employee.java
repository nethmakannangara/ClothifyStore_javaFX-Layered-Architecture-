package dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {
    private String employeeId;
    private String name;
    private String address;
    private String contactNo;
    private String email;
    private String password;
    private String gender;
}
