package ru.jvn;


import lombok.extern.log4j.Log4j;
import org.apache.wss4j.dom.WSConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;

import javax.sql.DataSource;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Properties;

@Configuration
//@EnableJpaRepositories
@EnableTransactionManagement
@EnableWebMvc

//@EnableSpringConfigured


@ComponentScan(basePackages = {
        "ru.jvn.controller",
        "ru.jvn.repository",
        "ru.jvn.wsdl",
})

public class SpringConfig implements
        WebMvcConfigurer {

//    @Autowired
//    private Environment env;
//
//    @Autowired
//    private ApplicationContext ctx;


    @Bean
    public StandardServletMultipartResolver multipartResolver() {
        StandardServletMultipartResolver ssmpr = new StandardServletMultipartResolver();
        return ssmpr;
    }

    @Bean
    public DataSource MyConnectionProviderImpl() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://db:5432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() throws Exception {

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(MyConnectionProviderImpl());
        sessionFactory.setPackagesToScan("ru.jvn.model");
        sessionFactory.setHibernateProperties(hibernateProperties());
        System.out.println("Heloooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
        return sessionFactory;
    }


    @Bean
    public PlatformTransactionManager hibernateTransactionManager() throws Exception {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(
                "hibernate.hbm2ddl.auto", "update");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");


        return hibernateProperties;
    }

    @Bean
    public Wss4jSecurityInterceptor securityInterceptor() {
        Wss4jSecurityInterceptor wss4jSecurityInterceptor = new Wss4jSecurityInterceptor();
        wss4jSecurityInterceptor.setSecurementActions("Timestamp UsernameToken");
        wss4jSecurityInterceptor.setSecurementUsername("soap");
        wss4jSecurityInterceptor.setSecurementPassword("1234");
        wss4jSecurityInterceptor.setSecurementPasswordType(WSConstants.PW_TEXT);
        return wss4jSecurityInterceptor;
    }

    @Bean
    public ClientInterceptor logInterceptor(){
    return new ClientInterceptor() {
        public  void printFormattedXML(String xml) throws Exception
        {

            Source xmlInput = new StreamSource(new StringReader(xml));
            StringWriter stringWriter = new StringWriter();
            StreamResult xmlOutput = new StreamResult(stringWriter);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", 5);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(xmlInput, xmlOutput);
            String xmlString = xmlOutput.getWriter().toString();
            System.out.println(xmlString);
        }
        @Override
        public boolean handleResponse(MessageContext messageContext) throws WebServiceClientException {
            System.out.println("### SOAP RESPONSE ###");
            try {
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                messageContext.getResponse().writeTo(buffer);
                String payload = buffer.toString(java.nio.charset.StandardCharsets.UTF_8.name());
                System.out.println(payload);
                printFormattedXML(payload);
            } catch (IOException e) {
                throw new WebServiceClientException("Can not write the SOAP response into the out stream", e) {
                    private static final long serialVersionUID = -7118480620416458069L;
                };
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return true;
        }

        @Override
        public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {

            System.out.println("### SOAP REQUEST ###");
            try {
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                messageContext.getRequest().writeTo(buffer);
                String payload = buffer.toString(java.nio.charset.StandardCharsets.UTF_8.name());
                System.out.println(payload);
                printFormattedXML(payload);
            } catch (IOException e) {
                throw new WebServiceClientException("Can not write the SOAP request into the out stream", e) {
                    private static final long serialVersionUID = -7118480620416458069L;
                };
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return true;
        }

        @Override
        public boolean handleFault(MessageContext messageContext) throws WebServiceClientException {
            System.out.println("### SOAP FAULT ###");
            try {
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                messageContext.getResponse().writeTo(buffer);
                String payload = buffer.toString(java.nio.charset.StandardCharsets.UTF_8.name());
                System.out.println(payload);
            } catch (IOException e) {
                throw new WebServiceClientException("Can not write the SOAP fault into the out stream", e) {
                    private static final long serialVersionUID = 3538336091916808141L;
                };
            }

            return true;
        }

        @Override
        public void afterCompletion(MessageContext messageContext, Exception e) throws WebServiceClientException {
            System.out.println("a");
        }
    };
    }
    ////////////////////

    @Bean
    public Jaxb2Marshaller marshaller() throws Exception {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("ru.jvn.wsdl");
        return marshaller;
    }

    @Bean
    public SecureRusgardClient secureRusgardClient(Jaxb2Marshaller marshaller) throws Exception {
        InstallCert.main(new String[]{"rusgard"});
        //System.setProperty("javax.net.ssl.keyStore", "/opt/java/openjdk/lib/security/jssecacerts");
        System.setProperty("javax.net.ssl.keyStore", "/var/lib/jetty/.keystore");
        System.setProperty("javax.net.ssl.keyStorePassword", "");
        System.setProperty("javax.net.ssl.keyStoreType", "JKS");
        //System.setProperty("javax.net.ssl.trustStore", "/opt/java/openjdk/lib/security/jssecacerts");
        System.setProperty("javax.net.ssl.trustStore", "/var/lib/jetty/.keystore");
        System.setProperty("javax.net.ssl.trustStorePassword", "");
        System.setProperty("javax.net.ssl.trustStoreType", "JKS");
        //System.out.println(System.getProperty("javax.net.ssl.keyStore"));
        //System.out.println(System.getProperty("javax.net.ssl.trustStore"));

        //InstallCert.main(new String[]{"rusgard"});

        //System.out.println(System.getProperty("javax.net.ssl.keyStore"));
        //System.out.println(System.getProperty("javax.net.ssl.trustStore"));

        //System.setProperty("javax.net.debug", "all");
        SecureRusgardClient client = new SecureRusgardClient();
        client.setDefaultUri("https://rusgardserver/LNetworkServer/LNetworkService.svc");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        ClientInterceptor[] interceptors = new ClientInterceptor[]{securityInterceptor(), logInterceptor()};
        client.setInterceptors(interceptors);
        return client;
    }

    @Bean
    public RusgardClient RusgardClient(Jaxb2Marshaller marshaller) {
        RusgardClient client = new RusgardClient();
        client.setDefaultUri("http://rusgardserver/LNetworkServer/LNetworkService.svc");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}
