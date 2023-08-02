package doob.controllers;


import doob.entity.Dialog;
import doob.entity.Message;
import doob.entity.User;
import doob.repositoryes.DialogRepository;
import doob.repositoryes.MessageRepository;
import doob.services.DialogService;
import doob.services.MessageService;
import doob.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Controller
public class MessagesControllers {

    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @Autowired
    private DialogService dialogService;

    @GetMapping("/messages")
    public ModelAndView messages(@AuthenticationPrincipal User authUser, @ModelAttribute("message")Message message) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("messages.html");
        List<String> dialogsByAuthUser = dialogService.getMessagesByUser();
        mav.addObject("dialogsByAuthUser", dialogsByAuthUser);


        return mav;
    }

    @PostMapping("/messages")
    public ModelAndView messagePost(Message message,@AuthenticationPrincipal User authUser) {
        User userById = userService.findById(11);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/messages");
        mav.addObject("message", new Message());

        messageService.save(authUser,userById,message.getContext());
        return mav;
    }


}
