package doob.controllers;

import doob.entity.User;
import doob.services.FriendsService;
import doob.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {


    @Autowired
    private FriendsService friendsService;
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public ModelAndView registration(@ModelAttribute("user") User user) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("user", new doob.entity.User());
        mav.setViewName("registration/registration.html");
        return mav;
    }

    @PostMapping("/registration")
    public ModelAndView addUser(@Valid User user, BindingResult result) {
        if(!userService.isUsernameUnique(user)){
            return new ModelAndView("/registration/registrationNotUnique.html");
        }
        if(result.hasErrors()){
            return new ModelAndView("registration/registration.html");
        }
        userService.save(user);
        friendsService.createTable(user);
        return new ModelAndView("redirect:/login");
    }
}