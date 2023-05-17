package qa.windows;

import qa.RegressionController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Regression extends JFrame {

    // CONSTANTS --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->

    private static final String CALIBRI = "Calibri";
    private static final String DEFAULT = "Default";

    // ATTRIBUTES   -->  --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->

    private JComboBox comboBox;
    private JTextField testRunURL;
    private JTextField textPlanURL;
    private JButton updateButton;
    private JPanel jPanel;
    private JLabel browserLabel;
    private JLabel test_run_url_label;
    private JLabel testPlanRegressionLabel;
    private JLabel soleraLogo;
    private JLabel version;
    private JLabel statusLog;

    // CONSTRUCTORS  --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->

    public Regression(String appTitle)  {
        super(appTitle);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(jPanel);
        this.setVisible(true);
        this.pack();
        this.setBounds(0, 0, 800, 480);
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon("src/main/resources/img/Logo2.png").getImage());
        jPanel.setBackground(new Color(127, 133, 245));

        browserLabel.setFont(new Font(CALIBRI, Font.BOLD, 18));
        test_run_url_label.setFont(new Font(CALIBRI, Font.BOLD, 18));
        testPlanRegressionLabel.setFont(new Font(CALIBRI, Font.BOLD, 18));

        comboBox.setFont(new Font(DEFAULT, Font.PLAIN, 14));

        updateButton.setFont(new Font(DEFAULT, Font.PLAIN, 14));

        testRunURL.setFont(new Font(DEFAULT, Font.PLAIN, 14));
        textPlanURL.setFont(new Font(DEFAULT, Font.PLAIN, 14));

        comboBox.setBackground(Color.white);

        updateButton.setBackground(Color.white);

        updateButton.setEnabled(true);

        RegressionController controller = new RegressionController();

        // LISTENERS     --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCursor(Cursor.WAIT_CURSOR);
                controller.updateFromTestRail(String.valueOf(comboBox.getSelectedItem()), testRunURL.getText(),
                        textPlanURL.getText());
                setCursor(Cursor.DEFAULT_CURSOR);
            }
        });
        updateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                setCursor(Cursor.HAND_CURSOR);
            }
        });
        updateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                setCursor(Cursor.DEFAULT_CURSOR);
            }
        });
        comboBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                setCursor(Cursor.HAND_CURSOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                setCursor(Cursor.DEFAULT_CURSOR);
            }
        });

    }

    // Main Function --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->

    public static void runApplication() {
        new Regression("QapterClaims FR - Automation Passed Results");
    }
}