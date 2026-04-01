package com.transport.ReportsApis.Controller;

import com.transport.ReportsApis.Entity.TripHeader;
import com.transport.ReportsApis.Service.TripService;
import com.transport.tracking.response.AccessTokenVO;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @GetMapping("/allTrips")
    public List<TripHeader> getAllTrips() {
        return tripService.getAllTrips();
    }

    @GetMapping("/{tripid}")
    public TripHeader getTrip(@PathVariable String tripid) {
        return tripService.getTripById(tripid);
    }

    @GetMapping("/getBySiteandDate")
    public List<TripHeader> getBySiteAndDate(AccessTokenVO accessTokenVO, @RequestParam(name = "site", required = false) List<String> site, @RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return tripService.getTripsBySiteAndDate(site, date);
    }
}
