package server;

import java.sql.SQLException;

public interface AuthService {
    // метод получения Nickname по login и password;
    // если есть запись, возврат никнейма;
    // если отсутствует уч. запись, возврат нулл;
    String getNickNameByLoginAndPassword(String login, String password) throws SQLException, ClassNotFoundException;
    boolean registration (String login, String password, String nick) throws SQLException, ClassNotFoundException;
    boolean changeNick(String login, String password, String newNick) throws SQLException, ClassNotFoundException;
}
