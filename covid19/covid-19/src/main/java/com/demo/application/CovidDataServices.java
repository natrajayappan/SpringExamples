/**
 * 
 */
package com.demo.application;

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

/**
 * @author PooraniArya
 *
 */
@Service
public class CovidDataServices {
	
	private static String VIRUS_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	
	private List<LocationDetails> locationList= new ArrayList<LocationDetails>();
	
	/**
	 * @return the locationList
	 */
	public List<LocationDetails> getLocationList() {
		return locationList;
	}

	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchCovidDetails() {
		List<LocationDetails> newStats= new ArrayList<LocationDetails>();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create(VIRUS_URL)).build();
		HttpResponse<String> response = null;
		
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			StringReader reader = new StringReader(response.body());
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
			
			for (CSVRecord record : records) {
				LocationDetails ld = new LocationDetails();
				ld.setState(record.get("Province/State"));
			    ld.setCountry(record.get("Country/Region"));
			    ld.setLatestCount(Integer.parseInt(record.get(record.size() - 1)));
			    ld.setYesterdayCount(Integer.parseInt(record.get(record.size() - 2)));
			    newStats.add(ld);
			}
			this.locationList = newStats; 
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
