package entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SupplierEntity {
    private String supplierId;
    private String name;
    private String address;
    private String email;
    private String company;
    private String contactNO;
}
