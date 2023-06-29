import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class BancoCadastroPanel extends JPanel {
    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField idadeField;
    private JPasswordField senhaField;
    private Banco banco;

    public BancoCadastroPanel() {
        setLayout(new GridLayout(5, 2));

        JLabel nomeLabel = new JLabel("Nome:");
        nomeField = new JTextField();

        JLabel cpfLabel = new JLabel("CPF:");
        cpfField = new JTextField();

        JLabel idadeLabel = new JLabel("Idade:");
        idadeField = new JTextField();

        JLabel senhaLabel = new JLabel("Senha:");
        senhaField = new JPasswordField();

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrar();
            }
        });

        add(nomeLabel);
        add(nomeField);
        add(cpfLabel);
        add(cpfField);
        add(idadeLabel);
        add(idadeField);
        add(senhaLabel);
        add(senhaField);
        add(new JLabel()); // Espaçamento vazio
        add(cadastrarButton);

        banco = new Banco(); // Cria uma instância da classe Banco
    }

    private void cadastrar() {
        String nome = nomeField.getText();
        String cpf = cpfField.getText();
        int idade = Integer.parseInt(idadeField.getText());
        String senha = new String(senhaField.getPassword());

        banco.setNome(nome);
        banco.setCpf(cpf);
        banco.setIdade(idade);
        banco.setSenha(senha);

        JOptionPane.showMessageDialog(this, "Cadastro realizado:\n" + banco.toString());
    }

}
