package ru.jvn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.jvn.RusgardClient;
import ru.jvn.SecureRusgardClient;
import ru.jvn.enums.RusgardService;
import ru.jvn.wsdl.*;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.List;
import java.util.function.Supplier;

import static jdk.nashorn.internal.objects.NativeString.toLowerCase;


@Controller
@RequestMapping(value = "/test")
public class TestController {

//    @Autowired
//    SessionFactory sessionFactory;

    @Autowired
    RusgardClient rusgardClient;

    @Autowired
    SecureRusgardClient secureRusgardClient;

//    @Autowired
//    DataSource dataSource;

    //    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    @Transactional
//    public String getTest() {
//        User user = new User(UUID.randomUUID(), "peta", "feta");
//        Session currentSession = sessionFactory.getCurrentSession();
//        currentSession.saveOrUpdate(user);
//        //Session session = sessionFactory.openSession();
//        //session.persist("user",user);
//        //session.close();
//        return "ok";
//    }
//    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    @Transactional
//    public String createUser(@RequestBody User user) {
//        Session currentSession = sessionFactory.getCurrentSession();
//        currentSession.saveOrUpdate(user);
//        return "Response-sec  " + user.getId();
//    }



    private String getMd5(String data, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update((data+salt).getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }
    private AcsEmployeeGroup addGroup(AcsEmployeeGroup rootGroup, String name, String salt) throws NoSuchAlgorithmException {
        ObjectFactory objectFactory = new ObjectFactory();
        AddAcsEmployeeGroup addAcsEmployeeGroup = objectFactory.createAddAcsEmployeeGroup();
        addAcsEmployeeGroup.setParentId(objectFactory.createAddAcsEmployeeGroupParentId(rootGroup.getID()));
        addAcsEmployeeGroup.setName(objectFactory.createAddAcsEmployeeGroupName(name));
        addAcsEmployeeGroup.setGroupCode(objectFactory.createAddAcsEmployeeGroupGroupCode(getMd5(name,salt)));
        AddAcsEmployeeGroupResponse addAcsEmployeeGroupResponse = (AddAcsEmployeeGroupResponse) secureRusgardClient.getResponse(addAcsEmployeeGroup,RusgardService.CONFIG);
        return addAcsEmployeeGroupResponse.getAddAcsEmployeeGroupResult().getValue();

    }

    private static <T> T NullIfNpe(Supplier<T> f) {
        try {
            return f.get();
        } catch (NullPointerException e) {
            return null;
        }
    }


    @RequestMapping(value = "/makeGroups",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Transactional
    public List<String> makeGroups() throws NoSuchAlgorithmException {


        String salt = "ec-server";
        String rootGroupName = "Вся школа";
        String employeeGroupName = "Сотрудники";
        String studentsGroupName = "Ученики";
        String parentsGroupName = "Родители";
        String guestsGroupName = "Гости";


        ObjectFactory objectFactory = new ObjectFactory();
        GetAcsEmployeeGroups getAcsEmployeeGroups = objectFactory.createGetAcsEmployeeGroups();
        GetAcsEmployeeGroupsResponse response = (GetAcsEmployeeGroupsResponse) secureRusgardClient.getResponse(getAcsEmployeeGroups, RusgardService.DATA);
        List<AcsEmployeeGroup> grouplist = response.getGetAcsEmployeeGroupsResult().getValue().getAcsEmployeeGroup();

        AcsEmployeeGroup rootGroup = null;

        for (AcsEmployeeGroup acsEmployeeGroup : grouplist) {
            if (Objects.equals(NullIfNpe(()->toLowerCase(acsEmployeeGroup.getGroupCode().getValue())), toLowerCase(getMd5(rootGroupName, salt)))){
                rootGroup = acsEmployeeGroup;
                break;
            }
            System.out.println(acsEmployeeGroup.getGroupCode().getValue());
            System.out.println(acsEmployeeGroup.getID());
            System.out.println(acsEmployeeGroup.getName().getValue());
            System.out.println(getMd5(rootGroupName,salt));
        }

        if (rootGroup == null) {
            AddAcsEmployeeGroup addAcsEmployeeGroup = objectFactory.createAddAcsEmployeeGroup();
            addAcsEmployeeGroup.setName(objectFactory.createAddAcsEmployeeGroupName(rootGroupName));
            addAcsEmployeeGroup.setGroupCode(objectFactory.createAddAcsEmployeeGroupGroupCode(getMd5(rootGroupName,salt)));
            AddAcsEmployeeGroupResponse addAcsEmployeeGroupResponse = (AddAcsEmployeeGroupResponse) secureRusgardClient.getResponse(addAcsEmployeeGroup,RusgardService.CONFIG);
            rootGroup = addAcsEmployeeGroupResponse.getAddAcsEmployeeGroupResult().getValue();
        }

        System.out.println(rootGroup.getName());
        System.out.println((rootGroup.getGroupCode()));

        AcsEmployeeGroup employeesGroup = null;
        AcsEmployeeGroup studentsGroup = null;
        AcsEmployeeGroup parentsGroup = null;
        AcsEmployeeGroup guestsGroup = null;



        for (AcsEmployeeGroup acsEmployeeGroup : rootGroup.getEmployeeGroups().getValue().getAcsEmployeeGroup()){
            System.out.println(acsEmployeeGroup.getName());
            if (Objects.equals(toLowerCase(Objects.requireNonNull(NullIfNpe(() -> acsEmployeeGroup.getGroupCode().getValue()))), toLowerCase(getMd5(employeeGroupName, salt)))) {employeesGroup = acsEmployeeGroup;}
            if (Objects.equals(toLowerCase(Objects.requireNonNull(NullIfNpe(() -> acsEmployeeGroup.getGroupCode().getValue()))), toLowerCase(getMd5(studentsGroupName, salt)))) {studentsGroup = acsEmployeeGroup;}
            if (Objects.equals(toLowerCase(Objects.requireNonNull(NullIfNpe(() -> acsEmployeeGroup.getGroupCode().getValue()))), toLowerCase(getMd5(parentsGroupName, salt)))) {parentsGroup = acsEmployeeGroup;}
            if (Objects.equals(toLowerCase(Objects.requireNonNull(NullIfNpe(() -> acsEmployeeGroup.getGroupCode().getValue()))), toLowerCase(getMd5(guestsGroupName, salt)))) {guestsGroup = acsEmployeeGroup;}
        }

        if (employeesGroup == null ) {addGroup(rootGroup, employeeGroupName, salt);}
        if (studentsGroup == null ) {addGroup(rootGroup, studentsGroupName, salt);}
        if (parentsGroup == null ) {addGroup(rootGroup, parentsGroupName, salt);}
        if (guestsGroup == null ) {addGroup(rootGroup, guestsGroupName, salt);}



//        FindGroups findGroups = objectFactory.createFindGroups();
//        findGroups.setName(objectFactory.createFindGroupsName(rootGroupName));
//        FindGroupsResponse response = (FindGroupsResponse) secureRusgardClient.getResponse(findGroups, RusgardService.DATA);
//        System.out.println(response.getFindGroupsResult().getValue().getGuid());

        List<String> result = new ArrayList<>();


//        AddAcsEmployeeGroup request = objectFactory.createAddAcsEmployeeGroup();
//        JAXBElement<String> name = objectFactory.createFindGroupsName("Root");
//        //JAXBElement<String> name1 = objectFactory.createPropertyValueName("Root");
//
//        request.setName(name);
//
//        AddAcsEmployeeGroupResponse response = (AddAcsEmployeeGroupResponse) secureRusgardClient.getResponse(request, RusgardService.CONFIG);
//        System.out.println(response.getAddAcsEmployeeGroupResult().getValue().getGroupCode());

        return result;
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Transactional
    public List<String> createUser() {


        //Session currentSession = sessionFactory.getCurrentSession();
        //currentSession.saveOrUpdate(user);

        //RusgardClient rusgardClient = new RusgardClient();
        //GetAcsEmployeesResponse response = secureRusgardClient.getAcsEmployees();

        GetAcsEmployeesResponse response = (GetAcsEmployeesResponse) secureRusgardClient.getResponse(new GetAcsEmployees(), RusgardService.DATA);


        System.out.println(response.getGetAcsEmployeesResult().toString());
        JAXBElement<ArrayOfAcsEmployeeInfo> inf = response.getGetAcsEmployeesResult().getValue().getEmployees();
        ArrayOfAcsEmployeeInfo inf1 = inf.getValue();
        java.util.ArrayList<String> result = new ArrayList<>();
        for (AcsEmployeeInfo c : inf1.getAcsEmployeeInfo()) {
            System.out.println(c.getID().toString() + " " + c.getLastName().getValue() + " " + c.getFirstName().getValue() + " " + c.getGroupName().getValue());
            result.add(c.getID().toString() + " " + c.getLastName().getValue() + " " + c.getFirstName().getValue() + " " + c.getGroupName().getValue());
        }


//        inf1.getAcsEmployeeInfo().forEach(c->{
//            //result.putIfAbsent(c.getFirstName().getValue(), (c.getLastName().getValue() +" "+c.getFirstName().getValue() + " " + c.getGroupName().getValue()));
//            (c.getID().toString()+" "+c.getLastName().getValue() +" "+c.getFirstName().getValue() + " " + c.getGroupName().getValue());
//            System.out.println(c.getID().toString()+" "+c.getLastName().getValue() +" "+c.getFirstName().getValue() + " " + c.getGroupName().getValue());
//        });
//        //System.out.println(lsa.getItemType());


//        Arrays.asList(inf.getEmployees()).forEach(c -> {
//            AcsEmployeeInfo empInf = (AcsEmployeeInfo) c.getValue().getAcsEmployeeInfo();
//            System.out.println(empInf.getGroupCode());
//            System.out.println(empInf.getGroupName());
//            System.out.println(empInf.getFirstName());
//            System.out.println(empInf.getLastName());
//
//            //System.out.println(c.getValue().getAcsEmployeeInfo());
//        });
//        System.out.println(res.getValue());
//        System.out.println(res.getValue().getClass());


        return result;
    }
}
