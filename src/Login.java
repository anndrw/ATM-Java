import javax.swing.*; // importam clasa JFrame si JButton
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

// Utilizam ActionListener pentru a verifica daca un utilizator este logat sau nu.
// In caz afirmativ, afisam un mesaj de confirmare.
// In caz negativ, afisam un mesaj de eroare.
// In ambele cazuri, afisam un mesaj de confirmare.
// Utilizam JFRame pentru a crea o fereastra in care sa introducem ID-ul si parola.
public class Login implements ActionListener {

    JFrame fereastra = new JFrame(); // cream un obiect JFrame -> fereastra
    JButton buton = new JButton("Login"); // cream un obiect JButton
    JButton registerbuton = new JButton("Register"); // cream un obiect JButton
    JTextField id = new JTextField(); // cream un obiect JTextField -> pentru a introduce un ID
    JLabel titlu = new JLabel("ATM Unlimited"); // cream un obiect JLabel -> pentru a introduce un titlu
    JPasswordField parola = new JPasswordField(); // cream un obiect JPasswordField -> pentru a introduce o parola
    JLabel idLabel = new JLabel("ID:"); // cream un obiect JLabel -> pentru a afisa textul "ID:"
    JLabel parolaLabel = new JLabel("Parola:"); // cream un obiect JLabel -> pentru a afisa textul "Parola:"
    JLabel mesaj = new JLabel(); // cream un obiect JLabel -> pentru a afisa un mesaj de confirmare sau de eroare
    HashMap<String, String> loginInfo = new HashMap<String, String>();

    Login(HashMap<String, String> loginInfoOrg) { // constructorul clasei Login | loginInfoOrg are rolul de a prelua informatiile de login ale utilizatorilor
        loginInfo = loginInfoOrg;

        JOptionPane.showMessageDialog(null, "Bine ai venit la ATM Unlimited!\n" +
                "Te rugam sa iti introduci contul personal. Daca nu ai inca un cont, te poti inregistra la banca noastra!\n" +
                "Trebuie sa iti aducem la cunostinta ca retragerile mai mari de 9999 de lei necesita o reconfirmare a parolei.\n" +
                "De asemenea, poti depune si retrage doar valori mai mari decat 0.\n" +
                "Suntem singura banca cu ATM-uri ce ofera imprumut pe loc! Totusi pragul limita de balanta in urma imprumutului este de 7999 lei.\n"
                + "Daca depasesti pragul o sa fii nevoit sa ii depozitezi la loc!\n" +
                "Daca depuneti o suma mai mare de 8299 lei primiti un bonus financiar din partea noastra de 5%!\n" +
                "Cand doresti sa parasesti ATM-ul poti utiliza functia de Logout!"); // afisam un mesaj de bun venit

        // Sectiune de setare a pozitiei si dimensiunilor obiectelor
        idLabel.setBounds(125, 100, 75, 25); // setam pozitia si dimensiunile obiectului idLabel
        parolaLabel.setBounds(125, 150, 75, 25); // setam pozitia si dimensiunile obiectului parolaLabel
        mesaj.setBounds(125, 250, 250, 35); // setam pozitia si dimensiunile obiectului mesaj
        mesaj.setFont(new java.awt.Font("Arial", 0, 20)); // setam fontul obiectului mesaj
        id.setBounds(200, 100, 200, 25); // setam pozitia si dimensiunile obiectului id
        parola.setBounds(200, 150, 200, 25); // setam pozitia si dimensiunile obiectului parola
        titlu.setBounds(260, 25, 400, 50); // setam pozitia si dimensiunile obiectului titlu

        buton.setBounds(250, 200, 100, 25); // setam pozitia si dimensiunile obiectului buton
        buton.addActionListener(this); // adaugam un listener pentru a verifica daca butonul a fost apasat
        buton.setFocusable(false); // setam focusul obiectului buton

        registerbuton.setBounds(250, 240, 100, 25); // Poziționăm butonul de înregistrare
        registerbuton.addActionListener(this); // Adăugăm un listener pentru butonul de înregistrare
        registerbuton.setFocusable(false); // Setăm focusul butonului de înregistrare

        // Sectiune de adaugare a obiectelor in fereastra
        fereastra.add(idLabel); // adaugam obiectul idLabel in fereastra
        fereastra.add(parolaLabel); // adaugam obiectul parolaLabel in fereastra
        fereastra.add(mesaj); // adaugam obiectul mesaj in fereastra
        fereastra.add(id); // adaugam obiectul id in fereastra
        fereastra.add(parola); // adaugam obiectul parola in fereastra
        fereastra.add(titlu); // adaugam obiectul titlu in fereastra
        fereastra.add(buton); // adaugam obiectul buton in fereastra
        fereastra.add(registerbuton); // Adăugăm butonul de înregistrare în fereastră

        // Sectiune de operatii
        fereastra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // setam operatia de inchidere a ferestrei
        fereastra.setSize(600, 400); // setam dimensiunile ferestrei
        fereastra.setLayout(null); // setam layout-ul ferestrei
        fereastra.setVisible(true); // setam vizibilitatea ferestrei, astfel incat sa o vedem
        fereastra.getContentPane().setBackground(new Color(255, 204, 0)); // setam culoarea ferestrei
    }


    public void actionPerformed(ActionEvent e) {
        System.out.println("Butonul login a fost apasat");
        if (e.getSource() == buton) { // verificam daca butonul a fost apasat
            String userID = id.getText(); // preluam ID-ul introdus de utilizator
            String parolaUtilizator = parola.getText(); // preluam parola introdusa de utilizator
            if (loginInfo.containsKey(userID) && loginInfo.get(userID).equals(parolaUtilizator)) { // verificam daca ID-ul si parola introduse de utilizator sunt corecte
                mesaj.setText("Te-ai logat!"); // afisam un mesaj de confirmare
                fereastra.dispose(); // inchidem fereastra de login
                AfterLogin afterLogin = new AfterLogin(userID, parolaUtilizator); // cream un obiect AfterLogin
            } else {
                mesaj.setText("Login esuat!"); // afisam un mesaj de eroare

            }
        } else if (e.getSource() == registerbuton) { // Verificăm dacă butonul de înregistrare a fost apăsat
            String userID = id.getText();
            String parolaUtilizator = parola.getText();
            if (!userID.isEmpty() && !parolaUtilizator.isEmpty()) {
                if (loginInfo.containsKey(userID)) {
                    mesaj.setText("Utilizatorul '" + userID + "' exista.");
                } else {
                    loginInfo.put(userID, parolaUtilizator);
                    mesaj.setText("Utilizator înregistrat!");
                }
            } else {
                mesaj.setText("Te rugăm să completezi toate câmpurile.");
            }
        }


    }

}
