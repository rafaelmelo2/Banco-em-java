package banco;
import java.util.Scanner;
import java.util.InputMismatchException;
import javax.swing.JOptionPane;

public class Principal extends BancoService{
    static boolean validaWhile;

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        
        
        while(!validaWhile){
            try{
                System.out.println("\n\n==============Seja bem vindo ao Banco-FAFAS==============\n"+
                "1. Cadastro\n"+
                "2. Login\n"+
                "3. Mostrar bancos e ids \n"+
                "4. Sair\n"+
                "\n");
                int opcao = read.nextInt();
                switch(opcao){
                    case 1:
                        System.out.println("Você escolheu a opção CADASTRO");
                        
                        Banco aux1 = new Banco();
                        aux1.cadastro(aux1);
                        break;
                    case 2:
                        System.out.println("Você escolheu a opção LOGIN");
                        LoginPage loginPage = new LoginPage();
                        loginPage.exibirPaginaLogin();
                        SessionPage sessao = new SessionPage(loginPage.getIdArmazenado(), loginPage.getLoginArmazenado(), loginPage.getSenhaArmazenada(), loginPage.getCpfArmazenado(), loginPage.getIdadeArmazenada(), loginPage.getSaldoArmazenado());
                        sessao.run(); 
                        break;
                    case 3:
                        System.out.println("Você escolheu a opção mostrar bancos!");
                        BancoService.mostrarBancos();
                        break;
                    case 4:
                        System.out.println("Você escolheu sair!");
                                   
                        validaWhile = true;
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