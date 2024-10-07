import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.File;

public class AlgorithmVisualizer extends JFrame {

    public AlgorithmVisualizer() {
        setTitle("Algorithm Visualizer");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        // Maximize window on start
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Header
        JPanel header = new JPanel();
        header.setBackground(new Color(149, 69, 53));
        header.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Algorithm Visualizer", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        header.add(titleLabel, BorderLayout.NORTH);

        JLabel descriptionLabel = new JLabel("<html>Explore and visualize Dijkstra, Bellman-Ford, and A* algorithms.<br>Click on any algorithm to learn more and see how they work.</html>", SwingConstants.CENTER);
        descriptionLabel.setForeground(Color.WHITE);
        header.add(descriptionLabel, BorderLayout.SOUTH);
        add(header, BorderLayout.NORTH);

        // Algorithms Section
        JPanel algorithmsPanel = new JPanel();
        algorithmsPanel.setLayout(new GridLayout(1, 3, 20, 20)); // Space between the cards

        // Add algorithm cards with file paths
        algorithmsPanel.add(createAlgorithmCard("Dijkstra Algorithm", "C:\\Users\\Online\\Desktop\\dijkstra.png", null));
        algorithmsPanel.add(createAlgorithmCard("Bellman-Ford Algorithm", "C:\\Users\\Online\\Desktop\\BellmanFord.png", null));
        algorithmsPanel.add(createAlgorithmCard("A* Algorithm", "C:\\Users\\Online\\Desktop\\A.png", null)); // A* opens a window, not a link

        add(algorithmsPanel, BorderLayout.CENTER);

        // Footer
        JPanel footer = new JPanel();
        footer.setBackground(new Color(149, 69, 53));
        JLabel footerLabel = new JLabel("&copy; 2024 Algorithm Visualizer. All Rights Reserved.", SwingConstants.CENTER);
        footerLabel.setForeground(Color.WHITE);
        footer.add(footerLabel);
        add(footer, BorderLayout.SOUTH);
    }

    // Method to create algorithm card panels with square shape and rounded border
    private JPanel createAlgorithmCard(String name, String imagePath, String link) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setPreferredSize(new Dimension(200, 250)); // Smaller square-sized card
        card.setBackground(Color.WHITE);

        // Panel for image with rounded border
        JPanel imagePanel = new RoundedPanel(20); // Rounded corners with 20px radius
        imagePanel.setLayout(new GridBagLayout()); // Use GridBagLayout to center the image
        imagePanel.setBackground(Color.WHITE);
        imagePanel.setBorder(BorderFactory.createLineBorder(new Color(149, 69, 53), 3)); // Rounded border for the cornice

        JLabel imageLabel = new JLabel();
        try {
            // Load image from file path instead of class resource
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                ImageIcon imageIcon = new ImageIcon(imageFile.getAbsolutePath());
                Image scaledImage = imageIcon.getImage().getScaledInstance(450, 400, Image.SCALE_SMOOTH); // Set image size as needed
                imageLabel.setIcon(new ImageIcon(scaledImage));
            } else {
                imageLabel.setText("Image not found");
            }
        } catch (Exception e) {
            imageLabel.setText("Image not found");
        }

        imagePanel.add(imageLabel); // Add the image to the centered image panel
        card.add(imagePanel, BorderLayout.CENTER); // Add the image panel to the card

        // Label for the algorithm name
        JLabel nameLabel = new JLabel(name, SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Padding above the text
        card.add(nameLabel, BorderLayout.SOUTH);

        // Add click event listener for the card
        card.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if ("A* Algorithm".equals(name)) {
                    MyUtils.algorithm = 1;
                    new AStarVisualizer();
                } else if ("Bellman-Ford Algorithm".equals(name)) {
                    MyUtils.algorithm = 2;
                    new BellmanFordVisualizer();
                } else if ("Dijkstra Algorithm".equals(name)) {
                    MyUtils.algorithm = 3;
                    new DijkstraVisualizer();
                }
            }
        });

        return card;
    }

    // Custom JPanel with rounded corners
    class RoundedPanel extends JPanel {
        private int radius;

        public RoundedPanel(int radius) {
            this.radius = radius;
            setOpaque(false); // Make the background transparent for rounded corners
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), radius, radius)); // Rounded rectangle
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 200); // Ensure the size of the rounded panel is square
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AlgorithmVisualizer visualizer = new AlgorithmVisualizer();
            visualizer.setVisible(true);
        });
    }
}
