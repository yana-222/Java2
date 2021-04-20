package server;

public interface AuthService {
    // метод получения Nickname по login и password;
    // если есть запись, возврат никнейма;
    // если отсутствует уч. запись, возврат нулл;
    String getNickNameByLoginAndPassword(String login, String password);
    boolean registration (String login, String password, String nick);

}
