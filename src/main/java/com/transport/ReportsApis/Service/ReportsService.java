package com.transport.ReportsApis.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.transport.ReportsApis.Entity.*;
import com.transport.ReportsApis.Repo.*;
import com.transport.ReportsApis.Response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
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
    private final PodTrackingRepository repository;
    private final PodLineItemsRepository lineRepo;
    private final PodProductsRepository productRepo;
    private final PodImagesRepository imageRepo;
    private final DashboardReportRepository dashRepository;
    private final ObjectMapper objectMapper;
    private final OrderCalendarRepository orderRepo;
    private final DailyRouteSummaryRepository dailyRouteRepo;
    private final DriverActivitySummaryRepository summaryRepo;
    private final DriverActivityTimelineRepository timelineRepo;

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

    public List<PodTrackingDTO> getPodTracking(List<String> site,  Date dateFrom, Date dateTo) {
        List<PodTracking> list = repository.findBySiteInAndDateBetween(site, dateFrom, dateTo);
        return list.stream().map(p -> {
            PodTrackingDTO dto = new PodTrackingDTO();
            BeanUtils.copyProperties(p, dto);
            dto.setLineItems(lineRepo.findByDocument(p.getDocument()));
            dto.setPodProducts(productRepo.findByDeliveryNum(p.getDocument()));
            dto.setPodImages(imageRepo.findByDocument(p.getDocument())
                .stream()
                .filter(Objects::nonNull)
                .toList()
            );
            return dto;
        }).toList();
    }

    public List<DashboardReportResponse> getDashboardReport() {
        List<DashboardReport> reports = dashRepository.findAll();
        return reports.stream().map(report -> {
            DashboardReportResponse response = new DashboardReportResponse();
            try {
                response.setMetrics(
                        objectMapper.readValue(report.getMetrics(), new TypeReference<List<Map<String, Object>>>() {}
                        )
                );
                response.setActiveRoutes(
                        objectMapper.readValue(report.getActiveRoutes(), new TypeReference<List<Map<String, Object>>>() {}
                        )
                );

                response.setVehicleLocations(
                        objectMapper.readValue(report.getVehicleLocations(), new TypeReference<List<Map<String, Object>>>() {}
                        )
                );
            } catch (Exception e) {
                throw new RuntimeException("Error parsing dashboard JSON", e);
            }
            return response;

        }).toList();
    }

    public List<OrderCalendarDTO> getAllOrders() {
        return orderRepo.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private OrderCalendarDTO convertToDTO(OrderCalendar entity) {
        OrderCalendarDTO dto = new OrderCalendarDTO();
        dto.setId(entity.getId());
        dto.setOrderNumber(entity.getOrderNumber());
        dto.setSite(entity.getSite());
        dto.setCustomer(entity.getCustomer());
        dto.setCustomerName(entity.getCustomerName());
        dto.setOrderType(entity.getOrderType());
        dto.setStatus(entity.getStatus());
        dto.setOrderDate(entity.getOrderDate());
        dto.setOrderTime(entity.getOrderTime());
        dto.setRouteNumber(entity.getRouteNumber());
        dto.setVehicle(entity.getVehicle());
        dto.setDriver(entity.getDriver());
        dto.setOrigin(entity.getOrigin());
        dto.setDestination(entity.getDestination());
        dto.setTotalPacks(entity.getTotalPacks());
        dto.setTotalWeight(entity.getTotalWeight());
        dto.setProducts(entity.getProducts());
        return dto;
    }

    public DailyRouteDashboardResponse getDailyRouteDashboard(List<String> sites, Date date) {
        if (sites != null && sites.isEmpty()) {
            sites = null;
        }
        List<DailyRouteSummary> data = dailyRouteRepo.findBySiteAndDate(sites, date);
        DailyRouteDashboardResponse response = new DailyRouteDashboardResponse();
        response.setDate(date == null ? null : date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        response.setSite(sites == null ? "ALL" : String.join(",", sites));
        response.setSummary(buildSummary(data));
        response.setDriverPerformance(buildDriverPerformance(data));
        response.setDayActivity(buildDayActivity(data));
        response.setRouteDetails(buildRouteDetails(data));
        return response;
    }

    private Summary buildSummary(List<DailyRouteSummary> data) {
        Summary summary = new Summary();
        CustomersServiced cs = new CustomersServiced();
        cs.setValue(data.stream().map(DailyRouteSummary::getCustomerCode).filter(Objects::nonNull).distinct().count());
        summary.setCustomersServiced(cs);
        BigDecimal total = data.stream().map(d -> d.getAmount() == null ? BigDecimal.ZERO : d.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
        TotalSales sales = new TotalSales();
        sales.setValue(total);
        sales.setCurrency(data.stream().map(DailyRouteSummary::getCurrencyUnit).filter(Objects::nonNull).findFirst().orElse("USD"));
        summary.setTotalSales(sales);
        summary.setCollections(sales);
        return summary;
    }

    private List<DriverPerformance> buildDriverPerformance(List<DailyRouteSummary> data) {
        return data.stream().filter(d -> d.getDriver() != null).collect(Collectors.groupingBy(DailyRouteSummary::getDriver))
                .entrySet().stream().map(entry -> {
                    DriverPerformance dp = new DriverPerformance();
                    dp.setDriverId(entry.getKey());
                    dp.setVehicle(entry.getValue().get(0).getVehicle());
                    dp.setDeliveries((long) entry.getValue().size());
                    BigDecimal total = entry.getValue().stream().map(d -> d.getAmount() == null ? BigDecimal.ZERO : d.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
                    dp.setTotalSales(total);
                    return dp;
                }).toList();
    }

    private List<DayActivity> buildDayActivity(List<DailyRouteSummary> data) {
        return data.stream()
                .filter(d -> d.getDriver() != null && d.getRouteNo() != null)
                .collect(Collectors.groupingBy(d -> d.getDriver() + "_" + d.getRouteNo()))
                .entrySet().stream().map(entry -> {
                    DailyRouteSummary d = entry.getValue().get(0);
                    DayActivity da = new DayActivity();
                    da.setDriverId(d.getDriver());
                    da.setRouteNo(d.getRouteNo());
                    da.setDeliveries((long) entry.getValue().size());
                    return da;
                }).toList();
    }

    private List<RouteDetails> buildRouteDetails(List<DailyRouteSummary> data) {
        return data.stream().map(d -> {
            RouteDetails rd = new RouteDetails();
            rd.setRouteNo(d.getRouteNo());
            rd.setRouteDate(d.getRouteDate());
            rd.setDriver(d.getDriver());
            rd.setVehicle(d.getVehicle());
            rd.setSite(d.getSite());
            rd.setOrderNo(d.getOrderNo());
            rd.setDeliveryNo(d.getDeliveryNo());
            rd.setSequence(d.getSequence());
            rd.setStatus(d.getStatus());
            rd.setCustomerCode(d.getCustomerCode());
            rd.setCustomerName(d.getCustomer());
            rd.setCity(d.getCity());
            rd.setPostalCode(d.getPostal());
            rd.setTotalQty(d.getTotalQty());
            rd.setTotalVolume(d.getTotalVol());
            rd.setVolumeUnit(d.getVolUnit());
            rd.setTotalWeight(d.getTotalWgt());
            rd.setWeightUnit(d.getWgtUnit());
            rd.setAmount(d.getAmount());
            rd.setCurrency(d.getCurrencyUnit());
            rd.setLatitude(d.getLat());
            rd.setLongitude(d.getLng());
            return rd;
        }).toList();
    }


    public DriverActivityResponseDTO getDriverActivity(List<String> site, Date date) {
        List<DriverActivitySummary> summaryList = summaryRepo.getByDate(date);
        List<DriverActivityTimeline> timelineList = timelineRepo.getBySiteAndDate(site, date);
        DriverActivityResponseDTO response = new DriverActivityResponseDTO();
        response.setDate(date != null ? new SimpleDateFormat("yyyy-MM-dd").format(date) : null);
        response.setSite(site != null ? site.get(0) : null);
        // SUMMARY
        if (summaryList != null && !summaryList.isEmpty() && summaryList.get(0) != null) {
            DriverActivitySummary s = summaryList.get(0);
            SummaryDTO summaryDTO = new SummaryDTO();
            summaryDTO.setAvgHoursDriven(new MetricDTO(s.getAvgHoursDriven(), "hours", "steady this week", null));
            summaryDTO.setStopsPerDay(new MetricDTO(s.getStopsPerDay(), null, "+2 vs average", null));
            summaryDTO.setAvgVisitDuration(new MetricDTO(s.getAvgVisitDuration(), "min", null, "within SLA"));
            summaryDTO.setIdleRatio(new MetricDTO(s.getIdleRatio(), "%", "-1.5% improvement", null));
            response.setSummary(summaryDTO);
        }

        // TIMELINE GROUPING
        Map<String, TimelineDTO> map = new LinkedHashMap<>();
        for (DriverActivityTimeline t : timelineList) {
            String key = t.getDriver() + "_" + t.getVehicle();
            TimelineDTO dto = map.computeIfAbsent(key, k -> {
                TimelineDTO d = new TimelineDTO();
                d.setDriver(t.getDriver());
                d.setVehicle(t.getVehicle());
                d.setActivities(new ArrayList<>());
                return d;
            });
            dto.getActivities().add(new ActivityDTO(
                            t.getActivityType(),
                            t.getStartTime() != null ? t.getStartTime().toString() : null,
                            t.getEndTime() != null ? t.getEndTime().toString() : null, t.getDurationMin()));
        }
        response.setTimeline(new ArrayList<>(map.values()));
        return response;
    }
}