package doob.controllers;


import doob.entity.Dialog;
import doob.entity.Message;
import doob.entity.User;
import doob.repositoryes.DialogRepository;
import doob.services.DialogService;
import doob.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class MessagesController {

    @Autowired
    private DialogService messageService;
    @Autowired
    private UserService userService;
    @Autowired
    private DialogRepository dialogRepository;




    @GetMapping("/message/{id}")
    public ModelAndView messages(@AuthenticationPrincipal User authUser, @ModelAttribute("message") Message message,@PathVariable("id") int id){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("messages.html");
        User userById = userService.findById(id);
        Dialog dialog = dialogRepository.getDialog(authUser, userById);

        mav.addObject("messages",dialog.getMessages());
        return mav;
    }


    @PostMapping("/messages")
    public ModelAndView messagePost(Message message, @AuthenticationPrincipal User authUser) {
        User userById = userService.findById(18);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/messages");
        mav.addObject("message", new Message());
        String contentForSave = authUser.getId() + " " + LocalDateTime.now() + " " + message.getContext();
        messageService.save(authUser, userById, contentForSave);
        return mav;
    }


}
