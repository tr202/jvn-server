package ru.jvn.enums;

public enum RusgardService {

    DATA("http://www.rusguardsecurity.ru/ILDataService/"),
    CONFIG("http://www.rusguardsecurity.ru/ILNetworkConfigurationService/"),
    SUBSCRIBE("http://www.rusguardsecurity.ru/ILSubnetworkSubscribeService/"),
    MONITORING("http://www.rusguardsecurity.ru/ILMonitoringService/"),

    ;
    final String translateName;

    RusgardService(String translateName) {
        this.translateName = translateName;
    }

    public String getTranslateName() {
        return translateName;
    }
}
