import java.awt.Color;
import java.awt.Cursor;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JPanel;

public class SearchAlgorithms extends Thread {

	private Grid grid;
	private JPanel panel;
	private boolean solutionFound = false;

	public SearchAlgorithms(Grid grid, JPanel panel) {
		this.grid = grid;
		this.panel = panel;
	}

	@Override
	public void run() {
	    if (MyUtils.solving) {
	        MyUtils.breakAlgo = false;
	        solutionFound = false;
	        switch (MyUtils.algorithm) {
	        case 0:
	        	
	            bfs(grid.getStart());
	            break;
	        case 1:
	            dfs(grid.getStart());
	            break;
	        case 2:
	            best(grid.getStart());
	            break;
	        case 3:
	            astar(grid.getStart());
	            break;
	        case 4:
	          //  bellmanFord(grid.getStart()); 
	            bfs(grid.getStart());
	            break;
	        case 5:
	         //  dijkstra(grid.getStart()); 
	           dfs(grid.getStart());
		          
	            break;
	        }
	    }
	    MyUtils.solving = false;
	    if (MyUtils.breakAlgo) {
	        grid.initialiseGrid();
	    }
	    panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	    panel.revalidate();
	    panel.repaint();
	}

	
	
	
	
	
	
	
	
	
	
	

	private void astar(Node start) {
		TreeSet<Node> queue = new TreeSet<>();
		queue.add(start);
		start.setAlreadyVisited(true);

		while (MyUtils.solving && !solutionFound && !queue.isEmpty()) {
			Node current = queue.pollFirst();
			current.setType(Type.CURRENT);

			panel.revalidate();
			panel.repaint();
			delay(MyUtils.delay);

			if (current.equals(grid.getFinish())) {
				extractSolution(current);
				MyUtils.solving = false;
				solutionFound = true;
				return;
			} else {
				current.setType(Type.VISITED);
				for (Node child : current.getNeighbors(grid)) {
					queue.add(child);
					child.setAlreadyVisited(true);
					child.setType(Type.FRONTIER);
				}
			}

		}
	}
	
	
	
	
	
	
	
	
	
	private void bellmanFord(Node start) {
	    TreeSet<Node> queue = new TreeSet<>((n1, n2) -> Integer.compare(n1.getG(), n2.getG())); // Priority queue based on distance
	    queue.add(start);
	    start.setG(0);
	    start.setAlreadyVisited(true);
	    

	    while (MyUtils.solving && !solutionFound && !queue.isEmpty()) {
	        Node current = queue.pollFirst(); // Get node with the smallest distance
	        current.setType(Type.CURRENT);

	        panel.revalidate();
	        panel.repaint();
	        delay(MyUtils.delay);
	        System.out.println("Current node: " + current);
	        System.out.println("Current G value: " + current.getG());
	        System.out.println("Neighbors: " + current.getNeighbors(grid));


	        if (current.equals(grid.getFinish())) {
	            extractSolution(current);
	            MyUtils.solving = false;
	            solutionFound = true;
	            return;
	        } else {
	            current.setType(Type.VISITED);
	            for (Node neighbor : current.getNeighbors(grid)) {
	                // Relaxation logic
	                if (current.getG() + neighbor.getWeight() < neighbor.getG()) {
	                	neighbor.setG((int) Math.round(current.getG() + neighbor.getWeight()));

	                    neighbor.setParent(current);
	                    neighbor.setAlreadyVisited(true); // Mark neighbor as visited

	                    if (!queue.contains(neighbor)) {
	                        queue.add(neighbor); // Add neighbor to the queue
	                        neighbor.setType(Type.FRONTIER);
	                    }
	                }
	            }
	        }
	    }
	}




