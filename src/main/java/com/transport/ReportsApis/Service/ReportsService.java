package com.transport.ReportsApis.Service;

import com.transport.ReportsApis.Entity.KpiTransportation;
import com.transport.ReportsApis.Entity.KpiTransportationDetail;
import com.transport.ReportsApis.Entity.TripHeader;
import com.transport.ReportsApis.Repo.KpiTransportationDetailRepository;
import com.transport.ReportsApis.Repo.KpiTransportationRepository;
import com.transport.ReportsApis.Repo.TripHeaderRepository;
import com.transport.ReportsApis.Response.KpiTransportationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportsService{

    private final TripHeaderRepository tripHeaderRepository;
    private final KpiTransportationRepository chartRepo;
    private final KpiTransportationDetailRepository detailRepo;

    public List<TripHeader> getAllTrips() {
        return tripHeaderRepository.findAll();
    }

    public TripHeader getTripById(String tripid) {
        return tripHeaderRepository.findById(tripid).orElseThrow(() -> new RuntimeException("Trip not found : " + tripid));
    }

    public List<TripHeader> getTripsBySiteAndDate(List<String> site, Date date) {
        if(site == null || site.isEmpty()){
            return List.of();
        }
        return tripHeaderRepository.findBySiteInAndDate(site, date);
    }

    public KpiTransportationResponse getKpiTransportation() {
        List<KpiTransportation> chart = chartRepo.findAll();
        List<KpiTransportationDetail> details = detailRepo.findAll();
        KpiTransportationResponse response = new KpiTransportationResponse();
        response.setChartData(chart.stream().map(c -> {
            KpiTransportationResponse.ChartData dto = new KpiTransportationResponse.ChartData();
            dto.setSite(c.getId().getSite());
            dto.setNbStops(c.getNbStops());
            dto.setNbRoutes(c.getNbRoutes());
            dto.setDistance(c.getDistance());
            dto.setTravelTime(c.getTravelTime());
            dto.setPeriod(c.getId().getPeriod());
            return dto;
        }).collect(Collectors.toList()));

        response.setDetailRecords(details.stream().map(d -> {
            KpiTransportationResponse.DetailRecords dto = new KpiTransportationResponse.DetailRecords();
            dto.setKpi("fleet");
            dto.setSite(d.getSite());
            dto.setVehicle(d.getVehicle());
            dto.setDriver(d.getDriver());
            dto.setRoutes(d.getRoutes());
            dto.setStops(d.getStops());
            dto.setDistance(d.getDistance());
            dto.setTravelTime(d.getTravelTime());
            dto.setPeriod(d.getPeriod());
            return dto;
        }).collect(Collectors.toList()));
        response.setPeriods(chart.stream().map(c -> c.getId().getPeriod()).distinct().collect(Collectors.toList())
        );
        response.setSites(
                chart.stream().map(c -> c.getId().getSite()).distinct().collect(Collectors.toList())
        );
        return response;
    }
}