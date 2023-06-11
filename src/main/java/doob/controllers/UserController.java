package doob.controllers;


import doob.entity.Twit;
import doob.entity.User;
import doob.services.FriendsService;
import doob.services.TwitService;
import doob.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TwitService twitService;

    @Autowired
    private FriendsService friendsService;

    @GetMapping("/user/main")
    public ModelAndView main(@AuthenticationPrincipal User authUser, @ModelAttribute Twit twit) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("twit", new Twit());
        mav.addObject("userTwits", twitService.findAllByUser(authUser));
        mav.addObject("authUser", authUser);
        mav.addObject("allUsers", userService.findAll());
        mav.addObject("friends", friendsService.findAllByUser(authUser));
        mav.setViewName("user/main.html");
        return mav;
    }


    @GetMapping("/user/getById/{id}")
    public ModelAndView getById(@AuthenticationPrincipal User authUser, @PathVariable("id") int id) {
        User userById = userService.findById(id);
        ModelAndView mav = new ModelAndView();
        if(id == authUser.getId()){
            mav.setViewName("redirect:/user/main");
            return mav;
        }
        List<Twit> allTwtitsByUser = twitService.findAllByUser(userById);
        mav.addObject("allTwitsByUser", allTwtitsByUser);
        mav.addObject("userById", userById);
        mav.addObject("friends", friendsService.findAllByUser(userById));
        mav.setViewName("/user/getById.html");
        return mav;
    }


}
