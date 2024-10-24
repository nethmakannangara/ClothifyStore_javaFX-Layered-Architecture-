package entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
public class OrderDetailsId implements Serializable {
    private String orderId;
    private String itemCode;
}
