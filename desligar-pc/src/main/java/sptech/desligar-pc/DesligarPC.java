package sptech.destruction;

import java.io.IOException;
import javax.swing.JOptionPane;

public class DesligarPC {
   
    public static void main(String[] args) throws IOException {
        JOptionPane.showMessageDialog(null,"seu computador sera desligado"); 
        Runtime.getRuntime().exec("shutdown -s -t 120"); 
    }
}