
import javax.swing.*;
import java.awt.*;

public class CalculadoraIMC extends JFrame {

    private JTextField campoPeso;
    private JTextField campoAltura;
    private JLabel resultado;

    public CalculadoraIMC() {
        setTitle("Calculadora de IMC");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(5, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Calculadora de IMC");
        titulo.setHorizontalAlignment(JLabel.CENTER);
        painel.add(titulo);
        painel.add(new JLabel(""));

        painel.add(new JLabel("Peso (Kg):"));
        campoPeso = new JTextField();
        painel.add(campoPeso);

        painel.add(new JLabel("Altura (Cm):"));
        campoAltura = new JTextField();
        painel.add(campoAltura);

        JButton botao = new JButton("Calcular IMC");
        painel.add(botao);

        resultado = new JLabel("Resultado:");
        painel.add(resultado);

        botao.addActionListener(e -> calcularIMC());

        add(painel);
    }

    private void calcularIMC() {
        try {
            double peso = Double.parseDouble(
                campoPeso.getText().replace(",", ".")
            );

            double altura = Double.parseDouble(
                campoAltura.getText().replace(",", ".")
            ) / 100;

            if (peso <= 0 || altura <= 0) {
                JOptionPane.showMessageDialog(this,
                    "Informe valores maiores que zero.");
                return;
            }

            double imc = peso / (altura * altura);
            String classificacao;

            if (imc < 18.5) {
                classificacao = "Abaixo do peso";
            } else if (imc < 25) {
                classificacao = "Peso normal";
            } else if (imc < 30) {
                classificacao = "Sobrepeso";
            } else if (imc < 35) {
                classificacao = "Obesidade grau I";
            } else if (imc < 40) {
                classificacao = "Obesidade grau II";
            } else {
                classificacao = "Obesidade grau III";
            }

            resultado.setText(String.format(
                "<html>IMC: %.2f<br>%s</html>",
                imc, classificacao
            ));

        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(this,
                "Digite valores válidos para peso e altura.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CalculadoraIMC().setVisible(true);
        });
    }
}


