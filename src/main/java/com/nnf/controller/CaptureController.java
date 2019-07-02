package com.nnf.controller;

import com.google.common.collect.Lists;
import com.nnf.domain.*;
import com.nnf.dto.chronic.PainCaptureForm;
import com.nnf.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@ControllerAdvice
public class CaptureController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CauseRepository causeRepository;

    @Autowired
    OccurrenceRepository occurrenceRepository;

    @Autowired
    PainTypeRepository painTypeRepository;

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    RemedyRepository remedyRepository;

    @Autowired
    TemperatureRepository temperatureRepository;

    @Autowired
    PainFormRepository painFormRepository;

    private static Logger logger = LoggerFactory.getLogger(CaptureController.class);

    @GetMapping("/ouch")
    public String serve(Model model) {

        Date date = Calendar.getInstance().getTime();
        String timestamp = new SimpleDateFormat("yyyy-MM-dd").format(date);

        String[] dateTime = timestamp.split("-");
        PainCaptureForm painCaptureForm = new PainCaptureForm();
        painCaptureForm.setYear(dateTime[0]);
        painCaptureForm.setMonth(dateTime[1]);
        painCaptureForm.setDay(dateTime[2]);

        model.addAttribute("captureForm", painCaptureForm);

        List<CauseCategory> causeCategories = populateCauses();
        causeCategories.add(0, new CauseCategory(""));
        model.addAttribute("allCauses", causeCategories);

        List<OccurrenceTime> occurrenceTimes = populateOccurrences();
        occurrenceTimes.add(0, new OccurrenceTime(""));
        model.addAttribute("allOccurrences", occurrenceTimes);

        List<PainPosition> painPositions = populatePositions();
        painPositions.add(0, new PainPosition(""));
        model.addAttribute("allPositions", painPositions);

        List<PainType> painTypes = populatePainTypes();
        painTypes.add(0, new PainType(""));
        model.addAttribute("allPainTypes", painTypes);

        List<RemedyCategory> remedyCategories = populateRemedies();
        remedyCategories.add(0, new RemedyCategory(""));
        model.addAttribute("allRemedies", remedyCategories);

        List<Temperature> temperatures = populateTemperatures();
        temperatures.add(0, new Temperature(""));
        model.addAttribute("allTemperatures", temperatures );

        return "pain-capture-form";
    }

    @PostMapping("/ouch")
    public String capture(@ModelAttribute PainCaptureForm painCaptureForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "pain-capture-form";
        }

        logger.info(painCaptureForm.toString());
        painFormRepository.save(painCaptureForm);
        logger.debug(painFormRepository.findAll().toString());

        return "pain-capture-success";
    }

    @GetMapping("/pain-data")
    public String servePainData(Model model){
        model.addAttribute("allPainEntries", populateAllPain());
        return "pain-summary";
    }

    @GetMapping("/pain-ref-data")
    public String servePainRefData(Model model) {

        Date date = Calendar.getInstance().getTime();
        String timestamp = new SimpleDateFormat("yyyy-MM-dd").format(date);

        String[] dateTime = timestamp.split("-");

        model.addAttribute("causeCategory", new CauseCategory());
        model.addAttribute("occurrenceTime", new OccurrenceTime());
        model.addAttribute("painPosition", new PainPosition());
        model.addAttribute("painType", new PainType());
        model.addAttribute("remedyCategory", new RemedyCategory());
        model.addAttribute("temperature", new Temperature());

        model.addAttribute("allCauses", populateCauses());
        model.addAttribute("allOccurrences", populateOccurrences());
        model.addAttribute("allPositions", populatePositions());
        model.addAttribute("allPainTypes", populatePainTypes());
        model.addAttribute("allRemedies", populateRemedies());
        model.addAttribute("allTemperatures", populateTemperatures());

        return "pain-ref-data-form";
    }

    @PostMapping("/causes")
    public String causes(@ModelAttribute CauseCategory causeCategory, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "pain-ref-data-form";
        }
        logger.info(causeCategory.toString());
        causeRepository.save(causeCategory);
        return "redirect:/pain-ref-data";
    }

    @PostMapping("/occurrences")
    public String occurrences(@ModelAttribute OccurrenceTime occurrenceTime, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "pain-ref-data-form";
        }
        logger.info(occurrenceTime.toString());
        occurrenceRepository.save(occurrenceTime);
        return "redirect:/pain-ref-data";
    }

    @PostMapping("/types")
    public String types(@ModelAttribute PainType painType, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "pain-ref-data-form";
        }
        logger.info(painType.toString());
        painTypeRepository.save(painType);
        return "redirect:/pain-ref-data";
    }

    @PostMapping("/positions")
    public String positions(@ModelAttribute PainPosition painPosition, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "pain-ref-data-form";
        }
        logger.info(painPosition.toString());
        positionRepository.save(painPosition);
        return "redirect:/pain-ref-data";
    }

    @PostMapping("/remedies")
    public String remedies(@ModelAttribute RemedyCategory remedyCategory, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "pain-ref-data-form";
        }
        logger.info(remedyCategory.toString());
        remedyRepository.save(remedyCategory);
        return "redirect:/pain-ref-data";
    }

    @PostMapping("/temperatures")
    public String temperatures(@ModelAttribute Temperature temperature, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "pain-ref-data-form";
        }
        logger.info(temperature.toString());
        temperatureRepository.save(temperature);
        return "redirect:/pain-ref-data";
    }


    @GetMapping("/add-all-ref-data")
    public String addAllRefData() {

        addCauses();
        addOccurrences();
        addPainTypes();
        addPositions();
        addRemedies();
        addTemperatures();

        return "redirect:/pain-ref-data";
    }

    @GetMapping("/remove-all-ref-data")
    public String deleteAllRefData() {

        causeRepository.deleteAll();
        occurrenceRepository.deleteAll();
        painTypeRepository.deleteAll();
        positionRepository.deleteAll();
        temperatureRepository.deleteAll();
        remedyRepository.deleteAll();

        return "redirect:/pain-ref-data";
    }

    public void addCauses() {
        logger.info("Adding CauseCategory:");
        causeRepository.save(new CauseCategory("Shoes"));
        causeRepository.save(new CauseCategory("Standing"));
        causeRepository.save(new CauseCategory("Sitting"));
        causeRepository.save(new CauseCategory("Heavy objects"));
        causeRepository.save(new CauseCategory("Bed"));
        causeRepository.save(new CauseCategory("Diet"));
        causeRepository.save(new CauseCategory("Exercise"));
        causeRepository.save(new CauseCategory("Typing"));
        causeRepository.save(new CauseCategory("Driving"));
        causeRepository.save(new CauseCategory("Stress"));
    }

    public void addOccurrences() {
        logger.info("Adding OccurrenceTime:");
        occurrenceRepository.save(new OccurrenceTime("Morning"));
        occurrenceRepository.save(new OccurrenceTime("Mid day"));
        occurrenceRepository.save(new OccurrenceTime("Afternoon"));
        occurrenceRepository.save(new OccurrenceTime("Evening"));
    }

    public void addPainTypes() {
        logger.info("Adding PainType:");
        painTypeRepository.save(new PainType("Muscle"));
        painTypeRepository.save(new PainType("Spasm"));
        painTypeRepository.save(new PainType("Vertebrae"));
    }

    public void addPositions() {
        logger.info("Adding PainPosition:");
        positionRepository.save(new PainPosition("Low back"));
        positionRepository.save(new PainPosition("Bra area"));
        positionRepository.save(new PainPosition("Neck"));
        positionRepository.save(new PainPosition("Shoulders"));
        positionRepository.save(new PainPosition("Mid back"));
    }

    public void addTemperatures() {
        logger.info("Adding Temperature:");
        temperatureRepository.save(new Temperature("Cold"));
        temperatureRepository.save(new Temperature("Warm"));
        temperatureRepository.save(new Temperature("Normal"));
    }

    public void addRemedies() {
        logger.info("Adding RemedyCategory:");
        remedyRepository.save(new RemedyCategory("Bed rest"));
        remedyRepository.save(new RemedyCategory("Painkillers"));
        remedyRepository.save(new RemedyCategory("Anti-inflammatory"));
        remedyRepository.save(new RemedyCategory("Injections"));
        remedyRepository.save(new RemedyCategory("Ointments"));
        remedyRepository.save(new RemedyCategory("Massage"));
        remedyRepository.save(new RemedyCategory("Tape"));
        remedyRepository.save(new RemedyCategory("Plasters"));
        remedyRepository.save(new RemedyCategory("Exercise"));
        remedyRepository.save(new RemedyCategory("Heat"));
        remedyRepository.save(new RemedyCategory("Physiotherapy"));
        remedyRepository.save(new RemedyCategory("Natural Medicine"));
    }

    @ModelAttribute("allCauses")
    public List<CauseCategory> populateCauses() {
        List<CauseCategory> causeCategories = Lists.newArrayList(causeRepository.findAll());
        Collections.sort(causeCategories);
        return causeCategories;
    }

    @ModelAttribute("allOccurrences")
    public List<OccurrenceTime> populateOccurrences() {
        ArrayList<OccurrenceTime> occurrenceTimes = Lists.newArrayList(occurrenceRepository.findAll());
        Collections.sort(occurrenceTimes);
        return occurrenceTimes;
    }

    @ModelAttribute("allPositions")
    public List<PainPosition> populatePositions() {
        ArrayList<PainPosition> painPositions = Lists.newArrayList(positionRepository.findAll());
        Collections.sort(painPositions);
        return painPositions;
    }

    @ModelAttribute("allPainTypes")
    public List<PainType> populatePainTypes() {
        ArrayList<PainType> painTypes = Lists.newArrayList(painTypeRepository.findAll());
        Collections.sort(painTypes);
        return painTypes;
    }

    @ModelAttribute("allRemedies")
    public List<RemedyCategory> populateRemedies() {
        ArrayList<RemedyCategory> remedyCategories = Lists.newArrayList(remedyRepository.findAll());
        Collections.sort(remedyCategories);
        return remedyCategories;
    }

    @ModelAttribute("allTemperatures")
    public List<Temperature> populateTemperatures() {
        ArrayList<Temperature> temperatures = Lists.newArrayList(temperatureRepository.findAll());
        Collections.sort(temperatures);
        return temperatures;
    }

    @ModelAttribute("allPainEntries")
    public List<PainCaptureForm> populateAllPain() {
        ArrayList<PainCaptureForm> painCaptureForms = Lists.newArrayList(painFormRepository.findAll());
        Collections.sort(painCaptureForms);
        Collections.reverse(painCaptureForms);
        return painCaptureForms;
    }

}
