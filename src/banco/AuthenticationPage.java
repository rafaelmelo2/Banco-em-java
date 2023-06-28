package banco;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;


public class AuthenticationPage {
    private static final String arquivoLogin = "logins.txt";
    public boolean autenticarUsuario(String login, String senha) {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoLogin))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(";");
                String loginArmazenado = campos[1];
                
                String senhaArmazenada = campos[2];
                

                if (login.equals(loginArmazenado) && senha.equals(senhaArmazenada)) {
                    return true;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo de login: " + e.getMessage());
        }

        return false;
    }
}
