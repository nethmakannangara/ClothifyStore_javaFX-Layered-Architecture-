package entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminEntity {
    private String adminId;
    private String name;
    private String email;
    private String password;
}
