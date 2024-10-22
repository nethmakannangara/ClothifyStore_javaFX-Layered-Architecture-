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
@Entity(name = "admin")
@Table(name = "admin")
public class AdminEntity {
    @Id
    private String adminId;
    private String name;
    private String email;
    private String password;
}
