package com.example.demo.Controller;

import com.example.demo.Model.Survey;
import com.example.demo.Model.User;
import com.example.demo.Service.CityService;
import com.example.demo.Service.SurveyService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
public class SurveyController {

    @Autowired
    SurveyService surveyService;

    @Autowired
    CityService cityService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();

        int user_id = 1;

        List<Survey> survey = surveyService.getSurveyFromUserId(user_id);
        List<String> cities = new ArrayList<>();
        List<String> creators = new ArrayList<>();

        String testCity = "";
        String testUsername = "";

        System.out.println("Liste des sondages \n");
        for (int i = 0; i < survey.size(); i++) {

            testCity = cityService.getCityById(survey.get(i).getCity_id());
            cities.add(testCity);

            testUsername = userService.getUsernamebyId(survey.get(i).getUser_id());
            creators.add(testUsername);
//            System.out.println(
//                    "Nom du sondage : " + survey.get(i).getName() + "\n" +
//                    "Date : " + survey.get(i).getDate() + "\n" +
//                    "Activite : " + survey.get(i).getActivity() + "\n" +
//                    "Createur : " + testUsername + "\n" +
//                    "Ville : " + testCity + "\n\n"
//            );
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
        modelAndView.addObject("survey", survey);
        modelAndView.setViewName("create-survey"); // resources/template/create-survey.html
        return modelAndView;
    }

    @RequestMapping(value="/create-survey", method=RequestMethod.POST)
    public ModelAndView createSurvey(Survey survey, BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        // Check for the validations
        if(bindingResult.hasErrors()) {
            System.out.println("--------" +
                    "\nName = " + survey.getName() +
                    "\nDate = " + survey.getDate() +
                    "\nActivity = " + survey.getActivity() +
                    "\nAttendance = " + survey.getAttendance() +
                    "\nUser = " + survey.getUser_id() +
                    "\nCity = " + survey.getCity_id() +
                    "\n--------"
            );
            modelAndView.addObject("message", "Please correct the errors in form!");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
//        else if(userService.doesUserExist(survey)){
//            modelAndView.addObject("message", "user already exists!");
//        }
//        // we will save the user if, no binding errors
        else {
            System.out.println("1");
            surveyService.saveSurvey(survey);
            modelAndView.addObject("message", "User is registered successfully!");
        }
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("create-survey");
        return modelAndView;
    }


}
