import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PDV1 extends JDialog{
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JRadioButton dinheiroRadioButton;
    private JRadioButton cartãoDeCréditoRadioButton;
    private JRadioButton cartãoDeDébitoRadioButton;
    private JRadioButton PIXRadioButton;
    private JRadioButton cartãoAlimentaçãoRadioButton;
    private JRadioButton cartãoRefeiçãoRadioButton;
    private JButton finalizarCompraButton;
    private JButton cliqueParaSomarButton;
    private JButton addButton;
    private JPanel PDV1;
    private JTextField textField4;


    public PDV1(JFrame parent){

        super(parent);
        setTitle("PDV");
        setSize(800,800);
        setContentPane(PDV1);
        setLocale(parent);
        setMinimumSize(new Dimension(450,474));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);



    }

    private void setLocale(JFrame parent) {

    }
}
