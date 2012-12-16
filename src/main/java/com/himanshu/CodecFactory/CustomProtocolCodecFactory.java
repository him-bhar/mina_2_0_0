package com.himanshu.CodecFactory;

import com.himanshu.MinaRequestDecoder.RequestDecoder;
import com.himanshu.ResponseEncoder.RequestEncoder;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.textline.TextLineEncoder;

/**
 * Created by IntelliJ IDEA.
 * User: Himanshu
 * Date: 29 Aug, 2010
 * Time: 10:59:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class CustomProtocolCodecFactory extends TextLineEncoder implements ProtocolCodecFactory {
    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        //return null;  //To change body of implemented methods use File | Settings | File Templates.
        //return new TextLineEncoder();
        return new RequestEncoder();
    }

    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        //return null;  //To change body of implemented methods use File | Settings | File Templates.
        return new RequestDecoder();
    }
}
