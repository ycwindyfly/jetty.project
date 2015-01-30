//
//  ========================================================================
//  Copyright (c) 1995-2015 Mort Bay Consulting Pty. Ltd.
//  ------------------------------------------------------------------------
//  All rights reserved. This program and the accompanying materials
//  are made available under the terms of the Eclipse Public License v1.0
//  and Apache License v2.0 which accompanies this distribution.
//
//      The Eclipse Public License is available at
//      http://www.eclipse.org/legal/epl-v10.html
//
//      The Apache License v2.0 is available at
//      http://www.opensource.org/licenses/apache2.0.php
//
//  You may elect to redistribute this code under either of these licenses.
//  ========================================================================
//

package org.eclipse.jetty.tests.ws;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/sessioninfo")
public class SessionInfoSocket
{
    @Inject
    private HttpSession httpSession;

    @OnMessage
    public void onMessage(Session session, String message)
    {
        try
        {
            switch (message)
            {
                case "info":
                    session.getBasicRemote().sendText("HttpSession = " + httpSession);
                    break;
                case "close":
                    session.close();
                    break;
                default:
                    session.getBasicRemote().sendText(message);
                    break;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace(System.err);
        }
    }
}