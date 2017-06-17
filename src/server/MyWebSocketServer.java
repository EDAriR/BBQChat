package server;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/MyWebSocketServer/{name}/{room}")
public class MyWebSocketServer {

    private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(@PathParam("name") String name, @PathParam("room") int room,
                       Session userSession) throws IOException {

        connectedSessions.add(userSession);
        String text = String.format("Session ID = %s, connected; name = %s; room = %s",
                userSession.getId(), name, room);
        System.out.println(text);
    }

    @OnMessage
    public void onMessage(Session userSession, String message) {
        for (Session session : connectedSessions) {
            if (session.isOpen())
                session.getAsyncRemote().sendText(message);
        }
        System.out.println("Message received: " + message);
    }

    @OnError
    public void onError(Session userSession, Throwable e) {
        System.out.println("Error: " + e.toString());
    }

    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        connectedSessions.remove(userSession);
        String text = String.format("session ID = %s, disconnected; close code = %d", userSession.getId(),
                reason.getCloseCode().getCode());
        System.out.println(text);
    }
}
