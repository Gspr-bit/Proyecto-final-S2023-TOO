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
     * @param x
     * @param y
     * @return
     */
    public Direction findDirection(int x, int y) throws PathEmptyException,
                                                        InvalidPointException,
                                                        EndOfPathException {
        int index = this.path.indexOf(new Point(x, y));

        if (this.path.isEmpty()) {
            throw new PathEmptyException("El camino no ha sido inicializado. Por favor llama findPath()");
        }

        if (index == -1) {
            throw new InvalidPointException("No estás dentro del camino");
        }

        Point nextPoint = null;
        try {
            nextPoint = this.path.get(index + 1);
        } catch (IndexOutOfBoundsException e) {
            throw new EndOfPathException("Haz llegado al final del camino");
        }

        int dx = nextPoint.x - x;
        int dy = nextPoint.y - y;

        if (dx > 0)
            return Direction.RIGHT;

        if (dx < 0)
            return Direction.LEFT;

        if (dy > 0)
            return Direction.DOWN;

        if (dy < 0)
            return Direction.UP;

        // No debería llegar así
        return Direction.RIGHT;
    }

    /**
     * Busca el camino desde (x, y) hasta el camino
     * punto más a la izquierda posible.
     * Regresa el camino.
     * @param x
     * @param y
     */
    public void findPath(int x, int y) {
        for (int [] row : visited)
            Arrays.fill(row, INF);

        path.clear();

        try {
            Point targetPoint = bfs(x, y);
            retrievePath(targetPoint);
        } catch (InvalidShadowSizeExceptions e) {
            throw new RuntimeException(e);
        }
    }

    private Point bfs(int x, int y) throws InvalidShadowSizeExceptions {
        Queue<Point> queue = new LinkedList<>();

        if (map[x][y].isCollidable()) {
            throw new InvalidShadowSizeExceptions("El punto (" + x + ", " + y + ") no es un punto válido");
        }

        int [] dxs = {0, 0, 1, -1};
        int [] dys = {1, -1, 0, 0};

        visited[x][y] = 0;

        Point rightMostPoint = new Point(x, y);

        queue.add(new Point(x, y));
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            int px = p.x;
            int py = p.y;

            if (visited[px][py] >= MAX_DISTANCE)
                break;

            for (int i = 0; i < 4; i++) {
                int dx = dxs[i];
                int dy = dys[i];
                int nx = px + dx;
                int ny = py + dy;
                if (nx >= 0 && nx < map.length && ny >= 0 && ny < map[0].length) {
                    if (!map[nx][ny].isCollidable() && visited[nx][ny] > visited[px][py] + 1) {
                        visited[nx][ny] = visited[px][py] + 1;
                        queue.add(new Point(nx, ny));

                        if (ny > rightMostPoint.y ||
                                (ny == rightMostPoint.y &&
                                        visited[nx][ny] < visited[rightMostPoint.x][rightMostPoint.y]))
                            rightMostPoint = new Point(nx, ny);
                    }
                }
            }
        }

        return rightMostPoint;
    }

    /**
     * Construye el camino a partir de la matriz de
     */
    private void retrievePath(Point target) {
        int x = target.x;
        int y = target.y;
        int distance = visited[x][y];
        int [] dxs = {0, 0, 1, -1};
        int [] dys = {1, -1, 0, 0};

        while (distance > 0) {
            for (int i = 0; i < 4; i++) {
                int dx = dxs[i];
                int dy = dys[i];
                int nx = x + dx;
                int ny = y + dy;
                if (nx >= 0 && nx < visited.length && ny >= 0 && ny < visited[0].length) {
                    if (visited[nx][ny] == distance - 1) {
                        path.add(0, new Point(x, y));
                        x = nx;
                        y = ny;
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