	private void dijkstra(Node start) {
	    PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.getG(), b.getG()));
	    Set<Node> inQueue = new HashSet<>();  // Set për të mbajtur gjurmët e nyjeve në PriorityQueue
	    start.setG(0);  // Nyja e fillimit ka një distancë prej 0
	    pq.add(start);
	    inQueue.add(start);

	    while (!pq.isEmpty() && MyUtils.solving && !solutionFound) {
	        Node current = pq.poll();  // Merr nyjen me vlerën më të vogël të G
	        inQueue.remove(current);  // Hiq nga inQueue pasi po përpunohet

	        // Kontrollo nëse kemi arritur nyjen e fundit
	        if (current.equals(grid.getFinish())) {
	            extractSolution(current);  // Ekstrakto rrugën e shkurtër
	            MyUtils.solving = false;
	            solutionFound = true;
	            return;
	        }

	        current.setType(Type.VISITED);  // Shëno nyjen aktuale si të vizituar
	        panel.revalidate();  // Rifresko UI
	        panel.repaint();
	        delay(MyUtils.delay);

	        // Përditëso fqinjët për nyjen aktuale
	        for (Node neighbor : current.getNeighbors(grid)) {
	            if (neighbor.isAlreadyVisited() || neighbor.isWall()) {
	                continue;  // Shmang nyjet e vizituara ose murin
	            }

	            int newCost = (int) (current.getG() + neighbor.getWeight());  // Llogarit koston e re

	            // Nëse kostoja e re është më e vogël, bëjmë 'relaxation'
	            if (newCost < neighbor.getG()) {
	                neighbor.setG(newCost);  // Përditëso vlerën G
	                neighbor.setParent(current);  // Vendos prindin e fqinjit

	                // Kontrollo nëse fqinja është tashmë në radhën prioritare
	                if (!inQueue.contains(neighbor)) {
	                    pq.add(neighbor);  // Shto fqinjën në radhë
	                    inQueue.add(neighbor);  // Shëno që është shtuar në radhë
	                    neighbor.setType(Type.FRONTIER);  // Marko si pjesë të frontit
	                } else {
	                    // Nëse fqinja është tashmë në radhë, rifresko pozicionin e saj
	                    pq.remove(neighbor);  // Hiq versionin e vjetër
	                    pq.add(neighbor);  // Shto fqinjën me vlerën e përditësuar të G
	                }
	            }
	        }
	    }
	}

	        
	    
	

	      

	    
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	private void best(Node start) {
		TreeSet<Node> queue = new TreeSet<>();
		queue.add(start);
		start.setAlreadyVisited(true);

		while (MyUtils.solving && !solutionFound && !queue.isEmpty()) {
			Node current = queue.pollFirst();
			current.setType(Type.CURRENT);

			panel.revalidate();
			panel.repaint();
			delay(MyUtils.delay);

			if (current.equals(grid.getFinish())) {
				extractSolution(current);
				MyUtils.solving = false;
				solutionFound = true;
				return;
			} else {
				current.setType(Type.VISITED);
				for (Node child : current.getNeighbors(grid)) {
					queue.add(child);
					child.setAlreadyVisited(true);
					child.setType(Type.FRONTIER);
				}
			}

		}
	}

	private void dfs(Node start) {
		dfsUntill(start);
	}

	private void dfsUntill(Node node) {
		if (!MyUtils.solving || solutionFound) {
			return;
		}
		node.setType(Type.CURRENT);
		node.setAlreadyVisited(true);

		panel.revalidate();
		panel.repaint();
		delay(MyUtils.delay);

		if (node.equals(grid.getFinish())) {
			extractSolution(node);
			MyUtils.solving = false;
			solutionFound = true;
			return;
		} else {
			node.setType(Type.VISITED);
			LinkedList<Node> children = (LinkedList<Node>) node.getNeighbors(grid);
			for (Node child : children) {
				if (!solutionFound) {
					for (Node temp : children) {
						if (temp.equals(child)) {
							continue;
						}
						if (!temp.getType().equals(Type.VISITED)) {
							temp.setType(Type.FRONTIER);
						}
					}
				}

				dfsUntill(child);
			}

		}

	}

	private void bfs(Node startingNode) {
		Queue<Node> frontier = new LinkedList<Node>();
		Node currentNode = null;
		frontier.add(startingNode);

		while (MyUtils.solving && !frontier.isEmpty() && !solutionFound) {
			currentNode = frontier.poll();
			currentNode.setType(Type.CURRENT);

			panel.revalidate();
			panel.repaint();
			delay(MyUtils.delay);

			if (currentNode.equals(grid.getFinish())) {
				extractSolution(currentNode);
				MyUtils.solving = false;
				solutionFound = true;
				continue;
			} else {
				currentNode.setType(Type.VISITED);
				for (Node neighbor : currentNode.getNeighbors(grid)) {
					frontier.add(neighbor);
					neighbor.setType(Type.FRONTIER);
					neighbor.setAlreadyVisited(true);
				}
			}

		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public void extractSolution(Node node) {
		if (!MyUtils.solving) {
			return;
		}

		Node parent = node.getParent();

		while (!grid.getStart().equals(parent)) {
			parent.setType(Type.PATH);
			panel.revalidate();
			panel.repaint();
			delay(10);
			parent = parent.getParent();
		}
		panel.revalidate();
		panel.repaint();
	}

	public void delay(int delay) {
		try {
			Thread.sleep(delay);
			panel.repaint();
		} catch (InterruptedException e) {
		}
	}
}