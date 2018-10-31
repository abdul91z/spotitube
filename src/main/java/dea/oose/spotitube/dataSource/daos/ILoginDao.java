package dea.oose.spotitube.dataSource.daos;



import java.sql.ResultSet;

public interface ILoginDao {

    public String getFirstName(String username);
    public String getToken(String username);
    public String getLastName(String username);
    ResultSet checkUsernamePassword(String username,String password);
    public void setNewToken(String userName, String token);

}
