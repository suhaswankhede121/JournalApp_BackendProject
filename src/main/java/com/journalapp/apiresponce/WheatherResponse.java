package com.journalapp.apiresponce;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class WheatherResponse{
	   
	    public Current current;
	    
	    @Getter
	    @Setter
	    public class Current{
		  
		    public int temperature;
		    @JsonProperty("weather_descriptions")
		    public ArrayList<String> weatherDescriptions;
		   
		}
	}
