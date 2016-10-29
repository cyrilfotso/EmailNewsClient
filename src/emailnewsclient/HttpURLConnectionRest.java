/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailnewsclient;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
//import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author cyril
 */
public class HttpURLConnectionRest {

    private final String USER_AGENT = "Mozilla/5.0";
    public  final String BASE_URL="http://localhost:8080/EmailNews/web";

    // HTTP GET request
    public String getBaseUrl(){
        return BASE_URL;
    }
    public String sendGet(String path,Object[] param, Object [] paramValues) throws Exception {
        
        String urlParameters = "";
        
        if (param!=null && paramValues!=null) {
          for (int i = 0; i < param.length; i++) {
            if(i==param.length-1){
                urlParameters+=param[i]+"="+paramValues[i];
            }else{
                urlParameters+=param[i]+"="+paramValues[i]+"&";
            }            
         }  
          urlParameters="?"+urlParameters;
        }else{
            urlParameters="/";
        }
        
        String url = BASE_URL+path+urlParameters;
        System.out.println(url);
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        return response.toString();

    }
    
    public String getUrlText(String url) throws Exception {
        String urrl=BASE_URL+url;
        URL website = new URL(urrl);
        URLConnection connection = website.openConnection();
        StringBuilder response;
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream()))) {
            response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                response.append(inputLine);
        }

        return response.toString();
    }
    
    // HTTP POST request
    public String sendPost(String path,Object[] param, Object [] paramValues) throws Exception {

        String url = BASE_URL+path;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

//        String urlParameters = "userName=boxNameTest&boxName=12345";
        String urlParameters = "";
        
        for (int i = 0; i < param.length; i++) {
            if(i==param.length-1){
                urlParameters+=param[i]+"="+paramValues[i];
            }else{
                urlParameters+=param[i]+"="+paramValues[i]+"&";
            }            
        }
        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
    
    public String getSingleStringMessage(String msg) throws ParserConfigurationException, SAXException, IOException{
          DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
          Document parse = newDocumentBuilder.parse(new ByteArrayInputStream(msg.getBytes()));
          return  parse.getFirstChild().getTextContent();
    }
}
