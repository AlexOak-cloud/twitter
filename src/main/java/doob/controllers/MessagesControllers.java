package doob.controllers;


import doob.entity.Message;
import doob.entity.User;
import doob.repositoryes.MessageRepository;
import doob.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;

@Controller
public class MessagesControllers {

    @Autowired
    private MessageRepository messageRepository;





}
