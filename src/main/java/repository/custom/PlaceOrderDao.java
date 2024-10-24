package repository.custom;

import dto.OrderDetails;
import dto.Orders;
import repository.SuperDao;

import java.sql.SQLException;
import java.util.List;

public interface PlaceOrderDao extends SuperDao {

    boolean updateOrders(Orders order) throws SQLException;

}
