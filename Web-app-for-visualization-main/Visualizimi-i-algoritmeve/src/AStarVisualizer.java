import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AStarVisualizer extends JFrame {
    public AStarVisualizer() {
        // Set frame properties
        setTitle("A* Algorithm");
        setSize(600, 400);
        
        setLocationRelativeTo(null);
        
        // Customize the look and feel
        UIManager.put("Panel.background", new Color(244, 244, 249));
        UIManager.put("Button.background", new Color(188, 143, 143));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.font", new Font("Arial", Font.PLAIN, 16));
        UIManager.put("Label.font", new Font("Arial", Font.PLAIN, 16));

        // Create components
        JLabel titleLabel = new JLabel("A* Algorithm", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(149, 69, 53));
        headerPanel.add(titleLabel);

        JTextArea descriptionArea = new JTextArea();
        descriptionArea.setText(
            "The A* (A-star) algorithm is a popular and powerful search algorithm used for finding the shortest path from a start node to a goal node in a weighted graph. " +
            "It combines aspects of Dijkstra's algorithm and Greedy Best-First Search, making it highly efficient for pathfinding.\n\n" +
            "A* uses a heuristic function to estimate the cost from the current node to the goal, allowing it to prioritize nodes that are likely to lead to the shortest path. " +
            "This heuristic helps the algorithm explore fewer nodes compared to Dijkstra's algorithm, making it faster for many practical applications.\n\n" +
            "Key Features:\n" +
            "- Uses heuristics to improve search efficiency.\n" +
            "- Optimal for pathfinding in games, navigation systems, and robotics.\n" +
            "- Combines Dijkstra's algorithm with a heuristic to explore fewer nodes.\n"
        );
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(Color.WHITE);
        descriptionArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        descriptionArea.setMargin(new Insets(10, 10, 10, 10));

        JButton visualizeButton = new JButton("Visualize A*");
        visualizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your A* visualization logic here
            	MyUtils.algorithm=3;
            	MyUtils.name="Visualize A*";
              	 new WindowFrame();
              	setVisible(false);
            }
        });
        visualizeButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        visualizeButton.setFocusPainted(false);

        // Layout components
        setLayout(new BorderLayout());
        add(headerPanel, BorderLayout.NORTH);
        add(new JScrollPane(descriptionArea), BorderLayout.CENTER);
        add(visualizeButton, BorderLayout.SOUTH);
        
        // Finalize frame
        setVisible(true);
    }

   
}
