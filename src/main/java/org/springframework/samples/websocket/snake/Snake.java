/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.websocket.snake;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;


public class Snake {

    private static final int DEFAULT_LENGTH = 5;

    private final int id;
    private final WebSocketSession session;

    private int length = DEFAULT_LENGTH;

    private final String hexColor;

    public Snake(int id, WebSocketSession session) {
        this.id = id;
        this.session = session;
        this.hexColor = SnakeUtils.getRandomHexColor();
        resetState();
    }

    private void resetState() {
        this.length = DEFAULT_LENGTH;
    }

    private synchronized void kill() throws Exception {
        resetState();
        sendMessage("{'type': 'dead'}");
    }

    private synchronized void reward() throws Exception {
        length++;
        sendMessage("{'type': 'kill'}");
    }


    protected void sendMessage(String msg) throws Exception {
    	session.sendMessage(new TextMessage(msg));
    }

    public int getId() {
        return id;
    }

    public String getHexColor() {
        return hexColor;
    }
}
