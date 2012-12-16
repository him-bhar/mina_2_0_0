package com.himanshu.MinaRequestDecoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import static com.himanshu.constant.Constants.REQUEST_DELIMITER_CHAR;
import org.apache.mina.util.byteaccess.ByteArray;

/**
 * Created by IntelliJ IDEA.
 * User: Himanshu
 * Date: 29 Aug, 2010
 * Time: 10:54:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class RequestDecoder extends CumulativeProtocolDecoder {
    /*@Override
    protected boolean doDecode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        //return false;  //To change body of implemented methods use File | Settings | File Templates.
        System.out.println("Inside deDecode of RequestDecoder");
        ioBuffer.setAutoExpand(true);
        ioBuffer.setAutoShrink(true);
        System.out.println("IoBuffer    -"+ioBuffer.remaining());
        System.out.println("IoBuffer Has Remaining   -"+ioBuffer.hasRemaining());
        label1:
        while (ioBuffer.hasRemaining()) {
            System.out.println("ioBuffer.limit() "+ioBuffer.limit());
            System.out.println("ioBuffer.position() "+ioBuffer.position());
            System.out.println("Inside the WHILE loop");
            char[] charArray = new char[ioBuffer.limit() - ioBuffer.position()];
            label2:
            for (int i=ioBuffer.position(); i< ioBuffer.limit(); i++) {
                System.out.println("Inside the FOR loop");
                char ch = (char)ioBuffer.get(i);
                charArray[i] = ch;
                System.out.println("ch =="+ch);
                if(ch == '|') {
                    String outputString = new String(charArray);
                    outputString = outputString.trim();
                    System.out.println("Complete Statement is entered. Good- "+outputString+"\nLength is -"+outputString.length());
                    //protocolDecoderOutput.write("Dummy Value fed in doDecode inside RequestDecoder");
                    protocolDecoderOutput.write(ioBuffer);
                    System.out.println("At the Inside Return True");
                    return true;
                } else {
                    System.out.println("At the Inside Return False");
                    //return false;
                    if (i != ioBuffer.limit()-1) {
                        continue;
                    } else {
                        break label1;
                    }
                }
            }
        }
        System.out.println("At the Outside Return");
        return false;
    }*/

    @Override
    protected boolean doDecode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        //return false;  //To change body of implemented methods use File | Settings | File Templates.
        System.out.println("Inside deDecode of RequestDecoder");
        ioBuffer.setAutoExpand(true);
        ioBuffer.setAutoShrink(true);
        System.out.println("IoBuffer    -"+ioBuffer.remaining());
        System.out.println("IoBuffer Has Remaining   -"+ioBuffer.hasRemaining());
        System.out.println("IoBuffer Limit   -"+ioBuffer.limit());
        label1:
        while (ioBuffer.hasRemaining()) {
            char testChar = (char)ioBuffer.get(ioBuffer.limit()-1);
            if (testChar != '\n' && testChar != '\r' && testChar != REQUEST_DELIMITER_CHAR) {
                System.out.println("INSIDE IF BLOCK");
                System.out.println("testchar ="+testChar);
                //System.out.println("ioBuffer output after getting is "+ioBuffer);
                //System.out.println("ioBuffer.position  "+ioBuffer.position());
                //System.out.println("Buffer position is set to zero");
                //ioBuffer.position(0);
                //System.out.println("ioBuffer.position  "+ioBuffer.position());
                return false;
            } else {
                System.out.println("INSIDE ELSE BLOCK");
                if (testChar == '\n' || testChar == '\r') {
                    char delimiterChar = (char)ioBuffer.get(ioBuffer.limit()-3);
                    System.out.println("Delimiter Char in input is- "+delimiterChar);
                    if (delimiterChar == REQUEST_DELIMITER_CHAR) {
                        System.out.println("ioBuffer.limit() "+ioBuffer.limit());
                        System.out.println("ioBuffer.position() "+ioBuffer.position());
                        System.out.println("Inside the WHILE loop");
                        char[] charArray = new char[ioBuffer.limit() - ioBuffer.position()];
                        label2:
                        for (int i=ioBuffer.position(); i< ioBuffer.limit(); i++) {
                            System.out.println("Inside the FOR loop");
                            char ch = (char)ioBuffer.get(i);
                            if(ch == '\r') {
                                System.out.println("0D found");
                            } else if (ch == '\n') {
                                System.out.println("0A found");
                            } else if (ch == REQUEST_DELIMITER_CHAR) {
                                System.out.println("DELIMITER found");
                            } else {
                                charArray[i] = ch;
                            }
                            System.out.println("character scanned ="+ch);
                        }

                        ioBuffer.position(ioBuffer.limit());

                        String outputString = new String(charArray);
                        outputString = outputString.trim();
                        System.out.println("Complete Statement is entered. Good- "+outputString+"\nLength is -"+outputString.length());
                        protocolDecoderOutput.write(outputString);
                        System.out.println("At the Inside Return True");

                        System.out.println("Buffer state == "+ioBuffer);
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    char delimiterChar = (char)ioBuffer.get(ioBuffer.limit()-1);
                    System.out.println("Delimiter Char in input is- "+delimiterChar);
                    if (delimiterChar == REQUEST_DELIMITER_CHAR) {
                        System.out.println("ioBuffer.limit() "+ioBuffer.limit());
                        System.out.println("ioBuffer.position() "+ioBuffer.position());
                        System.out.println("Inside the WHILE loop");
                        char[] charArray = new char[ioBuffer.limit() - ioBuffer.position()];
                        label2:
                        for (int i=ioBuffer.position(); i< ioBuffer.limit(); i++) {
                            System.out.println("Inside the FOR loop");
                            char ch = (char)ioBuffer.get(i);
                            if(ch == '\r') {
                                System.out.println("0D found");
                            } else if (ch == '\n') {
                                System.out.println("0A found");
                            } else if (ch == REQUEST_DELIMITER_CHAR) {
                                System.out.println("DELIMITER found");
                            } else {
                                charArray[i] = ch;
                            }
                            System.out.println("character scanned ="+ch);
                        }

                        ioBuffer.position(ioBuffer.limit());
                        
                        String outputString = new String(charArray);
                        outputString = outputString.trim();
                        System.out.println("Complete Statement is entered. Good- "+outputString+"\nLength is -"+outputString.length());
                        protocolDecoderOutput.write(outputString);
                        System.out.println("At the Inside Return True");
                        System.out.println("Buffer state == "+ioBuffer);
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        System.out.println("IoBuffer Has Remaining did not enter while loop   -"+ioBuffer.hasRemaining());
        return false;
    }
}
