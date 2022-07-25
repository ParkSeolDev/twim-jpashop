package jpabook.jpashop.api.controller;

import jpabook.jpashop.api.service.DataService;
import jpabook.jpashop.db.dto.DataDTO;
import jpabook.jpashop.socket.Connection;
import lombok.RequiredArgsConstructor;
import java.util.List;

//0033A99520000000000000000{0:0428,1:1}

@TcpController
@RequiredArgsConstructor
public class EchoController {
    private final DataService dataService;
    private final int numByte = 4;
    private final Double bufferSize = 20.0;
    private int count = 10000;
    private Double skip = 1.0 + Math.ceil((count - (bufferSize - numByte)) / bufferSize);
    private StringBuilder text = new StringBuilder();

    public void receiveData(Connection connection, byte[] data) {

        String s = new String(data);
        connection.send(s.toUpperCase().getBytes());

        if (count == 10000) {
            count = Integer.parseInt(s.substring(0, numByte));
//            skip = 1.0 + Math.floor((count - numBit) / 20.0);
            skip = 1.0 + Math.ceil((count - (bufferSize - numByte)) / bufferSize);
            String str = s.substring(numByte);
            DataDTO data1 = new DataDTO();
            data1.setSplitData(str);
            dataService.createData(data1);
            text.append(data1.getSplitData());
        } else {
            DataDTO data1 = new DataDTO();
            data1.setSplitData(s);
            dataService.createData(data1);
            text.append(data1.getSplitData());
        }
//        fixSplitData();
        skip--;

        if (skip == 0) {
            System.out.println(text.toString());
            text = new StringBuilder();
            count = 10000;
            skip = 1.0 + Math.ceil((count - (bufferSize - numByte)) / bufferSize);
            dataService.setIsPrinted();
        }
    }

    public void fixSplitData() {
        List<DataDTO> list = dataService.getSplitData();
        for (DataDTO data : list) {
            text.append(data.getSplitData());
        }
    }


    public void connect(Connection connection) {
        System.out.println("New connection " + connection.getAddress().getCanonicalHostName());
    }

    public void disconnect(Connection connection) {
        System.out.println("Disconnect " + connection.getAddress().getCanonicalHostName());
    }
}
