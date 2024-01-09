package com.coronavirus.example.service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.coronavirus.example.model.corona;



@Service
public class CoronaVirusDataservice {
	
private List<corona>allstates=new ArrayList<corona>();
	
	
	public List<corona> getAllstates() {
		return allstates;
	}
	public void setAllstates(List<corona> allstates) {
		this.allstates = allstates;
	}
	private static String VIRUS_DATA_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
	@PostConstruct
	@Scheduled(cron = "* * * * * *")
	public void fetchVirusData() throws IOException, InterruptedException
	{
	List<corona>newstates=new ArrayList<corona>();
	HttpClient client=HttpClient.newHttpClient();
	HttpRequest request=HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_URL)).build();
	HttpResponse<String>httpResponse=client.send(request, HttpResponse.BodyHandlers.ofString());
	
	StringReader csvBodyreader=new StringReader(httpResponse.body());
	Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyreader);
	for (CSVRecord record : records) 
	{
		corona cor=new corona();
	    cor.setState(record.get("Province/State"));
	    cor.setCountry(record.get("Country/Region"));
	    int latestCase=Integer.parseInt(record.get(record.size()-1));
	    int PrevCase=Integer.parseInt(record.get(record.size()-2));
	    cor.setLatestTotalDeaths(latestCase);
	    cor.setDifferFromPrevious(latestCase-PrevCase);
	    System.out.println(cor);
	    
	    newstates.add(cor);
	    
	}
	this.allstates=newstates;
		
	}
}
