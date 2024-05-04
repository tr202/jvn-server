package ru.jvn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import ru.jvn.wsdl.GetAcsEmployees;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.JAXBException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {

    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    public static void main(String[] args) throws JAXBException, NoSuchAlgorithmException {

        System.out.println("Hello world!");

//        Person person = new Person(123L, "Dow");
//        SaveAcsEmployee saveEmployee = new SaveAcsEmployee();
//        saveEmployee.setData(new JAXBElement<>());
//       // employee.setGroupID("asdasd");
//
//        JAXBContext context = JAXBContext.newInstance(AcsEmployee.class);
//        Marshaller mar = context.createMarshaller();
//        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//       // mar.marshal(employee, new File("./emplyuee"));
        //RusgardClient rusgardClient = new RusgardClient();
        //System.out.println(rusgardClient.getAcsEmployees());
        String hash = "35454B055CC325EA1AF2126E27707052";
        String password = "49838Test-v2";

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();

        System.out.println(myHash);

        private AddAcsEmployeeResponse addAcsEmployee(String acsGroupId, String md5) throws NoSuchAlgorithmException {
            ObjectFactory objectFactory = new ObjectFactory();
            AddAcsEmployee addAcsEmployee = objectFactory.createAddAcsEmployee();
            addAcsEmployee.setEmployeeGroupID(acsGroupId);
            AcsEmployeeSaveData saveData = objectFactory.createAcsEmployeeSaveData();
            saveData.setFirstName(objectFactory.createAcsEmployeeSlimBaseFirstName("first"));
            saveData.setLastName(objectFactory.createAcsEmployeeSlimBaseLastName("last"));
            saveData.setSecondName(objectFactory.createAcsEmployeeSlimBaseSecondName("second"));
            saveData.setComment(objectFactory.createAcsEmployeeSlimBaseComment(md5));
            objectFactory.createAddAcsEmployeeData(saveData);
            addAcsEmployee.setData(objectFactory.createAddAcsEmployeeData(saveData));

            AddAcsEmployeeResponse addAcsEmployeeResponse =
                    (AddAcsEmployeeResponse) secureRusgardClient.getResponse(addAcsEmployee, RusgardService.CONFIG);

            return addAcsEmployeeResponse;

        }





    }
}