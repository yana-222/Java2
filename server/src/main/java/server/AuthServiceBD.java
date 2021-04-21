package server;
// 1. Добавить в сетевой чат авторизацию через базу данных SQLite.
// 2. *Добавить в сетевой чат возможность смены ника.

import java.sql.*;

public class AuthServiceBD implements AuthService{
    private static Connection connection;
    private static Statement stmt;

    public AuthServiceBD() {
    }
    @Override
    public boolean changeNick(String login, String password, String newNick) throws SQLException, ClassNotFoundException {
        String str = "SELECT * FROM users";
        connect();
        ResultSet rs = stmt.executeQuery(str);
        while (rs.next()){
            if (getNickNameByLoginAndPassword(login,password) ==null || rs.getString(1).equals(newNick)) {
                rs.close();
                disconnect();
                return false;
            }
        }
        rs.close();
        String str1 = "UPDATE users SET nick = '" + newNick + "' WHERE login = '" + login +"' AND password = '" +password+"';";
        stmt.executeUpdate(str1);
        disconnect();
        return true;
    }

    @Override
    public String getNickNameByLoginAndPassword(String login, String password) throws SQLException, ClassNotFoundException {
        connect();
        String str = "SELECT * FROM users WHERE login ='" + login + "';";
        ResultSet rs = stmt.executeQuery(str);
        while (rs.next()){
            if (rs.getString(2).equals(login) && rs.getString(3).equals(password)){
                System.out.println(rs.getString(1));
                String str2 =  rs.getString(1);
                rs.close();
                disconnect();
                return str2;
            }
        }
        rs.close();
        disconnect();
        return null;
    }

    @Override
    public boolean registration(String nick, String login, String password) throws SQLException, ClassNotFoundException {
        String str = "SELECT * FROM users";
        connect();
        ResultSet rs = stmt.executeQuery(str);
        while (rs.next()){
            if (rs.getString(1).equals(nick) || rs.getString(2).equals(login)) {
              rs.close();
              disconnect();
              return false;
          }
        }
        rs.close();
        String str1 = "INSERT INTO users (nick,login,password) VALUES ('" + nick+ "','" + login+ "','" + password + "');";
        stmt.executeUpdate(str1);
        disconnect();
        return true;
    }

    private static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:users.db");
        stmt = connection.createStatement();

    }
    private static void disconnect(){
        try {
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
