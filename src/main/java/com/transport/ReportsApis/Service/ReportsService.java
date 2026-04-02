package com.transport.ReportsApis.Service;

import com.transport.ReportsApis.Entity.*;
import com.transport.ReportsApis.Repo.*;
import com.transport.ReportsApis.Response.KpiTransportationResponse;
import com.transport.ReportsApis.Response.RouteListResponse;
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
    private final RouteListHeaderRepo headerRepo;
    private final RouteListDocsRepo docsRepo;

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

    public RouteListResponse getRouteList(List<String> site, Date from, Date to) {
        List<RouteListHeader> headers = headerRepo.findBySiteInAndTripDateBetween(site, from, to);
        List<String> tripIds = headers.stream().map(RouteListHeader::getId).collect(Collectors.toList());
        List<RouteListDocs> docs = docsRepo.findByTripidIn(tripIds);
        Map<String, List<RouteListDocs>> docsMap = docs.stream().collect(Collectors.groupingBy(RouteListDocs::getTripid));
        RouteListResponse response = new RouteListResponse();
        response.setRecords(headers.stream().map(h -> {
            RouteListResponse.Record r = new RouteListResponse.Record();
            r.setId(h.getId());
            r.setRouteCode(h.getRouteCode());
            r.setVehicle(h.getVehicle());
            r.setTrip(h.getTrip());
            r.setDriverId(h.getDriverId());
            r.setCarrier("INTERNAL");
            r.setSite(h.getSite());
            r.setSchedDepDate(h.getSchedDepDate());
            r.setSchedDepTime(h.getSchedDepTime());
            r.setSchedRetDate(h.getSchedRetDate());
            r.setSchedRetTime(h.getSchedRetTime());
            r.setCorrDepDate(h.getCorrDepDate());
            r.setCorrDepTime(h.getCorrDepTime());
            r.setCorrRetDate(h.getCorrRetDate());
            r.setCorrRetTime(h.getCorrRetTime());
            r.setActDepDate(h.getActDepDate());
            r.setActDepTime(h.getActDepTime());
            r.setActRetDate(h.getActRetDate());
            r.setActRetTime(h.getActRetTime());
            r.setDistanceKm(h.getDistanceKm());
            r.setTimeH(h.getTimeH());

            List<RouteListResponse.Document> documents = docsMap.getOrDefault(h.getId(), Collections.emptyList()).stream().map(d -> {
            RouteListResponse.Document doc = new RouteListResponse.Document();
            doc.setSequence(d.getSequence());
            doc.setDocumentNo(d.getDocumentNo());
            doc.setDocType(d.getDocType());
            doc.setArvTime(d.getArvTime());
            doc.setDepTime(d.getDepTime());
            doc.setStatus(d.getStatus());
            return doc;
        }).collect(Collectors.toList());
            r.setDocuments(documents);
            return r;
        }).collect(Collectors.toList()));
        return response;
    }
}