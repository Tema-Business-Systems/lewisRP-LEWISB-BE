package com.transport.ReportsApis.Controller;
import com.transport.ReportsApis.Entity.DashboardReport;
import com.transport.ReportsApis.Entity.TripHeader;
import com.transport.ReportsApis.Response.*;
import com.transport.ReportsApis.Service.ReportsService;
import com.transport.tracking.response.AccessTokenVO;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportsController {

    private final ReportsService reportsService;

    @GetMapping("/allTrips")
    public List<TripHeader> getAllTrips() {
        return reportsService.getAllTrips();
    }

    @GetMapping("/{tripid}")
    public TripHeader getTrip(@PathVariable String tripid) {
        return reportsService.getTripById(tripid);
    }

    @GetMapping("/getBySiteandDate")
    public List<TripHeader> getBySiteAndDate(
            AccessTokenVO accessTokenVO,
            @RequestParam(name = "site", required = false) List<String> site,
            @RequestParam(name = "dateFrom", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(name = "dateTo", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {

        return reportsService.getTripsBySiteAndDate(site, startDate, endDate);
    }

    @GetMapping("/kpiReports")
    public KpiTransportationResponse getKpiReports() {
        return reportsService.getKpiTransportation();
    }

//    @GetMapping("/routeList")
//    public RouteListResponse getRouteList(@RequestParam(name = "site") List<String> site, @RequestParam(name = "dateFrom") @DateTimeFormat(pattern = "yyyy-MM-dd") Date from, @RequestParam(name = "dateTo") @DateTimeFormat(pattern = "yyyy-MM-dd") Date to) {
//        return reportsService.getRouteList(site, from, to);
//    }
    @GetMapping("/routeList")
    public RouteListResponse getRouteList() {
        return reportsService.getRouteList();
    }

//    @GetMapping("/podTracking")
//    public List<PodTrackingDTO> getPodTracking(AccessTokenVO accessTokenVO, @RequestParam(name = "site", required = false) List<String> site, @RequestParam(name = "dateFrom", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom, @RequestParam(name = "dateTo", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo) {
//        return reportsService.getPodTracking(site, dateFrom, dateTo);
//    }
    @GetMapping("/podTracking")
    public List<PodTrackingDTO> getPodTracking() {
        return reportsService.getPodTracking();
    }

    @GetMapping("/dashboard")
    public List<DashboardReportResponse> getDashboardReport() {
        return reportsService.getDashboardReport();
    }

//    @GetMapping("/orderDetail")
//    public List<OrderCalendarDTO> getAllOrders(
//            @RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
//            @RequestParam(name = "dateFrom", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom,
//            @RequestParam(name = "dateTo", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo) {
//        return reportsService.getAllOrders(date, dateFrom, dateTo);
//    }
    @GetMapping("/orderDetail")
    public List<OrderCalendarDTO> getAllOrders() {
        return reportsService.getAllOrders();
    }

    @GetMapping("/getDailyRouteBySiteandDate")
    public DailyRouteDashboardResponse getrouteBySiteAndDate(
            AccessTokenVO accessTokenVO,
            @RequestParam(name = "site", required = false) List<String> site,
            @RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            @RequestParam(name = "dateFrom", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom,
            @RequestParam(name = "dateTo", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo) {
        return reportsService.getDailyRouteDashboard(site, date, dateFrom, dateTo);
    }

    @GetMapping("/driverActivity")
    public DriverActivityResponseDTO getDriverActivity(
            AccessTokenVO accessTokenVO,
            @RequestParam(name = "site", required = false) List<String> site,
            @RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            @RequestParam(name = "dateFrom", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom,
            @RequestParam(name = "dateTo", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo) {
        return reportsService.getDriverActivity(site, date, dateFrom, dateTo);
    }

    @GetMapping("/customerService")
    public CustomerServiceResponseDTO getCustomerService() {
        return reportsService.getCustomerServiceData();
    }

    @GetMapping("/dailyReportService")
    public DailyServiceVisitResponseDTO getDailyServiceVisit(
            @RequestParam(name = "site", required = false) List<String> site,
            @RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            @RequestParam(name = "dateFrom", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom,
            @RequestParam(name = "dateTo", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo) {
        return reportsService.getDailyServiceVisit(site, date, dateFrom, dateTo);
    }
}
