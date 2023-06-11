package doob.controllers;


import doob.entity.Twit;
import doob.entity.User;
import doob.services.TwitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private TwitService twitService;

    @GetMapping("main")
    public ModelAndView mainPage(@AuthenticationPrincipal User authUser){
        ModelAndView mav = new ModelAndView();
        mav.addObject("authUser", authUser);
        List<Twit> allTwits = twitService.findAll();
        twitService.sortedListByDateTime(allTwits);
        mav.addObject("allTwits", allTwits);






        return mav;
    }


}
