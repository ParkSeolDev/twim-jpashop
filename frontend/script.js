// import { io } from "socket.io-client";
// import * as io from 'socket.io-client';
// import { connect } from 'socket.io-client';
// var socket2 = io2.connect('http://localhost:9999');
// io = new Socket("127.0.0.1", port);
// const socket2 = connect(`127.0.0.1:9999`);
// const socket2 = connect('http://localhost:9999', { // ws:// 를 안쓰고 http를 쓴다
//       path: '/socket.io', // 서버 path와 일치시켜준다
//       transports: ['websocket']
//    });


// import { receiveMessageOnPort } from "worker_threads";

// const { io } = require("socket.io-client");

// import io from "src/node_modules/socket.io-client/dist/socket.io.js";



let socketClient = io("127.0.0.1:9999",{
  cors:{origin:'*'}
});




// socketClient.connect('connect', (socket)=>{
//   socket.on('disconnect', (reason)=>{ console.log(reason)});
//   socket.on('error', (error)=>{console.log(error) });
//   socket.on('result', (data)=>{ console.log(data) });
// });
console.log(JSON.stringify(Response))
socketClient.on('connection', function(data) {
  
  console.log(data)
  $('#result').append(data);
  print("connected");
});

socketClient.volatile.emit("hello", "might or might not be received");


document.querySelector('#resultBtn').onclick = function() {
  console.log('11')
  socketClient.emit('message', "클릭");
  // socketClient.of('/api/v1/order/all-orders').emit("responseNewMap", { mapData: map })
  socketClient.on("responseNewMap", function(data) {
    var map = data.mapData;
    socketClient.of('/api/v1/order/all-orders').emit("responseNewMap", { mapData: map })
  });


  socketClient.onopen = function(e) {
    alert("[open] 커넥션이 만들어졌습니다.");
    alert("데이터를 서버에 전송해봅시다.");
    socketClient.send("My name is Bora");
    print("aaa")
  };
  
  socketClient.onmessage = function(event) {
    alert(`[message] 서버로부터 전송받은 데이터: ${event.data}`);
  };

};



// let interval;


// // socketClient.on('news', function (data) {
// //   console.log(data);

// //   socket.emit('reply', 'Hello JS');
// // });
// socketClient.on(port, () => document.querySelector("#result").value = result);
// socketClient.on("connection", (socket) => {
//   console.log("New client connected");
//   if (interval) {
//     clearInterval(interval);
//   }
//   interval = setInterval(() => getApiAndEmit(socket), 1000);
//   socket.on("disconnect", () => {
//     console.log("Client disconnected");
//     clearInterval(interval);
//   });

//   socket.on(port, () => document.querySelector("#result").value = result);
// });

// const getApiAndEmit = socket => {
//   const response = new Date();
//   // Emitting a new message. Will be consumed by the client
//   socket.emit("FromAPI", response);
// };

// server.listen(port, () => console.log(`Listening on port ${port}`));




        // var msgform = document.getElementById('result');
        // // socket.on 함수로 서버에서 전달하는 신호를 수신
        // socket.on('usercount', (count) => {
        //     var userCounter = document.getElementById('usercount');
        //     userCounter.innerText = "현재 " + count + "명이 서버에 접속해있습니다.";
        // });

        // // 메시지 수신시 HTML에 메시지 내용 작성
        // socket.on('message', (msg) => {
        //     var messageList = document.getElementById('messages');
        //     var messageTag = document.createElement("li");
        //     messageTag.innerText = msg;
        //     messageList.appendChild(messageTag);
        // });

        // msgform.onsubmit = (e) => {
        //     e.preventDefault();
        //     var msginput = document.getElementById('msginput');

        //     // socket.emit으로 서버에 신호를 전달
        //     socket.emit('message', msginput.value);

        //     msginput.value = "";
        // };

// document.querySelector("#result").value = result;
