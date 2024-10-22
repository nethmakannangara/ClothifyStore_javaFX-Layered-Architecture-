package dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Admin {
    private String adminId;
    private String name;
    private String email;
    private String password;
}
