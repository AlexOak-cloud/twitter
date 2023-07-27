package doob.repositoryes;


import doob.entity.Dialog;
import doob.entity.User;
import doob.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class DialogRepository {

    private String path = "E:/Code/messages/";


    public Set<Dialog> findAllByUser(User user) {
        File file = new File(path + user.getId());
        try {
            List<Path> allDialogs = Files.walk(Paths.get(path + user.getId()))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        }catch (IOException ex){
            ex.printStackTrace();
        }


    }


}
