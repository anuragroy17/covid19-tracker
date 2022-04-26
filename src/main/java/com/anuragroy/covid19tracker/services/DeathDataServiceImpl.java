package com.anuragroy.covid19tracker.services;

import com.anuragroy.covid19tracker.models.DeathLocationStats;
import com.anuragroy.covid19tracker.models.TotalDeaths;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class DeathDataServiceImpl implements DeathDataService{

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    @Value("${url.deathUrl}")
    private String VIRUS_DATA_URL_DEATH;

    private List<DeathLocationStats> deathLocationStats = new ArrayList<>();

    private String lastDate;

    @Override
    public List<DeathLocationStats> getDeathLocationStats() {
        return deathLocationStats;
    }

    @Override
    public TotalDeaths getTotalDeathCases() {
        TotalDeaths totals = new TotalDeaths();
        totals.setTotalReportedDeaths(deathLocationStats.stream().mapToInt(stat -> stat.getLatestTotalDeaths()).sum());
        totals.setTotalNewDeaths(deathLocationStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum());
        totals.setLastDate(lastDate);
        return totals;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchDeathsData() throws IOException {
        List<DeathLocationStats> newStats = new ArrayList<>();

        HttpGet request = new HttpGet(VIRUS_DATA_URL_DEATH);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                StringReader csvBodyReaders = new StringReader(result);
                Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().withSkipHeaderRecord(false).parse(csvBodyReaders);
                Set<String> headers = records.iterator().next().toMap().keySet();
                setLastDate(headers);
                for (CSVRecord record : records) {
                    DeathLocationStats locationStat = new DeathLocationStats();
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
                    locationStat.setLatestTotalDeaths(latestCases);
                    locationStat.setDiffFromPrevDay(latestCases - prevDayCases);
                    newStats.add(locationStat);
                }
                this.deathLocationStats = newStats;
            }
        }
    }

    private void setLastDate(Set<String> headers) {
        SimpleDateFormat df1 = new SimpleDateFormat("MM/dd/yy");
        SimpleDateFormat df2 = new SimpleDateFormat("dd MMM yyyy");
        Date startDate;
        try {
            startDate = df1.parse(headers.stream().skip(headers.size() - 1).findFirst().get());
            lastDate = df2.format(startDate);
        } catch (ParseException e) {
            lastDate = "today";
        }
    }

}
