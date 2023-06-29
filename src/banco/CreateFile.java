import java.io.File;
import java.io.IOException;

public class CreateFile {
    private static final String pastaLogins = "Banco/src/logins";
    private static final String arquivoLogin = pastaLogins + "/logins.txt";

    public void createIfNotExists() {
        File file = new File(arquivoLogin);
        File parentDir = file.getParentFile();

        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Arquivo criado em: " + arquivoLogin);
            } catch (IOException e) {
                System.out.println("Erro ao criar o arquivo: " + e.getMessage());
            }
        }
    }

    public static String getArquivoLoginPath() {
        return arquivoLogin;
    }
}
