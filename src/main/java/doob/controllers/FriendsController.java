package doob.controllers;

import doob.entity.User;
import doob.services.FriendsService;
import doob.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FriendsController {

    @Autowired
    private FriendsService friendsService;

    @Autowired
    private UserService userService;


    @PostMapping("/friends/new/{id}")
    public ModelAndView addFriend(@AuthenticationPrincipal User authUser, @PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView();
        User userById = userService.findById(id);
        friendsService.addFriend(authUser, userById);


        mav.setViewName("redirect:/user/getById/" + userById.getId());
        return mav;
    }


    @GetMapping("userById/{id}/friends")
    public ModelAndView findAllById(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("friends.html");
        User userById = userService.findById(id);
        mav.addObject("friends", friendsService.findAllByUser(userById));
        mav.addObject("userById", userById);
        return mav;
    }

    @GetMapping("/authUser/friends")
    public ModelAndView authUserFriends(@AuthenticationPrincipal User authUser){
        ModelAndView mav = new ModelAndView();
        mav.addObject("friends",friendsService.findAllByUser(authUser));
        mav.addObject("authUser", authUser);
        mav.setViewName("friends.html");

        return mav;
    }



}
