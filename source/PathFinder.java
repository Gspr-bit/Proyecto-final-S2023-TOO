import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Clase que implementa las funciones necesarias para encontrar caminos
 * 
 * @author Gaspar
 */
public class PathFinder  
{
    // Distancia máxima del camino
    private static final int MAX_DISTANCE = 15;
    private static final int INF = (int) 10e9;
    private ArrayList<Point> path;
    private Tile [][] map;
    private int [][] visited;

    public PathFinder(Tile [][] map)
    {
        this.path = new ArrayList<>();
        this.map = map;
        this.visited = new int[map.length][map[0].length];
    }

    /**
     * Regresa la dirección hacia donde debería caminar cuando está en el punto 0, 0
     * @param i
     * @param j
     * @return
     */
    public Direction findDirection(int i, int j) throws PathEmptyException,
                                                        InvalidPointException,
                                                        EndOfPathException {
        int index = this.path.indexOf(new Point(i, j));

        if (this.path.isEmpty()) {
            throw new PathEmptyException("El camino no ha sido inicializado. Por favor ejecuta findPath()");
        }

        if (index == -1) {
            throw new InvalidPointException("No estás dentro del camino");
        }

        Point nextPoint;
        try {
            nextPoint = this.path.get(index + 1);
        } catch (IndexOutOfBoundsException e) {
            throw new EndOfPathException("Haz llegado al final del camino");
        }

        int di = nextPoint.i - i;
        int dj = nextPoint.j - j;

        if (di > 0)
            return Direction.RIGHT;

        if (di < 0)
            return Direction.LEFT;

        if (dj > 0)
            return Direction.DOWN;

        if (dj < 0)
            return Direction.UP;

        // No debería llegar así
        throw new RuntimeException("Algo extraño sucedió");
    }

    /**
     * Busca el camino desde (i, j) hasta el camino
     * punto más a la izquierda posible.
     * Regresa el camino.
     * @param i
     * @param j
     */
    public Direction findPath(int i, int j) {
        for (int [] row : visited)
            Arrays.fill(row, INF);

        path.clear();

        try {
            Point targetPoint = bfs(i, j);
            retrievePath(targetPoint);
            path.add(0, new Point(i, j));

            return this.findDirection(i, j);
        } catch (InvalidPointException | PathEmptyException | EndOfPathException e) {
            throw new RuntimeException(e + " (i, j) = " + i + ", " + j + " path = " + path);
        }
    }

    private Point bfs(int i, int j) throws InvalidPointException {
        Queue<Point> queue = new LinkedList<>();

        if (i < 0 || i >= map.length || j < 0 || j >= map[0].length || map[i][j].isCollidable()) {
            throw new InvalidPointException("El punto (" + i + ", " + j + ") no es un punto válido. path = " + path);
        }

        int [] dis = {0, 0, 1, -1};
        int [] djs = {1, -1, 0, 0};

        visited[i][j] = 0;

        Point rightMostPoint = new Point(i, j);

        queue.add(new Point(i, j));
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            int pi = p.i;
            int pj = p.j;

            if (visited[pi][pj] >= MAX_DISTANCE)
                break;

            for (int k = 0; k < 4; k++) {
                int di = dis[k];
                int dj = djs[k];
                int ni = pi + di;
                int nj = pj + dj;
                if (ni >= 0 && ni < map.length && nj >= 0 && nj < map[0].length) {
                    if (!map[ni][nj].isCollidable() && visited[ni][nj] > visited[pi][pj] + 1) {
                        visited[ni][nj] = visited[pi][pj] + 1;
                        queue.add(new Point(ni, nj));

                        if (ni > rightMostPoint.i ||
                                (ni == rightMostPoint.i &&
                                        visited[ni][nj] < visited[rightMostPoint.i][rightMostPoint.j]))
                            rightMostPoint = new Point(ni, nj);
                    }
                }
            }
        }

        printDistances();

        return rightMostPoint;
    }

    /**
     * REMOVE ME
     */
    private void printDistances() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j].isCollidable()) {
                    System.out.print("####");
                } else if (visited[i][j] == (int) 10e6) {
                    System.out.print("    ");
                } else {
                    if (visited[i][j] < 10) {
                        System.out.print(" ");
                    }
                    System.out.printf(" %d ", visited[i][j]);
                }
            }
            System.out.println();
        }
    }


    /**
     * Construye el camino a partir de la matriz de
     */
    private void retrievePath(Point target) {
        int i = target.i;
        int j = target.j;
        int distance = visited[i][j];
        int [] dis = {0, 0, 1, -1};
        int [] djs = {1, -1, 0, 0};

        while (distance > 0) {
            for (int k = 0; k < 4; k++) {
                int di = dis[k];
                int dj = djs[k];
                int ni = i + di;
                int nj = j + dj;
                if (ni >= 0 && ni < visited.length && nj >= 0 && nj < visited[0].length) {
                    if (visited[ni][nj] == distance - 1) {
                        path.add(0, new Point(i, j));
                        i = ni;
                        j = nj;
                        distance--;
                        break;
                    }
                }
            }
        }
    }
}

class InvalidPointException extends Exception {
    public InvalidPointException(String message) {
        super(message);
    }
}

class PathEmptyException extends Exception {
    public PathEmptyException(String message) {
        super(message);
    }
}

class EndOfPathException extends Exception {
    public EndOfPathException(String message) {
        super(message);
    }
}
