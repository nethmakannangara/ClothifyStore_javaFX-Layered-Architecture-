package service.custom;

import dto.Item;
import javafx.collections.ObservableList;
import service.SuperFactory;

import java.util.List;

public interface ItemService extends SuperFactory {

    ObservableList<Item> getAll();

    void add(Item item);

    String generateId();

    void delete(String itemCode);

    void update(Item updateItem);

    Item search(String itemCode);

    String checkLastId();
}
