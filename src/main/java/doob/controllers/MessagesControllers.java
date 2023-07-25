package doob.controllers;


import doob.entity.User;
import doob.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MessagesControllers {

    @Autowired
    private MessageService messageService;



    @GetMapping("/dialogs")
    private ModelAndView dialogs(@AuthenticationPrincipal User authUser){



        return new ModelAndView()
    }




}
