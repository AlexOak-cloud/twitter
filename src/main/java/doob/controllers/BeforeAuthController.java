package doob.controllers;

import doob.entity.User;
import doob.services.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BeforeAuthController {

    @Autowired
    private FriendsService friendsService;


    @GetMapping("/beforeAuth")
    public ModelAndView beforeAuth(@AuthenticationPrincipal User authUser) {
        friendsService.createTable(authUser);

        return new ModelAndView("redirect:/main");
    }




}
