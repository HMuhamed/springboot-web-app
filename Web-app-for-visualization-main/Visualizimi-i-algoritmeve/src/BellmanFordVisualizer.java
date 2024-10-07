import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BellmanFordVisualizer extends JFrame {
    public BellmanFordVisualizer() {
       
        setTitle("Bellman-Ford Algorithm");
        setSize(600, 400);
        
        setLocationRelativeTo(null);
        
     
        UIManager.put("Panel.background", new Color(244, 244, 249));
        UIManager.put("Button.background", new Color(188, 143, 143));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.font", new Font("Arial", Font.PLAIN, 16));
        UIManager.put("Label.font", new Font("Arial", Font.PLAIN, 16));

       
        JLabel titleLabel = new JLabel("Bellman-Ford Algorithm", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(149, 69, 53));
        headerPanel.add(titleLabel);

        JTextArea descriptionArea = new JTextArea();
        descriptionArea.setText(
            "The Bellman-Ford algorithm is designed to find the shortest paths from a single source vertex to all other vertices in a weighted graph. " +
            "It is particularly useful for graphs that contain negative weight edges.\n\n" +
            "Unlike Dijkstra's algorithm, the Bellman-Ford algorithm can handle graphs with negative weights by detecting negative weight cycles. " +
            "It iterates over all edges up to |V| - 1 times, where |V| is the number of vertices in the graph, ensuring that the shortest path is accurately computed even with negative weights.\n\n" +
            "Key Features:\n" +
            "- Handles negative weight edges and detects negative weight cycles.\n" +
            "- Provides a reliable solution for graphs with negative weights.\n" +
            "- Less efficient compared to Dijkstra's algorithm for graphs with non-negative weights.\n"
        );
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(Color.WHITE);
        descriptionArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        descriptionArea.setMargin(new Insets(10, 10, 10, 10));

        JButton visualizeButton = new JButton("Visualize Bellman-Ford");
        visualizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	MyUtils.algorithm=4;
            	MyUtils.name="Visualize Bellman-Ford";
             	 new WindowFrame();
             	setVisible(false);
            }
        });
        visualizeButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        visualizeButton.setFocusPainted(false);

        
        setLayout(new BorderLayout());
        add(headerPanel, BorderLayout.NORTH);
        add(new JScrollPane(descriptionArea), BorderLayout.CENTER);
        add(visualizeButton, BorderLayout.SOUTH);
        
        
        setVisible(true);
    }

   
}
