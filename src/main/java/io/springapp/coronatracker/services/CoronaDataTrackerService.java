package io.springapp.coronatracker.services;

import io.springapp.coronatracker.models.LocationData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaDataTrackerService {
    private static String VIRUS_DATA="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
  private   List<LocationData> casesData=new ArrayList<>();

    public List<LocationData> getCasesData() {
        return casesData;
    }

    @PostConstruct
     @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() throws IOException, InterruptedException {
        List<LocationData> newData=new ArrayList<>();
        HttpClient client=HttpClient.newHttpClient();
        HttpRequest request=HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA))
                .build();
        HttpResponse<String> response=client.send(request,HttpResponse.BodyHandlers.ofString());

    StringReader csvReader=new StringReader(response.body());
   Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
   // Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader("ID", "CustomerNo", "Name").parse(in);

        for (CSVRecord record : records) {
            LocationData locationData=new LocationData();
            locationData.setState(record.get("Province/State"));
            locationData.setCountry(record.get("Country/Region"));
            int latestCases=Integer.parseInt(record.get(record.size()-1));
            int prevDayCases= Integer.parseInt(record.get(record.size()-2));
            locationData.setDiffBwLastDay(latestCases-prevDayCases);
            locationData.setTotalActiveCases(latestCases);


            newData.add(locationData);

            }

        this.casesData=newData;


    }

}
