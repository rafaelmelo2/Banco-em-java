package banco;
import javax.swing.JOptionPane;


public class ErrorPage {
    public void erro(String mensagem){
        JOptionPane.showMessageDialog(null, mensagem, "Alerta" , JOptionPane.ERROR_MESSAGE);
    }
    public void erro(String mensagem, String erro){
        JOptionPane.showMessageDialog(null, mensagem + erro, "Alerta" , JOptionPane.ERROR_MESSAGE);
    }
    

    public void informar(String mensagem){
        JOptionPane.showMessageDialog(null, mensagem, "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }

    public String pergunta(String mensagem){
        
        return JOptionPane.showInputDialog(null, mensagem);
    }
}
