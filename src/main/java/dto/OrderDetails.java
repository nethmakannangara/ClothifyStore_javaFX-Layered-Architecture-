package dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetails {
    private String orderId;
    private String itemCode;
    private String size;
    private Integer quantity;
    private Integer discount;
}
