package jpabook.jpashop.api.controller;

import com.corundumstudio.socketio.SocketIOClient;
import jpabook.jpashop.api.service.SocketIOService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExternalController {

    private SocketIOService socketIOService;

    @GetMapping("/data")
    public ResponseEntity<String> sendData(@RequestParam("clientId") String clientId) {

        if (socketIOService.getClientMap().containsKey(clientId)) {
            SocketIOClient client = socketIOService.getClientMap().get(clientId);
            client.sendEvent("data_event", "Data: " + "External data received at: " + new Date());
        }

        return ResponseEntity.ok("success");

    }

}