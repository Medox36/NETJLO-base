package ch.giuntini.netjlo_base.socket;

public interface Send<P> {

    default void send(P p) {
        // TODO set proper Exception message
        throw new UnsupportedOperationException("");
    }

    default void sendAll(P p) {
        // TODO set proper Exception message
        throw new UnsupportedOperationException("");
    }
}
