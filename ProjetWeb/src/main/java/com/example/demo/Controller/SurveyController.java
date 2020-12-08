package com.example.demo.Controller;

import com.example.demo.Model.Survey;
import com.example.demo.Model.User;
import com.example.demo.Service.CityService;
import com.example.demo.Service.SurveyService;
import com.example.demo.Service.UserService;
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


    User activeUser;
    int user_id = 1;

    public List<String> returnCities(int user_id) {

        List<Survey> surveys = surveyService.getSurveyFromUserId(user_id);
        List<String> cities = new ArrayList<>();

        String testCity = "";

        for (int i = 0; i < surveys.size(); i++) {
            testCity = cityService.getCityById(surveys.get(i).getCity_id());
            cities.add(testCity);
        }

        return cities;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        activeUser = userService.getUserByEmail(currentPrincipalName);

        System.out.println("active user details are : \n" +
                "\nId = " + activeUser.getAuth_user_id() +
                "\nFirst Name = " + activeUser.getFirst_name() +
                "\nLast Name = " + activeUser.getLastName() +
                "\nEmail = " + activeUser.getEmail()
        );

        List<Survey> survey = surveyService.getSurveyFromUserId(activeUser.getAuth_user_id());
        List<String> cities = new ArrayList<>();
        List<String> creators = new ArrayList<>();

        String testCity = "";
        String testUsername = "";

        for (int i = 0; i < survey.size(); i++) {
            testCity = cityService.getCityById(survey.get(i).getCity_id());
            cities.add(testCity);

            testUsername = userService.getUsernamebyId(survey.get(i).getUser_id());
            creators.add(testUsername);
        }

        modelAndView.addObject("sondages", survey);
        modelAndView.addObject("cities", cities);
        modelAndView.addObject("creators", creators);

        modelAndView.setViewName("home"); // resources/template/home.html
        return modelAndView;
    }

    @RequestMapping(value = "/create-survey", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        Survey survey = new Survey();

        List<Survey> listSurvey = surveyService.getSurveyFromUserId(activeUser.getAuth_user_id());
        List<String> cities = cityService.getAllCities();
//        List<String> creators = new ArrayList<>();

//        String testCity = "";
//        String testUsername = "";
//
//        for (int i = 0; i < listSurvey.size(); i++) {
//            testCity = cityService.getCityById(listSurvey.get(i).getCity_id());
//            cities.add(testCity);
//
//            testUsername = userService.getUsernamebyId(listSurvey.get(i).getUser_id());
//            creators.add(testUsername);
//        }

        modelAndView.addObject("sondages", listSurvey);
        modelAndView.addObject("cities", cities);
        //modelAndView.addObject("creators", creators);
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
        List<String> cities = cityService.getAllCities();

        modelAndView.addObject("sondages", listSurvey);
        modelAndView.addObject("cities", cities);
        //modelAndView.addObject("creators", creators);
        modelAndView.addObject("user_id", user_id);

        modelAndView.setViewName("home"); // resources/template/home.html
        return "home";
    }

    @RequestMapping(value="/create-survey", method=RequestMethod.POST)
    public ModelAndView createSurvey(Survey survey, BindingResult bindingResult, ModelMap modelMap, @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        ModelAndView modelAndView = new ModelAndView();

        survey.setDate(date);

        if(bindingResult.hasErrors() && survey.getDate() == null) {
            List<String> cities = cityService.getAllCities();
            System.out.println("***** ERROR *****");
//
//                    System.out.println("--------" +
//                    "\nName = " + survey.getName() +
//                    "\nDate = " + survey.getDate() +
//                    "\nActivity = " + survey.getActivity() +
//                    "\nAttendance = " + survey.getAttendance() +
//                    "\nUser = " + survey.getUser_id() +
//                    "\nCity = " + survey.getCity_id() +
//                    "\n--------"
//            );
            System.out.println("***********\n");

            modelMap.addAttribute("cities", cities);
            modelAndView.addObject("message", "Veuillez rectifier les erreurs du formulaire !");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
        else {

            List<String> cities = cityService.getAllCities();
            modelMap.addAttribute("cities", cities);

            System.out.println("\n***** SUCCESS : GO CHECK DATABASE *****");
            surveyService.saveSurvey(survey);
            modelAndView.addObject("message", "Le sondage a été crée avec succès !");
        }
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("create-survey");
        return modelAndView;
    }
}
