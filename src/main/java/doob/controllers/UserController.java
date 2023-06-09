package doob.controllers;


import doob.entity.Twit;
import doob.entity.User;
import doob.services.TwitService;
import doob.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private TwitService twitService;

    @GetMapping("/user/main")
    public ModelAndView main(@AuthenticationPrincipal User authUser, @ModelAttribute Twit twit){
        ModelAndView mav = new ModelAndView();
        mav.addObject("twit",new Twit());
        mav.addObject("userTwits", twitService.findAllByUser(authUser));
        mav.addObject("authUser",authUser);
        mav.setViewName("user/main.html");
        return mav;
    }


}
