import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WindowFrame extends JFrame {

    private static final long serialVersionUID = -4280772482528512323L;

    private JPanel container;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public static int rows = (int) Math.floor(HEIGHT / Node.size);
    public static int cols = (int) Math.floor(HEIGHT / Node.size);

    public WindowFrame() {
        Grid grid = new Grid(rows, cols);

        container = new JPanel();

        GridPanel gridPanel = new GridPanel(WIDTH - 200, HEIGHT, grid);
        ControlsPanel controls = new ControlsPanel(200, HEIGHT, gridPanel);

        container.setLayout(new BorderLayout());

        container.add(BorderLayout.WEST, controls);
        container.add(BorderLayout.CENTER, gridPanel);

        this.setContentPane(container);
         
        this.setTitle(MyUtils.name);
        this.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.pack();
        this.setVisible(true);
        this.setResizable(true); // Allow resizing
        this.setLocationRelativeTo(null);
    }
}