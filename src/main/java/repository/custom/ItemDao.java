package repository.custom;

import dto.OrderDetails;
import entity.ItemEntity;
import repository.CrudDao;

import java.sql.SQLException;

public interface ItemDao extends CrudDao<ItemEntity> {

    void delete(String itemCode);

    int update(ItemEntity updateItem);

    ItemEntity search(String itemCode);

    boolean updateQuaninty(OrderDetails orderDetail) throws SQLException;
}
