public class Main {

    public static void main(String[] args) {
        InfoLogin infoLogin = new InfoLogin();
        Login login = new Login(infoLogin.getLoginInfo());
        //AfterLogin afterLogin = new AfterLogin();
    }

}
