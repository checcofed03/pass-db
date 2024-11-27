package entities;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EntityInterface {
    @Override
    public String toString();
    
    public EntityInterface traduci(String attributi,ResultSet risQuery)throws SQLException;
    public String insert();
    public String update(String attrModificato,String newValue,String conditions);
}
