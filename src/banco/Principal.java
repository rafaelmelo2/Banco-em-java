package banco;
import java.util.Scanner;
import java.util.InputMismatchException;
import javax.swing.JOptionPane;

public class Principal{
    static boolean validaWhile;

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        
        
        while(!validaWhile){
            try{
                String opcao = JOptionPane.showInputDialog("\n\n==============Seja bem vindo ao Banco-FAFAS==============\n"+
                "1. Cadastro\n"+
                "2. Login\n"+
                "3. Sair\n"+
                "\n");
                
                switch(Integer.parseInt(opcao)){
                    case 1:
                        System.out.println("Você escolheu a opção CADASTRO");
                        
                        Banco aux1 = new Banco();
                        aux1.cadastro(aux1);
                        break;
                    case 2:
                        LoginPage loginPage = new LoginPage();
                        loginPage.exibirPaginaLogin();
                        SessionPage sessao = new SessionPage(loginPage.getIdArmazenado(), loginPage.getLoginArmazenado(), loginPage.getSenhaArmazenada(), loginPage.getCpfArmazenado(), loginPage.getIdadeArmazenada(), loginPage.getSaldoArmazenado());
                        sessao.start();
                        
                        break;
                    case 3:
                        validaWhile = true;                     
                        JOptionPane.showMessageDialog(null, "Obrigado por ter utilizado nosso banco!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    default:
                        System.out.println("Não foi possível executar essa escolha");
                }
            }catch(InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número válido.");
                
            }    
        }
        read.close();

    }
    
    
}