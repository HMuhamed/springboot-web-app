
public class MyUtils {
	public static int delay = 30;
	public static int algorithm = 0; // 0: bfs
										public static int getAlgorithm() {
		return algorithm;
	}
	public static void setAlgorithm(int algorithm) {
		MyUtils.algorithm = algorithm;
	}
	// 1: dfs
										// 2: best
										// 3: astar
	public static boolean solving = false;
	public static boolean breakAlgo = false;
	public static boolean allowDiagonials = false;
	public static boolean stopped = false;
	
	
	public static String name = "";
}
