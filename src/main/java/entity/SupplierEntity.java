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
@Entity(name = "supplier")
@Table(name = "supplier")
public class SupplierEntity {
    @Id
    private String supplierId;
    private String name;
    private String address;
    private String email;
    private String company;
    private String contactNO;
}
