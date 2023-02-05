package db;

public class DbIntegrityException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    // Construtor que recebe uma mensagem de erro
    public DbIntegrityException(String msg) {
        // Passa a mensagem de erro para o construtor da classe mãe (RuntimeException)
        super(msg);
    }
}

