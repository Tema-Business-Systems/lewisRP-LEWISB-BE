package com.transport.ReportsApis.Service;

import com.transport.ReportsApis.Entity.TripHeader;
import com.transport.ReportsApis.Repo.TripHeaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TripService{

    private final TripHeaderRepository tripHeaderRepository;

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
}