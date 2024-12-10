package com.journalapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.journalapp.apiresponce.WheatherResponse;
import com.journalapp.cashe.AppCashe;
import com.journalapp.constants.Placeholders;

@Service
public class WheatherService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private AppCashe appCashe;

    @Value("${weather.api.key}")
    private String apiKey; //here need to deal with instances so remove static type
   
    
    
    public WheatherResponse getWheather(String city) {
        String finalString = appCashe.appCashe.get(AppCashe.keys.WEATHER_API.toString()).replace(Placeholders.CITY, city).replace(Placeholders.API_KEY, apiKey);
        /*
         * Lets say i want to take a post call how to do it
         */
        
//        HttpHeaders httpHeaders=new HttpHeaders();
//        httpHeaders.set("key", "value");
//        
//        
//        
//        User user=(User) User.builder().username("Suhas").password("Suhas123").build();
//        HttpEntity<User> httpEntity=new HttpEntity<>(user,httpHeaders); // we can provide headeer also
        
        ResponseEntity<WheatherResponse> res = restTemplate.exchange(finalString, HttpMethod.GET, null, WheatherResponse.class);
        return res.getBody();
    }
}
