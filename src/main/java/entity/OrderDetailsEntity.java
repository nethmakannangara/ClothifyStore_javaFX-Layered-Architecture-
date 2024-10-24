package entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table
public class OrderDetailsEntity {
    @EmbeddedId
    private OrderDetailsId id;

    private String size;
    private Integer quantity;
    private Integer discount;
}
