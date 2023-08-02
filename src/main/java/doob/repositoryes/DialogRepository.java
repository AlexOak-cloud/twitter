package doob.repositoryes;


import doob.entity.Dialog;
import doob.entity.Message;
import doob.entity.User;
import doob.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.lang.annotation.Retention;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class DialogRepository {

    private String path = "E:/Code/messages/";

    @Autowired
    private MessageService messageService;


    public List<String> getMessagesByUser(File file){
        List<String> messages = new ArrayList<>();
        try(FileReader fileReader = new FileReader(file)){
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.readLine() != null){
                messages.add(bufferedReader.readLine());
            }
            return messages;
        }catch (IOException ex){
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

}





