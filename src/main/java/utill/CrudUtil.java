package utill;

import db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtil {

    public static <T> T execute(String SQL,Object...obj) throws SQLException {
        PreparedStatement psTm = DBConnection.getInstance().getConnection().prepareStatement(SQL);

        for (int i = 0; i < obj.length; i++) {
            psTm.setObject(i+1,obj[i]);
        }

        if (SQL.startsWith("SELECT") || SQL.startsWith("select")){
            return (T) psTm.executeQuery();
        }else {
            return (T) (Boolean) (psTm.executeUpdate() > 0);
        }
    }
}
