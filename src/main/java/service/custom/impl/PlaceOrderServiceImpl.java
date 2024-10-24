package service.custom.impl;

import db.DBConnection;
import dto.CartTm;
import dto.Item;
import dto.OrderDetails;
import dto.Orders;
import entity.OrdersEntity;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.ItemDao;
import repository.custom.OrdersDao;
import repository.custom.impl.PlaceOrderDaoImpl;
import service.BoFactory;
import service.custom.ItemService;
import service.custom.PlaceOrderService;
import utill.DaoType;
import utill.ServiceType;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class PlaceOrderServiceImpl implements PlaceOrderService {

    //--------Singleton--------//
    private static PlaceOrderServiceImpl instance;

    private PlaceOrderServiceImpl() {
    }

    public static PlaceOrderServiceImpl getInstance() {
        return instance == null ? instance = new PlaceOrderServiceImpl() : instance;
    }
    //--------------------------//

    ItemService itemService = BoFactory.getInstance().getServiceType(ServiceType.ITEM);
    OrdersDao ordersDao = DaoFactory.getInstance().getDaoType(DaoType.ORDERS);
    PlaceOrderDaoImpl placeOrderDao = DaoFactory.getInstance().getDaoType(DaoType.PLACEORDER);
    ItemDao itemDao = DaoFactory.getInstance().getDaoType(DaoType.ITEM);

    double total = 0;

    @Override
    public Item loadItemInfo(String itemCode) {
        return itemService.search(itemCode);
    }

    @Override
    public String calNetTotal(ObservableList<CartTm> cartTmObservableList) {
        cartTmObservableList.forEach(cartTm -> {
            total += cartTm.getAmount();
        });
        return String.valueOf(total);
    }

    public String checkLastId() {
        String lastId = null;
        List<OrdersEntity> resultSet = ordersDao.getAll();

        if(!resultSet.isEmpty()){
            lastId = new ModelMapper().map(resultSet.getLast(),Orders.class).getOrderId();
            System.out.println("LastId: "+lastId);
        }
        return lastId;
    }

    @Override
    public String generateOrderId() {
        String orderId = "01100";
        if(checkLastId()!=null){
            Scanner id = new Scanner(checkLastId());
            id.useDelimiter("[A-Z]");
            while (id.hasNext()){
                orderId = id.next();
            }
            int orderCount = Integer.parseInt(orderId);
            return String.format("OD%05d", ++orderCount);
        }else {
            int orderCount = Integer.parseInt(orderId);
            return String.format("OD%05d", orderCount);
        }
    }

    @Override
    public void placeOrder(Orders order) {
        try {
            boolean isUpdateOrders = placeOrderDao.updateOrders(order);
            if(isUpdateOrders){
                boolean isAddOrderDetails = addOrderDetails(order.getOrderDetailsList());
                if(isAddOrderDetails){
                    boolean isUpdateItemQuantity = callUpdateItemQuantity(order.getOrderDetailsList());
                    if(isUpdateItemQuantity){
                        new Alert(Alert.AlertType.INFORMATION,"Order place successfully...").show();
                    }else {
                        new Alert(Alert.AlertType.ERROR,"Order place unsuccessful....").show();
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                DBConnection.getInstance().getConnection().setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean addOrderDetails(List<OrderDetails> orderDetailsList) throws SQLException {
        boolean isUpdateOrderDetails = false;

        for (OrderDetails orderDetail:orderDetailsList){
            isUpdateOrderDetails = placeOrderDao.updateOrderDetailTable(orderDetail);
        }

        return  isUpdateOrderDetails;
    }

    private boolean callUpdateItemQuantity(List<OrderDetails> orderDetailsList) throws SQLException {
        for (OrderDetails orderDetail: orderDetailsList){
            itemDao.updateQuaninty(orderDetail);
        }
        return false;
    }
}
