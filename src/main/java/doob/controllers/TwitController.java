package doob.controllers;


import doob.entity.Twit;
import doob.entity.User;
import doob.services.TwitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Controller
public class TwitController {

    @Autowired
    private TwitService twitService;

    @PostMapping("/twit/new")
    public ModelAndView newTwit(@AuthenticationPrincipal User user, @ModelAttribute("twit") Twit twit){
        twit.setDataTime(LocalDateTime.now());
        twit.setUser(user);
        twitService.save(twit);
        return new ModelAndView("redirect:/authUser/main");
    }




}
