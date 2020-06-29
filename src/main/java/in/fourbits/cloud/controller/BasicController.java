package in.fourbits.cloud.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class BasicController {
	
	@GetMapping("/home")
    public String getAllEmployees() {
        return "Basic API test";
    }
}
