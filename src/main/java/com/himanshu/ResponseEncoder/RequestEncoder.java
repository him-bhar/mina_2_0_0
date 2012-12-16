package com.himanshu.ResponseEncoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import static com.himanshu.constant.Constants.RESPONSE_DELIMITER_CHAR;

/**
 * Created by IntelliJ IDEA.
 * User: Himanshu
 * Date: 3 Sep, 2010
 * Time: 2:58:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class RequestEncoder extends ProtocolEncoderAdapter implements ProtocolEncoder {
    public void encode(IoSession ioSession, Object o, ProtocolEncoderOutput protocolEncoderOutput) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.println("Implementing encode method");
        System.out.println("Message to be encoded is =="+o);
        o = o.toString()+RESPONSE_DELIMITER_CHAR;
        IoBuffer ioBuffer = IoBuffer.allocate(3, false);
        ioBuffer.setAutoExpand(true);
        ioBuffer.setAutoShrink(true);
        System.out.println("Initial response buffer state == "+ioBuffer);
        byte[] responseByteArr = o.toString().getBytes();
        ioBuffer.put(responseByteArr);
        System.out.println("Final response buffer state after adding bytes == "+ioBuffer);
        //ioBuffer.position(0); This can also be used instead of flip
        ioBuffer.flip();
        System.out.println("Final response buffer state after adding bytes and flipping == "+ioBuffer);
        protocolEncoderOutput.write(ioBuffer);
        protocolEncoderOutput.flush();
    }

    public void dispose(IoSession ioSession) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.println("Disposing Session - IMPLEMENTATION PENDING");
        super.dispose(ioSession);
    }
}
