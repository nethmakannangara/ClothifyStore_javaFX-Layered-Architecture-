package repository;

import entity.AdminEntity;

import java.util.List;

public interface CrudDao<T> extends SuperDao{

    void save(T t);

    List<T> getAll();
}
