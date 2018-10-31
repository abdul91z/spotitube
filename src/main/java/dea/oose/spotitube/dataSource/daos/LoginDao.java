package dea.oose.spotitube.dataSource.daos;


import dea.oose.spotitube.dataSource.DatabaseConnection;


import java.sql.*;


public class LoginDao extends DatabaseConnection implements ILoginDao {


    private Connection connection = getConnection();
    private PreparedStatement login;
    private PreparedStatement getAllData;
    private PreparedStatement newToken;


    //checkUsernameAndPassword
    //////////////////////////////////////////////////////////////////////////
    @Override
    public ResultSet checkUsernamePassword(String username, String password) {
        ResultSet rs = null;
        try {
            checkUsernamePasswordstatemente();

            login.setString(1, username);
            login.setString(2, password);
            rs = login.executeQuery();

        } catch (SQLException e) {
            System.out.println("Fout hierzo");
            e.printStackTrace();

        }
        return rs;
    }


    public void checkUsernamePasswordstatemente() {
        try {
            login = connection.prepareStatement("SELECT * FROM users where user = ? and pass = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    ///////////////////////////////////////////////////////////////////////


    private ResultSet getAlldataResult(String username) {
        ResultSet rs = null;
        try {

            getAllDataStatement();

            getAllData.setString(1, username);

            rs = getAllData.executeQuery();

            rs.first();

        } catch (SQLException e) {
            System.out.println("Fout hierzo");
            e.printStackTrace();

        }
        return rs;
    }

    public void getAllDataStatement() {
        try {
            getAllData = connection.prepareStatement("SELECT * FROM users where user = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getFirstName(String username) {
        try {
            return getAlldataResult(username).getString("firstname");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getToken(String username) {
        try {
            return getAlldataResult(username).getString("token");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getLastName(String username) {
        try {
            return getAlldataResult(username).getString("lastname");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setNewToken(String userName, String token) {
        try {
            newToken = connection.prepareStatement("UPDATE users SET token= ? WHERE user= ?");
            newToken.setString(1, token);
            newToken.setString(2, userName);
            newToken.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
