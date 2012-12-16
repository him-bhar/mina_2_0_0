package com.himanshu.MinaServer;

/**
 * Created by IntelliJ IDEA.
 * User: Himanshu
 * Date: 29 Aug, 2010
 * Time: 2:47:04 PM
 * To change this template use File | Settings | File Templates.
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSessionConfig;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.himanshu.CodecFactory.CustomProtocolCodecFactory;

public class MinaServer
{
  private static final int PORT = 8892;

  public static void main(String[] args) throws IOException {

      System.out.println("Initializing MINA Server - 2.0.0 RC1");
      IoAcceptor acceptor = new NioSocketAcceptor();
      acceptor.getFilterChain().addLast("logger", new LoggingFilter());
      //acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
      acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new CustomProtocolCodecFactory()));
      acceptor.setHandler(new MinaServerHandler());
      acceptor.getSessionConfig().setReadBufferSize(2048);
      acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
      acceptor.bind(new InetSocketAddress(PORT));
      System.out.println("Initialized MINA Server - 2.0.0 RC1");

  }
}
