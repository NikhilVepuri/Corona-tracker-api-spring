package io.springapp.coronatracker.controllers;

import io.springapp.coronatracker.models.LocationData;
import io.springapp.coronatracker.services.CoronaDataTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class MainController {
    @Autowired
    CoronaDataTrackerService coronaDataTrackerService;
@GetMapping("/")
    public String home(Model model)
    {
       List<LocationData> allData=  coronaDataTrackerService.getCasesData();
 int totalCases=allData.stream().mapToInt(stat-> stat.getTotalActiveCases()).sum();
 int newCases=allData.stream().mapToInt(stat-> stat.getDiffBwLastDay()).sum();
       model.addAttribute("locationDa",allData);
       model.addAttribute("totalActiveCases",totalCases);
        model.addAttribute("newCasesSincePrevDay",newCases);
        return "Home";
    }
}
