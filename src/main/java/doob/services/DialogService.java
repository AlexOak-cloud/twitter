package doob.services;

import doob.entity.Message;
import doob.entity.User;
import doob.repositoryes.DialogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class DialogService {


    @Autowired
    private DialogRepository dialogRepository;

    public List<String> getMessagesByUser(){
        File file = new File("E:/Code/messages/17/17_11");
        return dialogRepository.getMessagesByUser(file);
    }



}
