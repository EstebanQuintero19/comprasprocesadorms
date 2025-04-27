package co.ucoshop.comprasprocesadorms.util;

public interface MessageSender<T> {
    void execute(T message, Long idMessage);
}
