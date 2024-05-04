package ru.jvn;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import ru.jvn.wsdl.Ping;
import ru.jvn.wsdl.PingResponse;

public class RusgardClient extends WebServiceGatewaySupport {
    public PingResponse ping() {
        Ping request = new Ping();
        System.out.println(request.toString());
        PingResponse response = (PingResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://rusgardserver/LNetworkServer/LNetworkService.svc", request,
                        new SoapActionCallback("http://www.rusguardsecurity.ru/ILMonitoringService/Ping"));
        return response;
    }

}
