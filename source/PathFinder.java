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
    private static final int INF = (int) 10e9;
    private final ArrayList<Point> path;
    private final Tile [][] map;
    private final int [][] distances;

    public PathFinder(Tile [][] map)
    {
        this.path = new ArrayList<>();
        this.map = map;
        this.distances = new int[map.length][map[0].length];
    }

    /**
     * Regresa la dirección hacia donde debería caminar cuando está en el punto 0, 0
     * @param i Posición x del jugador en Tiles
     * @param j Posición y del jugador en Tiles
     * @return Dirección hacia donde debería caminar
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
     * @param i Posición x del jugador en Tiles
     * @param j Posición y del jugador en Tiles
     * @return Dirección hacia donde debería caminar.
     */
    public Direction findPath(int i, int j) {
        for (int [] row : distances)
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

    /**
     * Implementación de BFS para encontrar el camino más corto.
     * Nota que esta función es bastante costosa por lo que solo debería ser llamada una sola vez.
     * @param i Posición x del jugador en Tiles
     * @param j Posición Y del jugador en Tiles
     * @return El punto más a la izquierda más cercano.
     * @throws InvalidPointException cuando queremos comenzar la búsqueda en un punto donde no puede estar el personaje
     */
    private Point bfs(int i, int j) throws InvalidPointException {
        Queue<Point> queue = new LinkedList<>();

        if (i < 0 || i >= map.length || j < 0 || j >= map[0].length || map[i][j].isCollidable()) {
            throw new InvalidPointException("El punto (" + i + ", " + j + ") no es un punto válido. path = " + path);
        }

        int [] dis = {0, 0, 1, -1};
        int [] djs = {1, -1, 0, 0};

        distances[i][j] = 0;

        Point rightMostPoint = new Point(i, j);

        queue.add(new Point(i, j));
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            int pi = p.i;
            int pj = p.j;

            for (int k = 0; k < 4; k++) {
                int di = dis[k];
                int dj = djs[k];
                int ni = pi + di;
                int nj = pj + dj;
                if (ni >= 0 && ni < map.length && nj >= 0 && nj < map[0].length) {
                    if (!map[ni][nj].isCollidable() && distances[ni][nj] > distances[pi][pj] + 1) {
                        distances[ni][nj] = distances[pi][pj] + 1;
                        queue.add(new Point(ni, nj));

                        if (ni > rightMostPoint.i ||
                                (ni == rightMostPoint.i &&
                                        distances[ni][nj] < distances[rightMostPoint.i][rightMostPoint.j]))
                            rightMostPoint = new Point(ni, nj);
                    }
                }
            }
        }

        return rightMostPoint;
    }

    /**
     * Construye el camino a partir de la matriz `distances` que genera el BFS.
     */
    private void retrievePath(Point target) {
        int i = target.i;
        int j = target.j;
        int distance = distances[i][j];
        int [] dis = {0, 0, 1, -1};
        int [] djs = {1, -1, 0, 0};

        while (distance > 0) {
            for (int k = 0; k < 4; k++) {
                int di = dis[k];
                int dj = djs[k];
                int ni = i + di;
                int nj = j + dj;
                if (ni >= 0 && ni < distances.length && nj >= 0 && nj < distances[0].length) {
                    if (distances[ni][nj] == distance - 1) {
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
