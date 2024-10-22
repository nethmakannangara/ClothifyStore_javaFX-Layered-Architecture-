package repository.custom;

import entity.AdminEntity;
import repository.CrudDao;

import java.sql.ResultSet;
import java.util.List;

public interface AdminDao extends CrudDao<AdminEntity> {
    List<AdminEntity> getAll();
}
