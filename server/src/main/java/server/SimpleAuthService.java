/*package server;

import java.util.ArrayList;
import java.util.List;

public class SimpleAuthService implements AuthService {
    @Override
    public String getNickNameByLoginAndPassword(String login, String password) {
        for(UserData u : users){
            if (u.login.equals(login) && u.password.equals(password)) return u.nickName;
        }
        return null;
    }

    @Override
    public boolean registration(String login, String password, String nick) {
        for(UserData u : users){
            if (u.login.equals(login) || u.nickName.equals(nick)) {
                return false;}
        }
        users.add(new UserData(login,password,nick));
       /* for (UserData u : users) {
            if(u.nickName.equals(nick)){
                System.out.println(u.login +"/"+u.password+"/"+u.nickName );}
        }*/
  /*      return true;
    }

    public class UserData{
        String login;
        String password;
        String nickName;

        public UserData(String login, String password, String nickName) {
            this.login = login;
            this.password = password;
            this.nickName = nickName;
        }
    }

    private List<UserData> users;

    public SimpleAuthService(){
        users = new ArrayList<>();
        users.add(new UserData("qwe","qwe","qwe"));
        users.add(new UserData("asd","asd","asd"));
        users.add(new UserData("zxc","zxc","zxc"));
        users.add(new UserData("KOtE","KOtE","KOtE"));
        for (int i=0;i<10;i++){
            users.add(new UserData("xx"+i,"xx"+i,"xx"+i)) ;
        }
    }
}*/
