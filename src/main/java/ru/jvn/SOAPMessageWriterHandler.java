package ru.jvn;

import java.io.ByteArrayOutputStream;
import java.util.Set;

import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class SOAPMessageWriterHandler implements SOAPHandler<SOAPMessageContext> {

    public boolean handleMessage(SOAPMessageContext smc) {

        Boolean outboundProperty = (Boolean) smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        SOAPMessage message = smc.getMessage();

        try
        {
            if (!outboundProperty.booleanValue()) {
                System.out.println("SOAP Response : ");
                message.writeTo(System.out);
            } else {
                System.out.println("SOAP Request : ");
                message.writeTo(System.out);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return outboundProperty;
    }

    public Set getHeaders() {
        return null;
    }

    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    public void close(MessageContext context) {
    }

//    public static void printFormattedXML(SOAPMessage message) throws Exception
//    {
//        ByteArrayOutputStream bout = new ByteArrayOutputStream();
//        message.writeTo(bout);
//        String xml = bout.toString();
//
//        Source xmlInput = new StreamSource(new StringReader(xml));
//        StringWriter stringWriter = new StringWriter();
//        StreamResult xmlOutput = new StreamResult(stringWriter);
//        TransformerFactory transformerFactory = TransformerFactory.newInstance();
//        transformerFactory.setAttribute("indent-number", 5);
//        Transformer transformer = transformerFactory.newTransformer();
//        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//        transformer.transform(xmlInput, xmlOutput);
//        String xmlString = xmlOutput.getWriter().toString();
//
//        System.out.println(xmlString);
//    }
}