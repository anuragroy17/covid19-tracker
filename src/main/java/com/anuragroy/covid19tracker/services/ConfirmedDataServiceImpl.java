package com.anuragroy.covid19tracker.services;

import com.anuragroy.covid19tracker.models.ConfirmedLocationStats;
import com.anuragroy.covid19tracker.models.TotalConfirmed;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConfirmedDataServiceImpl implements ConfirmedDataService {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    @Value("${url.confirmedUrl}")
    private String VIRUS_DATA_URL_CONFIRMED;

    private List<ConfirmedLocationStats> confirmedLocationStats = new ArrayList<>();

    @Override
    public List<ConfirmedLocationStats> getConfirmedLocationStats() {
        return confirmedLocationStats;
    }

    @Override
    public TotalConfirmed getTotalConfirmedCases() {
        TotalConfirmed totals = new TotalConfirmed();
        totals.setTotalReportedCases(confirmedLocationStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum());
        totals.setTotalNewCases(confirmedLocationStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum());
        return totals;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchConfirmedData() throws IOException {
        List<ConfirmedLocationStats> newStats = new ArrayList<>();

        HttpGet request = new HttpGet(VIRUS_DATA_URL_CONFIRMED);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
//            System.out.println(response.getStatusLine().toString());
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                StringReader csvBodyReaders = new StringReader(result);
                Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReaders);
                for (CSVRecord record : records) {
                    ConfirmedLocationStats locationStat = new ConfirmedLocationStats();
                    locationStat.setState(record.get("Province/State"));
                    locationStat.setCountry(record.get("Country/Region"));
                    int latestCases = 0;
                    int prevDayCases = 0;
                    if(record.get(record.size() - 1).equals("")){
                        latestCases = 0;
                    } else {
                        latestCases = Integer.parseInt(record.get(record.size() - 1));
                    }
                    if(record.get(record.size() - 2).equals("")){
                        prevDayCases = 0;
                    } else {
                        prevDayCases = Integer.parseInt(record.get(record.size() - 2));
                    }
                    locationStat.setLatestTotalCases(latestCases);
                    locationStat.setDiffFromPrevDay(latestCases - prevDayCases);
                    newStats.add(locationStat);
                }
                this.confirmedLocationStats = newStats;
            }
        }
    }


}
