// Utilizam un hashmap pentru a stoca informatiile de login ale utilizatorilor
// si pentru a verifica daca un utilizator este logat sau nu.
import java.util.HashMap;
public class InfoLogin {
    HashMap<String, String> loginInfo = new HashMap<String, String>();

    InfoLogin() { // folosim constructorul pentru a adauga informatiile de login ale utilizatorilor | conturi de test, predefinite
        loginInfo.put("admin", "admin");
        loginInfo.put("Andrei", "25andrei");
    }



    protected HashMap getLoginInfo() { // metoda care returneaza informatiile de login ale utilizatorilor
        return loginInfo;
    }

}
