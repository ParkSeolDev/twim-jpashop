package jpabook.jpashop.common.util;

import com.corundumstudio.socketio.SocketIOClient;
import jpabook.jpashop.api.service.SocketIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledTasks {

    @Autowired
    private SocketIOService socketIOService;

    @Scheduled(fixedRate = 5000)
    public void sendMessageToClient() {
        if (socketIOService.getClientMap().containsKey("1")) {
            SocketIOClient client = socketIOService.getClientMap().get("1");
            client.sendEvent("data_event", "Data: "+ "Scheduled task received at: "+ new Date());
        }
    }
}