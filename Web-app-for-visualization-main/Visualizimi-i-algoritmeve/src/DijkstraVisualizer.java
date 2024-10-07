import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DijkstraVisualizer extends JFrame {
    public DijkstraVisualizer() {
        // Set frame properties
        setTitle("Dijkstra's Algorithm");
        setSize(600, 400);
        
        setLocationRelativeTo(null);
        
        // Customize the look and feel
        UIManager.put("Panel.background", new Color(244, 244, 249));
        UIManager.put("Button.background", new Color(188, 143, 143));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.font", new Font("Arial", Font.PLAIN, 16));
        UIManager.put("Label.font", new Font("Arial", Font.PLAIN, 16));

        // Create components
        JLabel titleLabel = new JLabel("Dijkstra's Algorithm", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(149, 69, 53));
        headerPanel.add(titleLabel);

        JTextArea descriptionArea = new JTextArea();
        descriptionArea.setText(
            "Dijkstra's algorithm is a classic algorithm used in graph theory to find the shortest paths from a single source vertex to all other vertices in a weighted graph. " +
            "It is widely applied in network routing protocols, geographical mapping systems, and various optimization problems.\n\n" +
            "The algorithm operates by iteratively selecting the node with the smallest known distance, updating the distances to its neighbors, and marking the node as processed. " +
            "This continues until all nodes are processed, ensuring that the shortest paths from the source to all other nodes are determined.\n\n" +
            "Key Features:\n" +
            "- Efficient for graphs with non-negative weights.\n" +
            "- Useful in real-time applications such as GPS navigation and network routing.\n" +
            "- Provides an optimal solution for finding the shortest paths.\n"
        );
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(Color.WHITE);
        descriptionArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        descriptionArea.setMargin(new Insets(10, 10, 10, 10));

        JButton visualizeButton = new JButton("Visualize Dijkstra");
        visualizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	MyUtils.algorithm=5;
            	MyUtils.name="Visualize Dijkstra";
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
