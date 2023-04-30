package ch.giuntini.netjlo_base.threads;

import ch.giuntini.netjlo_base.connections.client.sockets.BaseSocket;
import ch.giuntini.netjlo_base.socket.Disconnectable;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ThreadCommons {

    public static <S extends BaseSocket>
    void onExitIn(S socket, ObjectInputStream objectInputStream, Disconnectable connection, boolean stop) {
        try {
            if (!socket.isInputShutdown()) socket.shutdownInput();
            internalCommon(objectInputStream, connection, stop);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <S extends BaseSocket>
    void onExitOut(S socket, ObjectOutputStream objectOutputStream, Disconnectable connection, boolean stop) {
        try {
            if (!socket.isOutputShutdown()) socket.shutdownOutput();
            internalCommon(objectOutputStream, connection, stop);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static
    void internalCommon(Closeable objectStream, Disconnectable connection, boolean stop) throws IOException {
        objectStream.close();
        if (!stop) {
            try {
                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
