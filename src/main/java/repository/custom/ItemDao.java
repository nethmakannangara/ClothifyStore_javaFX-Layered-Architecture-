package repository.custom;

import dto.Item;
import entity.ItemEntity;
import repository.CrudDao;

public interface ItemDao extends CrudDao<ItemEntity> {

    void delete(String itemCode);

    int update(ItemEntity updateItem);
}
