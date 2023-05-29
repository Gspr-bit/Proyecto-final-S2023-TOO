import java.util.Objects;

/**
 * Representación de un punto en un plano cartesiano.
 * 
 * @author Gaspar
 */
class Point {
    protected int i;
    protected int j;

    public Point(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int manhattanDistance(Point o) {
        return Math.abs(o.i - this.i) + Math.abs(o.j - this.j);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return i == point.i && j == point.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }

    public String toString() {
        return "(" + i + ", " + j + ")";
    }
}
