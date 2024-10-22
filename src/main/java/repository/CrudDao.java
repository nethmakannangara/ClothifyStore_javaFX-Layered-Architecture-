package repository;

public interface CrudDao<T> extends SuperDao{

    void save(T t);
}
