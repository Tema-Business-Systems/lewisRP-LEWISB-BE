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
import java.time.LocalDate;
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
    private final CustomerServiceRepository custRepo;
    private final DailyServiceVisitRepository dailyServicerepo;

    public List<TripHeader> getAllTrips() {
        return tripHeaderRepository.findAll();
    }

    public TripHeader getTripById(String tripid) {
        return tripHeaderRepository.findById(tripid).orElseThrow(() -> new RuntimeException("Trip not found : " + tripid));
    }

    public List<TripHeader> getTripsBySiteAndDate(List<String> site,Date date, Date startDate, Date endDate) {
        if (site == null || site.isEmpty()) {
            return List.of();
        }
        if (date != null) {
            startDate = date;
            endDate = date;
        }
        if (startDate != null && endDate == null) {
            endDate = startDate;
        }

        if (startDate == null && endDate != null) {
            startDate = endDate;
        }
        if (endDate != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(endDate);
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 999);
            endDate = cal.getTime();
        }
        List<TripHeader> trips = tripHeaderRepository.findBySiteInAndDateBetween(site, startDate, endDate);
        return trips.stream().filter(trip -> trip.getStops() != null && !trip.getStops().isEmpty()).toList();
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

//    public List<PodTrackingDTO> getPodTracking(List<String> site,  Date dateFrom, Date dateTo) {
//        List<PodTracking> list = repository.findBySiteInAndDateBetween(site, dateFrom, dateTo);
//        return list.stream().map(p -> {
//            PodTrackingDTO dto = new PodTrackingDTO();
//            BeanUtils.copyProperties(p, dto);
//            dto.setLineItems(lineRepo.findByDocument(p.getDocument()));
//            dto.setPodProducts(productRepo.findByDeliveryNum(p.getDocument()));
//            dto.setPodImages(imageRepo.findByDocument(p.getDocument())
//                .stream()
//                .filter(Objects::nonNull)
//                .toList()
//            );
//            return dto;
//        }).toList();
//    }

    public List<PodTrackingDTO> getPodTracking() {
        List<PodTracking> list = repository.findAll();
        return list.stream().map(p -> {
            PodTrackingDTO dto = new PodTrackingDTO();
            BeanUtils.copyProperties(p, dto);
            dto.setLineItems(lineRepo.findByDocument(p.getDocument()));
            dto.setPodProducts(productRepo.findByDeliveryNum(p.getDocument()));
            dto.setPodImages(
                    imageRepo.findByDocument(p.getDocument())
                            .stream()
                            .filter(Objects::nonNull)
                            .toList()
            );
            return dto;
        }).toList();
    }

    public RouteListResponse getRouteList() {
        List<RouteListHeader> headers = headerRepo.findAll();
        List<String> tripIds = headers.stream()
                .map(RouteListHeader::getId)
                .collect(Collectors.toList());
        List<RouteListDocs> docs = docsRepo.findByTripidIn(tripIds);
        Map<String, List<RouteListDocs>> docsMap =
                docs.stream().collect(Collectors.groupingBy(RouteListDocs::getTripid));
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

            List<RouteListResponse.Document> documents =
                    docsMap.getOrDefault(h.getId(), Collections.emptyList())
                            .stream()
                            .map(d -> {
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

//    public List<OrderCalendarDTO> getAllOrders(Date date, Date dateFrom, Date dateTo) {
//        List<OrderCalendar> orders;
//        if (date == null && dateFrom == null && dateTo == null) {
//            orders = orderRepo.findAll();
//        } else {
//            Date[] range = resolveDateRange(date, dateFrom, dateTo);
//            LocalDate from = range[0].toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//            LocalDate to = range[1].toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//            orders = orderRepo.findByOrderDateBetween(from, to);
//        }
//        return orders
//                .stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }

    public List<OrderCalendarDTO> getAllOrders() {
        List<OrderCalendar> orders = orderRepo.findAll();
        return orders.stream()
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

    public DailyRouteDashboardResponse getDailyRouteDashboard(List<String> sites, Date date, Date dateFrom, Date dateTo) {
        if (sites != null && sites.isEmpty()) {
            sites = null;
        }
        if (date != null) {
            dateFrom = date;
            dateTo = date;
        }
        if (dateFrom != null && dateTo == null) {
            dateTo = dateFrom;
        }
        if (dateFrom == null && dateTo != null) {
            dateFrom = dateTo;
        }
        List<DailyRouteSummary> data = dailyRouteRepo.findBySiteAndDateRange(sites, dateFrom, dateTo);
        DailyRouteDashboardResponse response = new DailyRouteDashboardResponse();
        response.setDate(dateFrom == null ? null :
                dateFrom.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
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


    public DriverActivityResponseDTO getDriverActivity(List<String> site, Date date, Date dateFrom, Date dateTo) {
        Date[] range = resolveDateRange(date, dateFrom, dateTo);
        List<String> normalizedSite = (site == null || site.isEmpty()) ? null : site;
        List<DriverActivitySummary> summaryList = summaryRepo.getByDateRange(range[0], range[1]);
        List<DriverActivityTimeline> timelineList = timelineRepo.getBySiteAndDateRange(normalizedSite, range[0], range[1]);
        DriverActivityResponseDTO response = new DriverActivityResponseDTO();
        response.setDate(range[0] != null ? new SimpleDateFormat("yyyy-MM-dd").format(range[0]) : null);
        response.setSite(normalizedSite != null ? normalizedSite.get(0) : null);
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

    public CustomerServiceResponseDTO getCustomerServiceData() {
        List<CustomerServiceLog> data = custRepo.findAll().stream().filter(Objects::nonNull).toList();
        CustomerServiceResponseDTO response = new CustomerServiceResponseDTO();
        SummaryDataDTO summary = new SummaryDataDTO();
        long customersServiced = data.size();
        long cashAccounts = data.stream().filter(d -> d.getAccountType() != null).filter(d -> "Cash".equalsIgnoreCase(d.getAccountType())).count();
        long chargeAccounts = data.stream().filter(d -> d.getAccountType() != null).filter(d -> "Charge".equalsIgnoreCase(d.getAccountType())).count();
        long skipped = data.stream().filter(d -> d.getStatus() != null).filter(d -> "Missed".equalsIgnoreCase(d.getStatus())).count();
        summary.setCustomersServiced(new SummaryItemDTO((int) customersServiced, "active delivery day"));
        summary.setCashAccounts(new SummaryItemDTO((int) cashAccounts, "cash collection enabled"));
        summary.setChargeAccounts(new SummaryItemDTO((int) chargeAccounts, "majority of route"));
        summary.setSkippedMissed(new SummaryItemDTO((int) skipped, "needs follow-up"));
        response.setSummary(summary);

        FiltersDTO filters = new FiltersDTO();
        filters.setDrivers(data.stream().map(CustomerServiceLog::getDriver).filter(Objects::nonNull).distinct().collect(Collectors.toList()));
        filters.setAccountTypes(data.stream().map(CustomerServiceLog::getAccountType).filter(Objects::nonNull).distinct().collect(Collectors.toList()));
        response.setFilters(filters);

        List<CustomerServiceDetailsDTO> details = data.stream().map(d -> {
                    CustomerServiceDetailsDTO dto = new CustomerServiceDetailsDTO();
                    dto.setAccountNo(d.getAcctNo());
                    dto.setCustomer(d.getCustomer());
                    dto.setAccountType(d.getAccountType());
                    dto.setDriver(d.getDriver());
                    dto.setDelivery(d.getDeliveryStatus());
                    dto.setOrderChange(d.getOrderChange());
                    dto.setAdvanceOrder(d.getAdvOrder());
                    dto.setPayment(d.getPayment());
                    dto.setStatus(d.getStatus());
                    return dto;
                }).collect(Collectors.toList());
        response.setCustomerServiceDetails(details);
        return response;
    }

    public DailyServiceVisitResponseDTO getDailyServiceVisit(List<String> site, Date date, Date dateFrom, Date dateTo) {
        Date[] range = resolveDateRange(date, dateFrom, dateTo);
        List<String> normalizedSite = (site == null || site.isEmpty()) ? null : site;
        List<DailyServiceVisit> data = dailyServicerepo.findBySiteAndVisitDateRange(normalizedSite, range[0], range[1]).stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        DailyServiceVisitResponseDTO response = new DailyServiceVisitResponseDTO();

        // Filters
        VisitFiltersDTO filters = new VisitFiltersDTO();
        filters.setDate(range[0] != null
                ? range[0].toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString()
                : null);

        filters.setSite(normalizedSite != null ? String.join(",", normalizedSite) : "ALL");

        response.setFilters(filters);

        // Summary
        int totalVisits = data.size();
        String fieldStatus = totalVisits > 0 ? "Field Active" : "No Activity";
        response.setSummary(new VisitSummaryDTO(totalVisits, fieldStatus));

        // Details
        List<ServiceVisitLogDTO> details = data.stream().map(d -> {

            ServiceVisitLogDTO dto = new ServiceVisitLogDTO();
            dto.setStore(d.getStore());
            dto.setVisitType(d.getVisitType());
            dto.setStart(d.getStartTime());
            dto.setEnd(d.getEndTime());
            dto.setActivity(d.getActivity());
            dto.setStatus(d.getStatus());
            dto.setDate(d.getVisitDate() != null
                    ? d.getVisitDate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
                    .toString()
                    : null);
            dto.setSite(d.getSite());

            return dto;

        }).collect(Collectors.toList());
        response.setServiceVisitLog(details);
        return response;
    }

    private Date[] resolveDateRange(Date date, Date dateFrom, Date dateTo) {
        Date effectiveFrom = dateFrom;
        Date effectiveTo = dateTo;
        if (effectiveFrom == null && effectiveTo == null && date != null) {
            effectiveFrom = date;
            effectiveTo = date;
        }
        if (effectiveFrom == null) {
            effectiveFrom = effectiveTo;
        }
        if (effectiveTo == null) {
            effectiveTo = effectiveFrom;
        }
        return new Date[]{effectiveFrom, effectiveTo};
    }
}
