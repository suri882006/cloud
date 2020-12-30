package in.fourbits.cloud.controller;

import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import com.sap.db.jdbcext.DataSourceSAP;

@RestController
public class BasicController {
	
	@GetMapping("/home")
    public String getAllEmployees() {
        return "Basic API test";
    }

    @GetMapping("/protected")
    //@PreAuthorize("#oauth2.hasScope('uaa.user')")
    public String protectedResource() {
        return "This is a protected resource";
    }

    @GetMapping("/tokens")
    public void getTokens(final HttpServletRequest request, final HttpServletResponse response ) throws IOException {
        response.setContentType("text/plain");
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);

            response.getOutputStream().println(key+" : "+value);
        }
    }

    @GetMapping("/hanaconn")
    public String getConnectivity() {
         Connection connection = null;
         String response ="default";
	      System.out.println("Hellllo");
	      try {       
	    	  DataSourceSAP dataSourceSAP = new DataSourceSAP();
		      dataSourceSAP.setUser("GSTRMDC_1_ADNLOYQI9LZDMQA6IZ1EGBYI5_RT");
		      dataSourceSAP.setPassword("Tv2HL.jI9lQ8qFw87CJGSerdpPEHnlt-mp4exnvwkqjsoJjJp8kTwQ9cL4uuVwRtoeDV.6HbDsxYOA6lb74Enbfv4mACqAnwui.t4rXx6m4RBsk95boFrKxzmQ1HM2Wt");
		      
		      dataSourceSAP.setServerName("7e728ca0-d02b-41e9-811d-1880be197234.hana.trial-eu10.hanacloud.ondemand.com");
		      dataSourceSAP.setPort(443);
		      dataSourceSAP.setUrl("jdbc:sap://7e728ca0-d02b-41e9-811d-1880be197234.hana.trial-eu10.hanacloud.ondemand.com:443?encrypt=true&validateCertificate=false&currentschema=GSTRMDC_1");
		      dataSourceSAP.setSchema("GSTRMDC_1");
		      connection = dataSourceSAP.getConnection();
			                  
	      } catch (SQLException e) {
	         System.err.println("Connection Failed:");
	         System.err.println(e);
	         return "Error SQLException";
	      }
	      if (connection != null) {
	         try {
	            System.out.println("Connection to HANA successful!");
	            Statement stmt = connection.createStatement();
	            ResultSet resultSet = stmt.executeQuery("Select * from \"GSTRMDC.db::HEADER\"");
	            resultSet.next();
	            response = resultSet.getString(1);
	            System.out.println(response);
	       } catch (SQLException e) {
              System.err.println("Query failed!");
              return "Error SQLException";
	       }
         }
         return response;
	   }
}
