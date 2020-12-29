package in.fourbits.cloud.controller;

import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.sap.db.jdbcext.DataSourceSAP;

@RestController
public class TenantController {
	
	@GetMapping("/callback/v1.0/dependencies")
    public String getDependencies() {
        return "Basic API test";
    }

    @PutMapping("/callback/v1.0/tenants/{tenantId}")
    public String subscribe(@PathVariable("tenantId")String tenantId) {
         System.out.println("tenant Id ===> "+tenantId);
         return "registered";
	   }
}
