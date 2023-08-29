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
    private DialogService dialogService;




    @GetMapping("/message/{id}")
    public ModelAndView messages(@AuthenticationPrincipal User authUser, @ModelAttribute("message") Message message,@PathVariable("id") int id){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/dialogs/messages.html");
        User userById = userService.findById(id);
        mav.addObject("userById",userById);
        Dialog dialog = dialogService.getDialog(authUser, userById);
        mav.addObject("messages",dialog.getMessages());
        return mav;
    }


    @PostMapping("/messages/new/{id}")
    public ModelAndView messagePost(Message message, @AuthenticationPrincipal User authUser,@PathVariable("id") int id) {
        User userById = userService.findById(id);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/message/{id}");
        mav.addObject("message", new Message());
        String content = authUser.getId() + " " + LocalDateTime.now() + " " + message.getContext();
        messageService.save(authUser, userById, content);
        return mav;
    }


}
