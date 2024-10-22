package service.custom.impl;

import dto.Admin;
import dto.Item;
import entity.EmployeeEntity;
import entity.ItemEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.ItemDao;
import service.custom.ItemService;
import utill.DaoType;

import java.util.List;
import java.util.Scanner;

public class ItemServiceImpl implements ItemService {

    //--------Singleton--------//
    private static ItemServiceImpl instance;

    private ItemServiceImpl(){}

    public static ItemServiceImpl getInstance(){
        return instance==null?instance=new ItemServiceImpl():instance;
    }
    //--------------------------//

    ItemDao itemDao = DaoFactory.getInstance().getDaoType(DaoType.ITEM);


    @Override
    public ObservableList<Item> getAll() {
        List<ItemEntity> all = itemDao.getAll();
        ObservableList<Item> itemList = FXCollections.observableArrayList();

        all.forEach(itemEntity -> {
            itemList.add(new ModelMapper().map(itemEntity,Item.class));
        });
        return itemList;
    }

    @Override
    public void add(Item item) {
        itemDao.save(new ModelMapper().map(item, ItemEntity.class));
    }


    @Override
    public void delete(String itemCode) {
        itemDao.delete(itemCode);
    }

    @Override
    public void update(Item updateItem) {
        int update = itemDao.update(new ModelMapper().map(updateItem, ItemEntity.class));
        if (update>0){
            new Alert(Alert.AlertType.INFORMATION,"Item update successfully...").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Item not update!!!").show();
        }
    }

    @Override
    public Item search(String itemCode) {
        return null;
    }

    public String checkLastId() {
        String lastId = null;
        List<ItemEntity> resultSet = itemDao.getAll();

        if(!resultSet.isEmpty()){
            lastId = new ModelMapper().map(resultSet.getLast(), Item.class).getItemCode();
        }

        return lastId;
    }

    public String generateId() {
        String itemId = "1";
        if(checkLastId()!=null){
            Scanner id = new Scanner(checkLastId());
            id.useDelimiter("[A-Z]");
            while (id.hasNext()){
                itemId = id.next();
            }
            int adminCount = Integer.parseInt(itemId);
            return String.format("ITEM%03d", ++adminCount);
        }else {
            int adminCount = Integer.parseInt(itemId);
            return String.format("ITEM%03d", adminCount);
        }
    }
}
