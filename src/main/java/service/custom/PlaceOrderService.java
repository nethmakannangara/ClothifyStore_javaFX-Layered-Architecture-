package service.custom;

import dto.CartTm;
import dto.Item;
import dto.Orders;
import javafx.collections.ObservableList;
import service.SuperFactory;

public interface PlaceOrderService extends SuperFactory {
    Item loadItemInfo(String itemCode);

    String calNetTotal(ObservableList<CartTm> cartTmObservableList);

    String checkLastId();

    String generateOrderId();

    void placeOrder(Orders order);
}
