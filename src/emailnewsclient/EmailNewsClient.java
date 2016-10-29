/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailnewsclient;


import javax.xml.bind.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
/**
 *
 * @author USER
 */
public class EmailNewsClient {

    /**
     * @param args the command line arguments
     */
    private final String USER_AGENT = "Mozilla/5.0";

//    public static void main(String[] args) {
    public void Main(String[] args) {
        try {
            // TODO code application logic here
            HttpURLConnectionRest http = new HttpURLConnectionRest();                        
            
            // param to delete a user
//            Object[] param = new Object[1];
//            Object[] param_values = new Object[1];
//            param[0]="userName";
//            param_values[0]="boxNameTest4";
//            String path="/userManager/deleteUser";
//            System.out.println(http.sendPost(path,param,param_values));
            
            //param to add a user
//            Object[] param = new Object[2];
//            Object[] param_values = new Object[2];
//            param[0]="userName";
//            param[1]="boxName";
//            param_values[0]="boxNameTest4";
//            param_values[1]=3452434;
//            String path="/userManager/addUser";
//            System.out.println(http.sendPost(path,param,param_values));
                        

            // param to setWriteAccess
//            Object[] param = new Object[1];
//            Object[] param_values = new Object[1];
//            param[0]="userName";
//            param_values[0]="CYRIL";
//            String path="/userManager/setReadAccess";            
//            System.out.println(http.sendPost(path, param, param_values));

            // param to get all users : http://localhost:8080/EmailNews/web/userManager/getallUsers/
            
            
//            String path="/userManager/getallUsers";            
//            System.out.println(http.sendGet(path, null, null));
//            System.out.println(http.getUrlText("/userManager/getUser/CYRIL"));
             
              // start the windows preview
              loginForm lg =new loginForm();
              
        
        } catch (Exception ex) {
            Logger.getLogger(EmailNewsClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
    
    public static void main(String[] args) throws Exception {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse("http://localhost:8080/EmailNews/web/userManager/getUser/CYRIL/");
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("utilisateur");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

		System.out.println("\nCurrent Element :" + nNode.getNodeName());

		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) nNode;

			System.out.println("User id : " + eElement.getElementsByTagName("id").item(0).getTextContent());
			System.out.println("userName : " + eElement.getElementsByTagName("userName").item(0).getTextContent());
			System.out.println("userType : " + eElement.getElementsByTagName("userType").item(0).getTextContent());
			System.out.println("ReadAccess : " + eElement.getElementsByTagName("ReadAccess").item(0).getTextContent());
			System.out.println("WriteAccess : " + eElement.getElementsByTagName("WriteAccess").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
