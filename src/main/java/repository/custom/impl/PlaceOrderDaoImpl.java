package repository.custom.impl;

import db.DBConnection;
import dto.OrderDetails;
import dto.Orders;
import javafx.scene.control.Alert;
import repository.DaoFactory;
import repository.custom.ItemDao;
import repository.custom.PlaceOrderDao;
import utill.DaoType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PlaceOrderDaoImpl implements PlaceOrderDao {

    //--------Singleton---------//
    private static PlaceOrderDaoImpl instance;

    private PlaceOrderDaoImpl() {
    }

    public static PlaceOrderDaoImpl getInstance() {
        return instance == null ? instance = new PlaceOrderDaoImpl() : instance;
    }
    //-------------------------//

    //----------------------------------------------//
    ItemDao itemDao = DaoFactory.getInstance().getDaoType(DaoType.ITEM);
    //---------------------------------------------//

    public boolean updateOrders(Orders order) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String SQL = "INSERT INTO orders VALUES(?,?,?,?,?)";

        connection.setAutoCommit(false);
        PreparedStatement psTm = connection.prepareStatement(SQL);
        psTm.setObject(1, order.getOrderId());
        psTm.setObject(2, order.getEmployeeId());
        psTm.setObject(3, order.getOrderDate());
        psTm.setObject(4, order.getPaymentType());
        psTm.setObject(5, order.getAmount());

        return  psTm.executeUpdate() > 0;
    }

    public boolean updateOrderDetailTable(OrderDetails orderDetail) throws SQLException {
        String SQL = "INSERT INTO orderDetail VALUES(?,?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();

        PreparedStatement psTm = connection.prepareStatement(SQL);
        psTm.setObject(1, orderDetail.getOrderId());
        psTm.setObject(2, orderDetail.getItemCode());
        psTm.setObject(3, orderDetail.getSize());
        psTm.setObject(4, orderDetail.getQuantity());
        psTm.setObject(5, orderDetail.getDiscount());

        return psTm.executeUpdate() > 0;
    }

}
