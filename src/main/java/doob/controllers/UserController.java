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

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TwitService twitService;

    @Autowired
    private FriendsService friendsService;

    @GetMapping("/authUser/main")
    public ModelAndView main(@AuthenticationPrincipal User authUser, @ModelAttribute Twit twit) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("twit", new Twit());
        mav.addObject("userTwits", twitService.reverseList(twitService.findAllByUser(authUser)));
        mav.addObject("authUser", authUser);
        mav.addObject("friends", friendsService.findAllByUser(authUser));
        mav.addObject("isUnique", userService.isUsernameUnique(authUser));
        mav.setViewName("authUser/main.html");
        return mav;
    }


    @GetMapping("user/getById/{id}")
    public ModelAndView getById(@AuthenticationPrincipal User authUser, @PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView();

        User userById = userService.findById(id);

        if (id == authUser.getId()) {
            mav.setViewName("redirect:/authUser/main");
            return mav;
        }
        mav.addObject("isFriend", friendsService.isFriend(authUser, userById));
        mav.addObject("allTwitsByUser", twitService.findAllByUser(userById));
        mav.addObject("userById", userById);
        mav.addObject("friends", friendsService.findAllByUser(userById));
        mav.setViewName("users/getById.html");
        return mav;
    }




}
