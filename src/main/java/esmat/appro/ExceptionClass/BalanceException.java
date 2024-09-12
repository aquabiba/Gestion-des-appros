package esmat.appro.ExceptionClass;

public class BalanceException extends Exception {
    public BalanceException() {
        super("La quantité du mouvement est supérieure à la quantité actuelle.");
    }

    public BalanceException(String message) {
        super(message);
    }
}
