package doob.repositoryes;


import doob.entity.User;
import doob.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DialogRepository {

    private String path = "E:/Code/messages/";

    @Autowired
    private MessageService messageService;

    public List<File> getAllFilesByUser(User user) {
        List<File> rtnList = new ArrayList<>();
        File folder = new File(this.path + user.getId());
        File[] files = folder.listFiles();
        for (File tmp : files) {
            if (tmp.isFile()) {
                rtnList.add(tmp);
            }
        }
        return rtnList;
    }









}





