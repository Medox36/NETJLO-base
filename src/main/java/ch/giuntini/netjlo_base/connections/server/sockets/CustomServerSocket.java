package ch.giuntini.netjlo_base.connections.server.sockets;

import ch.giuntini.netjlo_base.connections.client.sockets.BaseSocket;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.SocketImpl;

public class CustomServerSocket<S extends BaseSocket> extends ServerSocket implements AutoCloseable {

    private final Class<S> socketC;

    public CustomServerSocket(int port, Class<S> socketC) throws IOException {
        super(port);
        this.socketC = socketC;
    }

    public CustomServerSocket(int port, int backlog, Class<S> socketC) throws IOException {
        super(port, backlog);
        this.socketC = socketC;
    }

    public CustomServerSocket(int port, int backlog, int soTimeout, Class<S> socketC) throws IOException {
        super(port, backlog);
        setSoTimeout(soTimeout);
        this.socketC = socketC;
    }

    public CustomServerSocket(int port, int backlog, InetAddress bindAddr, Class<S> socketC) throws IOException {
        super(port, backlog, bindAddr);
        this.socketC = socketC;
    }

    @Override
    public S accept() throws IOException {
        if (isClosed())
            throw new SocketException("Socket is closed");
        if (!isBound())
            throw new SocketException("Socket is not bound yet");
        try {
            S s = socketC.getConstructor(SocketImpl.class).newInstance((SocketImpl) null);
            implAccept(s);
            return s;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws IOException {
        if (!isClosed())
            super.close();
    }
}
