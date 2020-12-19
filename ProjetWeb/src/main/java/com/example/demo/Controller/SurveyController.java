package com.example.demo.Controller;

import com.example.demo.Model.Survey;
import com.example.demo.Model.User;
import com.example.demo.Model.Vote;
import com.example.demo.Service.CityService;
import com.example.demo.Service.SurveyService;
import com.example.demo.Service.UserService;
import com.example.demo.Service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@org.springframework.stereotype.Controller
public class SurveyController {

    @Autowired
    SurveyService surveyService;

    @Autowired
    CityService cityService;

    @Autowired
    UserService userService;

    @Autowired
    VoteService voteService;


    User activeUser;
    int user_id = 1;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        activeUser = userService.getUserByEmail(currentPrincipalName);

        List<Survey> survey = surveyService.getSurveyFromUserId(activeUser.getAuth_user_id());
        List<Survey> allSurvey = surveyService.getAllSurvey();
        List<String> creators = userService.getAllCreators();

        List<String> citiesOne = cityService.getAllCitiesOne();
        List<String> citiesTwo = cityService.getAllCitiesTwo();
        List<String> citiesThree = cityService.getAllCitiesThree();



        List<Boolean> checkboxDates = new ArrayList<>();
        List<Boolean> checkboxCities = new ArrayList<>();

        Boolean date1 = false;
        Boolean date2 = false;
        Boolean date3 = false;
        Boolean city1 = false;
        Boolean city2 = false;
        Boolean city3 = false;

        checkboxDates.add(date1);
        checkboxDates.add(date2);
        checkboxDates.add(date3);
        checkboxCities.add(city1);
        checkboxCities.add(city2);
        checkboxCities.add(city3);

        modelAndView.addObject("user_id", activeUser.getAuth_user_id());
        modelAndView.addObject("all_sondages", allSurvey);
        modelAndView.addObject("sondages", survey);
        modelAndView.addObject("creators", creators);

        modelAndView.addObject("citiesOne", citiesOne);
        modelAndView.addObject("citiesTwo", citiesTwo);
        modelAndView.addObject("citiesThree", citiesThree);

        modelAndView.addObject("checkboxesDates", checkboxDates);
        modelAndView.addObject("checkboxCities", checkboxCities);

        modelAndView.setViewName("home"); // resources/template/home.html
        return modelAndView;
    }

    @RequestMapping(value = "/create-survey", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        Survey survey = new Survey();

        List<String> cities = cityService.getAllCities();

        modelAndView.addObject("cities", cities);
        modelAndView.addObject("user_id", user_id);

        modelAndView.addObject("survey", survey);
        modelAndView.setViewName("create-survey"); // resources/template/create-survey.html
        return modelAndView;
    }

    @RequestMapping(value = "/home/{survey_id}", method = RequestMethod.DELETE)
    public String deleteSurvey(@PathVariable("survey_id") int survey_id) {
        ModelAndView modelAndView = new ModelAndView();

        surveyService.deleteSurvey(survey_id);


        List<Survey> listSurvey = surveyService.getSurveyFromUserId(activeUser.getAuth_user_id());
        List<Survey> allSurvey = surveyService.getAllSurvey();

        List<String> cities = cityService.getAllCities();

        modelAndView.addObject("sondages", listSurvey);
        modelAndView.addObject("all_sondages", allSurvey);
        modelAndView.addObject("cities", cities);
        //modelAndView.addObject("creators", creators);
//        modelAndView.addObject("user_id", user_id);

        modelAndView.setViewName("home"); // resources/template/home.html
        return "home";
    }

    @RequestMapping(value = "/vote/{array}/survey_id/{survey_id}", method = RequestMethod.POST)
    public String getVote(@PathVariable("array") List<Boolean> array, @PathVariable("survey_id") String survey_id) {
        ModelAndView modelAndView = new ModelAndView();
        voteService.scoreVote(array, Integer.parseInt(survey_id));
        modelAndView.setViewName("home"); // resources/template/home.html
        return "home";
    }

    @RequestMapping(value="/create-survey", method=RequestMethod.POST)
    public ModelAndView createSurvey(Survey survey, BindingResult bindingResult, ModelMap modelMap,
                                     @RequestParam("date_one") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOne,
                                     @RequestParam("date_two") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTwo,
                                     @RequestParam("date_three") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateThree ) {
        ModelAndView modelAndView = new ModelAndView();

        survey.setUser_id(activeUser.getAuth_user_id());
        survey.setDate_one(dateOne);
        survey.setDate_two(dateTwo);
        survey.setDate_three(dateThree);

        if(bindingResult.hasErrors() && survey.getDate_one() == null && survey.getDate_two() == null && survey.getDate_three() == null) {
            List<String> cities = cityService.getAllCities();
            System.out.println("***** ERROR *****");
            System.out.println("***********\n");

            modelMap.addAttribute("cities", cities);
            modelAndView.addObject("message", "Veuillez rectifier les erreurs du formulaire !");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
        else {
            List<String> cities = cityService.getAllCities();
            modelMap.addAttribute("cities", cities);

            System.out.println("\n***** SUCCESS : GO CHECK DATABASE *****");
            Vote vote = new Vote();
            surveyService.saveSurvey(survey, vote);
            modelAndView.addObject("message", "Le sondage a été crée avec succès !");
        }
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("create-survey");
        return modelAndView;
    }
}
