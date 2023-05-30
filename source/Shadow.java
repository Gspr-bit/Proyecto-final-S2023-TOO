import greenfoot.*;

/**
 * Sombra que oscurece el mapa.
 * Esta sombra siempre debe estar en el centro de la ventana.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Shadow extends Actor {
    private final int defaultSize;

    /**
     * @param size Tamaño de la sombra. 0 significa que no hay sombra.
     * @throws InvalidShadowSizeExceptions El tamaño especificado no es válido. Debe ser un número par entre 0 y 14
     */
    public Shadow(int size) throws InvalidShadowSizeExceptions {
        this.defaultSize = size;
        setSize(size);
    }

    /**
     * Cambia el tamaño de la sombra.
     *
     * @param size Tamaño de la sombra. 0 significa que no hay sombra.
     * @throws InvalidShadowSizeExceptions El tamaño especificado no es válido. Debe ser un número par entre 0 y 14
     */
    public void setSize(int size) throws InvalidShadowSizeExceptions {
        if (size < 0 || size > 14 || size % 2 != 0) {
            throw new InvalidShadowSizeExceptions("El tamaño de la sombra sólo puede ser un número par entre 0 y 14");
        }
        setImage("Shadows/shadow-" + size + ".png");
    }

    /**
     * Regresa la sombra a su tamaño original.
     */
    public void reset() {
        try {
            setSize(defaultSize);
        } catch (InvalidShadowSizeExceptions e) {
            // No debería llegar aquí
            throw new RuntimeException(e);
        }
    }
}

class InvalidShadowSizeExceptions extends Exception {
    public InvalidShadowSizeExceptions(String message) {
        super(message);
    }
}
