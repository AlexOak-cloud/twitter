package doob.controllers;


import doob.entity.Message;
import doob.entity.User;
import doob.services.DialogService;
import doob.services.MessageService;
import doob.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public ModelAndView messages(@AuthenticationPrincipal User authUser, @ModelAttribute("message") Message message) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("messages.html");
        User userById = userService.findById(17);
        Set<Message> messages = messageService.getMessages(authUser, userById);
        List<String> forIteration = new ArrayList<>();
        for (Message tmp : messages) {
            forIteration.add(tmp.getContext());
        }


        mav.addObject("list", forIteration);
        return mav;
    }

    @PostMapping("/messages")
    public ModelAndView messagePost(Message message, @AuthenticationPrincipal User authUser) {
        User userById = userService.findById(17);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/messages");
        mav.addObject("message", new Message());
        String contentForSave = authUser.getId() + " " + LocalDateTime.now() + " " + message.getContext();
        messageService.save(authUser, userById, contentForSave);
        return mav;
    }


}
