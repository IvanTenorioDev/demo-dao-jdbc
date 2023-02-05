package db;

public class DbException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    // Construtor que recebe uma mensagem de erro
    public DbException(String msg) {
        // Passa a mensagem de erro para o construtor da classe mãe (RuntimeException)
        super(msg);
    }
}

