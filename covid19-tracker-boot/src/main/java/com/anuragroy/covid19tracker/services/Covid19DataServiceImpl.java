package com.anuragroy.covid19tracker.services;

import com.anuragroy.covid19tracker.models.LocationStats;
import com.anuragroy.covid19tracker.models.Totals;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class Covid19DataServiceImpl implements Covid19DataService{

    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";
    private List<LocationStats> allStats = new ArrayList<>();

    @Override
    public List<LocationStats> getAllStats() {
        return allStats;
    }

    @Override
    public Totals getTotalCases() {
        Totals totals = new Totals();
        totals.setTotalReportedCases(allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum());
        totals.setTotalNewCases(allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum());
        return totals;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() throws IOException {
        List<LocationStats> newStats = new ArrayList<>();

        HttpGet request = new HttpGet(VIRUS_DATA_URL);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
//            System.out.println(response.getStatusLine().toString());
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                StringReader csvBodyReaders = new StringReader(result);
                Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReaders);
                for (CSVRecord record : records) {
                    LocationStats locationStat = new LocationStats();
                    locationStat.setState(record.get("Province/State"));
                    locationStat.setCountry(record.get("Country/Region"));
                    int latestCases = Integer.parseInt(record.get(record.size() - 1));
                    int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
                    locationStat.setLatestTotalCases(latestCases);
                    locationStat.setDiffFromPrevDay(latestCases - prevDayCases);
                    newStats.add(locationStat);
                }
                this.allStats = newStats;
            }
        }
    }

}
