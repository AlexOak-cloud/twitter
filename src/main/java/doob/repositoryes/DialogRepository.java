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


    public List<Path> getAllPathsByUser(User user) {
        Set<Dialog> dialogs = new HashSet<>();
        try {
            List<Path> allPaths = Files.walk(Paths.get(path + user.getId()))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
            return allPaths;

        } catch (IOException ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }


    public List<File> findFilesByPaths(List<Path> paths) {
        List<File> rtnList = new ArrayList<>();
        for (Path tmp : paths) {
            File file = tmp.toFile();
            rtnList.add(file);
        }
        return rtnList;
    }


    public Dialog buildDialog(File file) {
        Dialog dialog = new Dialog();
        try(FileReader fileReader = new FileReader(file)){
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.readLine() != null){
                Message message = new Message();
                message.setContext(bufferedReader.readLine());
                dialog.addMessage(message);
            }
            return dialog;
        }catch (IOException exception){
            exception.printStackTrace();
            return new Dialog();
        }
    }


    public Set<Dialog> findAllByUser(User user){
        Set<Dialog> rtnSet = new HashSet<>();
        List<Path> allPathsByUser = getAllPathsByUser(user);
        List<File> filesByPaths = findFilesByPaths(allPathsByUser);
        for(File tmp : filesByPaths){
            rtnSet.add(buildDialog(tmp));
        }
        return rtnSet;
    }


}





