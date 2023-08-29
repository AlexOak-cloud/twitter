package doob.repositoryes;

import doob.entity.User;
import org.springframework.stereotype.Component;

@Component
public class Utils {


    public static final String PATH = "E:/Code/messages/";
    public static final String FORMAT = ".txt";

    public String getFolder(User user){
        return PATH + user.getId() + "/";
    }

    public String getFile(User sender, User recipient){
        return getFolder(sender) + recipient.getId() + FORMAT;
    }



}
