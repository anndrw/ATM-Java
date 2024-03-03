import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AfterLogin implements ActionListener {
    private String parolaUtilizator;
    private String userID;
    private double balanta = 0;
    private double balantabonus = 0;
    JFrame fereastra = new JFrame();
    JLabel mesaj = new JLabel();
    JButton depunerebuton = new JButton("Depunere");
    JButton retragerebuton = new JButton("Retragere");
    JButton logoutbuton = new JButton("Logout");

    JButton imprumut = new JButton("Imprumut");
    JTextField sumaLabel = new JTextField();
    JLabel introduce = new JLabel("Suma: ");
    JLabel balantaLabel = new JLabel("Balanta: " + balanta);




    AfterLogin(String userID, String parolaUtilizator) {
        this.userID = userID;
        this.parolaUtilizator = parolaUtilizator;

        mesaj.setBounds(10, 5, 200, 35);
        mesaj.setFont(new java.awt.Font("Arial", Font.PLAIN, 12));
        mesaj.setText("Bine ai venit, " + userID + "!");

        balantaLabel.setBounds(270, 100, 200, 25);
        depunerebuton.setBounds(200, 150, 100, 25);
        depunerebuton.addActionListener(this);
        retragerebuton.setBounds(320, 150, 100, 25);
        retragerebuton.addActionListener(this);
        sumaLabel.setBounds(200, 200, 220, 25);
        introduce.setBounds(150, 200, 50, 25);
        logoutbuton.setBounds(250, 250, 100, 25);
        logoutbuton.addActionListener(this);
        imprumut.setBounds(250, 300, 100, 25);
        imprumut.addActionListener(this);


        fereastra.add(mesaj);
        fereastra.add(depunerebuton);
        fereastra.add(retragerebuton);
        fereastra.add(sumaLabel);
        fereastra.add(balantaLabel);
        fereastra.add(introduce);
        fereastra.add(logoutbuton);
        fereastra.add(imprumut);


        fereastra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fereastra.setSize(600, 400);
        fereastra.setLayout(null);
        fereastra.setVisible(true);
        fereastra.getContentPane().setBackground(new Color(255, 204, 0));

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == logoutbuton)
        {
            fereastra.dispose();
            JOptionPane.showMessageDialog(null, "Te-ai delogat cu succes!");
            new Login(new InfoLogin().getLoginInfo()); // deschidem fereastra de login
        }
        if(e.getSource() == imprumut)
        {
            double sumaImprumutata = Double.parseDouble(JOptionPane.showInputDialog("Introduceti suma pentru imprumut: ", null));
            if(sumaImprumutata > 0)
            {
                balanta -= sumaImprumutata;
                balantaLabel.setText("Balanța: " + balanta);
                JOptionPane.showMessageDialog(null, "Ai imprumutat " + sumaImprumutata + " lei.\n"
                + "Nu uita sa achiti suma imprumutata in 30 de zile!");
                if(balanta < -7999)
                {
                    JOptionPane.showMessageDialog(null, "Ai depasit pragul impus de noi. Va rugam sa achitati suma imprumutata.\n" +
                            "Nu puteti retrage bani pana nu achitati suma imprumutata.");
                    retragerebuton.setEnabled(false);
                    logoutbuton.setEnabled(false);
                    imprumut.setEnabled(false);

                }

            }
            else
            {
                JOptionPane.showMessageDialog(null, "Introduceți o sumă mai mare decât zero.");
            }
        }
        if (e.getSource() == depunerebuton) {
            double sumaDepusa = Double.parseDouble(sumaLabel.getText());
            if(sumaDepusa > 8299) // verificare daca suma e mai mare decat 8300
            {
                JOptionPane.showMessageDialog(null, "Suma depusa este mai mare decat 8299 lei, prin urmare o sa primiti un bonus de 5% din suma depusa. Bonusul este de: " + sumaDepusa * 0.05 + " lei.\n"
                        + "Puteti retrage bonusul dupa 72h pentru a evita abuzurile!");
               balanta += sumaDepusa + sumaDepusa * 0.05;
               balantaLabel.setText("Balanța: " + balanta);

            }
            else
            if (sumaDepusa > 0) {
                balanta += sumaDepusa;
                balantaLabel.setText("Balanța: " + balanta);
                JOptionPane.showMessageDialog(null, "Ai depus " + sumaDepusa + " lei.");
            } else {
                JOptionPane.showMessageDialog(null, "Introduceți o sumă mai mare decât zero.");
            }
            if(balanta >= 0)
            {
                retragerebuton.setEnabled(true);
                logoutbuton.setEnabled(true);
                imprumut.setEnabled(true);
            }
        } else if (e.getSource() == retragerebuton) {
            double sumaRetrasa = Double.parseDouble(sumaLabel.getText());
            if(sumaRetrasa > 9999) // verificare daca suma e mai mare decat 9999, cerem parola din nou sau anulam tranzactia
            {

                JOptionPane.showMessageDialog(null, "Suma retrasa este mai mare decat 9999 lei. Va rugam sa introduceti din nou parola");
                String parola = JOptionPane.showInputDialog("Introduceti parola: ", null);
                if(parola.equals(parolaUtilizator))
                {
                    JOptionPane.showMessageDialog(null, "Parola corecta. Suma retrasa este: " + sumaRetrasa);
                    balanta -= sumaRetrasa;
                    balantaLabel.setText("Balanța: " + balanta);
                    if(balanta < -7999)
                    {
                        JOptionPane.showMessageDialog(null, "Ai depasit pragul impus de noi. Va rugam sa achitati suma imprumutata.\n" +
                                "Nu puteti retrage bani pana nu achitati suma imprumutata.");
                        retragerebuton.setEnabled(false);
                        logoutbuton.setEnabled(false);
                        imprumut.setEnabled(false);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Parola incorecta. Va rugam sa introduceti din nou parola");
                }
            }
            else
            if (sumaRetrasa > 0) { // verificare daca suma e mai mare decat 0
                if (sumaRetrasa > balanta) {
                    JOptionPane.showMessageDialog(null, "Fonduri insuficiente.");
                } else {
                    balanta -= sumaRetrasa;
                    balantaLabel.setText("Balanța: " + balanta);
                    JOptionPane.showMessageDialog(null, "Ai retras " + sumaRetrasa + " lei.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Introduceți o sumă mai mare decât zero.");
            }

        }

    }

}

