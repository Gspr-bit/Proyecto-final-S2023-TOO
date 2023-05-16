/**
 * Esta excepción se lanza cuando los porcentajes de cuántos tiles deberían ser mostrados
 * están mal establecidos y no suman 100.
 */
class WrongGenerationPercentagesException extends Exception {
    public WrongGenerationPercentagesException(String message) {
        super(message);
    }
}
