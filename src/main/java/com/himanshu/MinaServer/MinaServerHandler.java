package com.himanshu.MinaServer;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * Created by IntelliJ IDEA.
 * User: Himanshu
 * Date: 29 Aug, 2010
 * Time: 2:47:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class MinaServerHandler implements IoHandler {
    public void sessionCreated(IoSession ioSession) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.println("Inside sessionCreated");
    }

    public void sessionOpened(IoSession ioSession) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.println("Inside sessionOpened");
        System.out.println("Session Information  ="+ioSession.toString());
    }

    public void sessionClosed(IoSession ioSession) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.println("Inside sessionClosed");
        //ioSession.close(true);
    }

    public void sessionIdle(IoSession ioSession, IdleStatus idleStatus) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.println("Inside sessionIdle");
        System.out.println("Session Idle is more than configured time. Hence closing the session");
        System.out.println("Idle Status="+idleStatus.toString());
        ioSession.close(true);
    }

    public void exceptionCaught(IoSession ioSession, Throwable throwable) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.println("Inside exceptionCaught");
        throwable.printStackTrace();
        ioSession.close(true);
    }

    public void messageReceived(IoSession ioSession, Object o) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.println("Inside messageReceived");
        System.out.println("Message Received from Remote Host is ="+o.toString());
        System.out.println("You Can put your business logic here.");
        Object responseMsg = "Output should come from business logic";
        ioSession.write(responseMsg);
        ioSession.close(true);
    }

    public void messageSent(IoSession ioSession, Object o) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.println("Inside messageSent");
    }
}
