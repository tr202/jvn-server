package ru.jvn;


import com.sun.istack.NotNull;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import ru.jvn.enums.RusgardService;
import ru.jvn.wsdl.GetAcsEmployees;
import ru.jvn.wsdl.GetAcsEmployeesResponse;
import ru.jvn.wsdl.Ping;
import ru.jvn.wsdl.PingResponse;

import javax.annotation.Nullable;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;


public class SecureRusgardClient extends WebServiceGatewaySupport {

    //Jaxb2Marshaller jaxb2Marshaller;


    //private static final Logger log = LoggerFactory.getLogger(RusgardClient.class);

//    public enum RusgardService {
//        DATA("http://www.rusguardsecurity.ru/ILDataService/"),
//        CONFIG("http://www.rusguardsecurity.ru/ILNetworkConfigurationService"),
//        SUBSCRIBE("http://www.rusguardsecurity.ru/ILSubnetworkSubscribeService/"),
//        MONITORING("http://www.rusguardsecurity.ru/ILMonitoringService/"),
//
//        ;
//        final String translateName;
//
//        RusgardService(String translateName) {
//            this.translateName = translateName;
//        }
//    }

    public Object getResponse(Object request, RusgardService service){
        return getWebServiceTemplate()
                .marshalSendAndReceive(this.getDefaultUri(), request,
                        new SoapActionCallback(service.getTranslateName() + request.getClass().getSimpleName()));
    }

//    public Object getResponse(Object request, String service){
//        return getWebServiceTemplate()
//                .marshalSendAndReceive("https://rusgardserver/LNetworkServer/LNetworkService.svc", request,
//                        new SoapActionCallback("http://www.rusguardsecurity.ru/" + service + "/" + request.getClass().getSimpleName()));
//    }

    public GetAcsEmployeesResponse getAcsEmployees() {

        //setMarshaller(new Jaxb2Marshaller());
        //setUnmarshaller(new Jaxb2Marshaller());

        GetAcsEmployees request = new GetAcsEmployees();
        System.out.println(request + "    ##$#$#$#$#$#$#$#$#$#$#$#$#");
        //request.setName(country)
        //log.info("Requesting location for " + country);

        GetAcsEmployeesResponse response = (GetAcsEmployeesResponse) getWebServiceTemplate()
                .marshalSendAndReceive("https://rusgardserver/LNetworkServer/LNetworkService.svc", request,
                        new SoapActionCallback(
                                "http://www.rusguardsecurity.ru/ILDataService/GetAcsEmployees"));

        return response;
    }


    public void printSOAPMessage(SOAPMessage soapMessage) throws Exception {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            Source sourceContent = soapMessage.getSOAPPart().getContent();
            System.out.print("\nSOAPage = ");
            StreamResult result = new StreamResult(System.out);
            transformer.transform(sourceContent, result);
        } catch (TransformerException e) {
            System.out.println("qqqqqqqqqqqqqq");
        } catch (SOAPException e) {
            System.out.println("wwwwwwwwwwwwwwww");
        }
    }

}