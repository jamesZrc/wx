import websocket
import json, threading, time

# tomcat目录: /opt
# nginx目录: /usr/local/nginx
# /opt目录下有很多自动化脚本，要小心

url = 'ws://47.104.15.205:8080/ws/connect'
thread_num = 0


def start():
    ws = websocket.create_connection(url)
    data = {"requestId": thread_num}
    ws.send(json.dumps(data))
    # 传达信息后会等待服务器回应，但服务器已设置成不回复，从而保持连接不会中断
    print(ws.recv())
    pass


#运行该脚本，会尝试建立10000个， 在服务器上tail -300f /opt/apache-tomcat-8.5.54/logs/catalina.out 可查看到建立的连接数
#中断该脚本，可以看到由该脚本建立的连接数减少
connect_num = 10000
for i in range(connect_num):
    thread_num = i
    th = threading.Thread(target=start)
    th.start()
    time.sleep(0.5)
