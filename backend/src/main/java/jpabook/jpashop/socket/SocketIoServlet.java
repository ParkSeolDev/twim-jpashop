package jpabook.jpashop.socket;

import io.socket.engineio.server.Emitter;
import io.socket.engineio.server.EngineIoServer;
import io.socket.socketio.server.SocketIoNamespace;
import io.socket.socketio.server.SocketIoServer;
import io.socket.socketio.server.SocketIoSocket;
import jpabook.jpashop.db.entity.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/socket.io/*")
public class SocketIoServlet extends HttpServlet {

    private final EngineIoServer mEngineIoServer = new EngineIoServer();
    private final SocketIoServer mSocketIoServer = new SocketIoServer(mEngineIoServer);

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        mEngineIoServer.handleRequest(request, response);
    }


}