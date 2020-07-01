package in.fourbits.cloud.controller;

import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.web.bind.annotation.GetMapping;
import com.sap.db.jdbcext.DataSourceSAP;

@RestController
public class BasicController {
	
	@GetMapping("/home")
    public String getAllEmployees() {
        return "Basic API test";
    }

    @GetMapping("/hanaconn")
    public String getConnectivity() {
         Connection connection = null;
         String response ="default";
	      System.out.println("Hellllo");
	      try {       
	    	  DataSourceSAP dataSourceSAP = new DataSourceSAP();
		      dataSourceSAP.setUser("USR_ANAGTI6Q2OI6JFTMGZBWOEKNP");
		      dataSourceSAP.setPassword("Lk4N3-hVj6Pz_jD34dt-AH4xhgyOJOVj8hD_sN1hS3gyH2LQ35dXxssevbo0ifO8FQLgaP3dnIW6nzGmdhJIbraTR9NlmOJjhbS_7e2.acJV7D8O31O3YKwqbdozyedo");
		      
		      dataSourceSAP.setServerName("zeus.hana.prod.us-east-1.whitney.dbaas.ondemand.com");
		      dataSourceSAP.setPort(21022);
		      dataSourceSAP.setUrl("jdbc:sap://zeus.hana.prod.us-east-1.whitney.dbaas.ondemand.com:21022?encrypt=true&validateCertificate=true&currentschema=USR_ANAGTI6Q2OI6JFTMGZBWOEKNP");
		      dataSourceSAP.setSchema("USR_ANAGTI6Q2OI6JFTMGZBWOEKNP");
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
	            ResultSet resultSet = stmt.executeQuery("Select current_timestamp from dummy");
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
