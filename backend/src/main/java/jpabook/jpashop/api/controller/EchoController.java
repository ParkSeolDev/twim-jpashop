package jpabook.jpashop.api.controller;

import jpabook.jpashop.api.service.DataService;
import jpabook.jpashop.db.dto.DataDTO;
import jpabook.jpashop.db.entity.Data;
import jpabook.jpashop.socket.Connection;
import lombok.RequiredArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@TcpController
@RequiredArgsConstructor
public class EchoController {

    private final DataService dataService;

    public void receiveData(Connection connection, byte[] data) {
        String s = new String(data);
        connection.send(s.toUpperCase().getBytes());
        int num = 4;
        String str = s.substring(num);
        DataDTO data1 = new DataDTO();
        data1.setSplitData(str);
        dataService.createData(data1);

        fixSplitData();
    }

    public void fixSplitData(){
        LocalDateTime now = LocalDateTime.now();
        List<DataDTO> list = dataService.getSplitData(now);
        String text = "";
        for (DataDTO data: list) {
            text = text + data.getSplitData();
        }
        System.out.println(text);
    }


    public void connect(Connection connection) {
        System.out.println("New connection " + connection.getAddress().getCanonicalHostName());
    }

    public void disconnect(Connection connection) {
        System.out.println("Disconnect " + connection.getAddress().getCanonicalHostName());
    }
}
