package controllers;

import entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.CityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Almaz on 07.04.2016.
 */
@Controller
@RequestMapping("")
public class MainPageController {
    @Autowired
    CityService cityService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String mainPage(ModelMap model, @RequestParam(required = false, value = "city", defaultValue = "Москва") String city) {
        List cities = cityService.getAllCities();
        model.addAttribute("city", city);
        model.addAttribute("cities", cities);
        return "/main/main";
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public String contactsPage(ModelMap model, @RequestParam(required = false, value = "city") String city) {
        List cities = cityService.getAllCities();
        model.addAttribute("city", city);
        model.addAttribute("cities", cities);
        return "/main/contacts";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model, @RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {
        if (error != null) {
            model.addAttribute("error", "Invalid username and password!");
        }
        if (logout != null) {
            model.addAttribute("msg", "You've been logged out successfully.");
        }
        return "/cabinet/login";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/cabinet", method = RequestMethod.GET)
    public String cabinet(ModelMap model) {
        Person person = (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String role = String.valueOf(person.getRole());

        if (role.equals("ROLE_PASSENGER")) {
            return "redirect:/client";
        }

        if (role.equals("ROLE_DRIVER")) {
            return "redirect:/driver";
        }
        return "/cabinet/login";
    }
}
