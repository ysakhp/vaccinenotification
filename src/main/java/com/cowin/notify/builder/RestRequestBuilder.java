package com.cowin.notify.builder;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.cowin.notify.model.Center;
import com.cowin.notify.model.CenterList;

@Service
public class RestRequestBuilder {
	@Autowired
	private WebClient.Builder webClientBuilder;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private Logger log;

	public List<Center> buildGetRequest(int pincode, String date) {

		String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode=" + pincode
				+ "&date=" + date;

		List<Center> centers = null;
		try {

			HttpHeaders headers = new HttpHeaders();
			headers.add("user-agent", "Application");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<>(headers);
			log.info("RestRequest invoking "+url);
			String centerList = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
			log.info("Details fetched from external API " + centerList);

//			CenterList res = restTemplate.getForObject(url, CenterList.class);
//			centers = res.getCenters();
		} catch (RestClientException e) {
			log.error("Rest Client Exception " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			log.error("Ecxeption in RestBuilder " + e.getMessage());
			e.printStackTrace();
		}
		return centers;

	}

}
