import java.util.Random;

public enum Effect {
    NONE, FREEZE, DIZZY, SLOW, BLIND;

    public static Effect randomEffect() {
        Random random = new Random();
        Effect [] effects = Effect.values();
        return effects[random.nextInt(effects.length - 1) + 1];
    }
}
