package doob.controllers;

import doob.entity.Dialog;
import doob.entity.User;
import doob.repositoryes.DialogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DialogsController {


    @Autowired
    private DialogRepository dialogRepository;


    @GetMapping("/dialogs")
    public ModelAndView messages(@AuthenticationPrincipal User authUser) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/dialogs/dialogs.html");
        List<Dialog> dialogs = dialogRepository.getDialogs(authUser);

            mav.addObject("dialogs", dialogs);
        return mav;
    }



}
