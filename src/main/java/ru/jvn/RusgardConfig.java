//package ru.jvn;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.oxm.jaxb.Jaxb2Marshaller;
//
//
//@Configuration
//public class RusgardConfig {
//
//    @Bean
//    public Jaxb2Marshaller marshaller() {
//        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//        // this package must match the package in the <generatePackage> specified in
//        // pom.xml
//        marshaller.setContextPath("ru.jvn.wsdl");
//        System.out.println("Mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
//        return marshaller;
//    }
//
//    @Bean
//    public RusgardClient rusgardClient(Jaxb2Marshaller marshaller) {
//        RusgardClient client = new RusgardClient();
//        client.setDefaultUri("https://rusgardserver/LNetworkServer/LNetworkService.svc");
//        client.setMarshaller(marshaller);
//        client.setUnmarshaller(marshaller);
//        return client;
//    }
//
//}

