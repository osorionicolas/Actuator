package com.example.demo.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainController {
	
    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


	@GetMapping("/map")
	public Map<String,String> getObject() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("key", "value");
		return map;
	}
	
	@GetMapping({"/",""})
	public ModelAndView main()
	{
	    return new ModelAndView("main");
	}

	@GetMapping("/clima")
	public ResponseEntity<?> clima()
	{
		RestTemplate rest = new RestTemplate();
		ResponseEntity<String> response = rest.getForEntity("https://api.darksky.net/forecast/204ba8d0e5729fec00ebfe2199f61de2/-34.603722,-58.381592?exclude=minutely,flags&lang=es&units=auto", String.class);
	    String body = response.getBody();
		return new ResponseEntity<String>(body, response.getStatusCode());
	}
	
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }
	
	
}
