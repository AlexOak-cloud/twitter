package doob.repositoryes;


import doob.entity.Dialog;
import doob.entity.Message;
import doob.entity.User;
import doob.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

@Repository
public class DialogRepository {

    public static final String PATH = "E:/Code/messages/";

    @Autowired
    private UserService userService;

    public List<File> getAllFilesByUser(User user) {
        File folder = new File(PATH + user.getId());
        List<File> files = new ArrayList<>();
        files.toArray(folder.listFiles());
        return files;
    }

    public File getFileByUsers(User sender, User recipient) {
        return new File(PATH + sender.getId() + "/" + recipient.getId() + ".txt");
    }

    public List<Dialog> getDialogs(User user) {
        List<Dialog> rtnList = new ArrayList<>();
        List<File> allFilesByUser = getAllFilesByUser(user);
        if(allFilesByUser != null && allFilesByUser.isEmpty()) {
            for (File file : allFilesByUser) {
                User recipient = userService.findById(Integer.parseInt(file.getName().substring(0, file.getName().length() - 4)));
                List<String> stringFromFile = getStringFromFile(file);
                List<Message> messages = convertInMessage(stringFromFile);
                rtnList.add(new Dialog(recipient, user, messages));
            }
        }
        return rtnList;
    }

    public Dialog getDialog(User sender, User recipient) {
        Dialog dialog = new Dialog();
        dialog.setSender(sender);
        dialog.setRecipient(recipient);
        File file = getFileByUsers(sender, recipient);
        List<Message> messages = convertInMessage(getStringFromFile(file));
        dialog.setMessages(messages);
        return dialog;
    }

    public boolean save(File forSender, File forRecipient, String content) {
        try {
            FileWriter writerForSender = new FileWriter(forSender, true);
            FileWriter writerForRecipient = new FileWriter(forRecipient, true);
            writerForSender.write(content + "\n");
            writerForRecipient.write(content + "\n");
            writerForSender.close();
            writerForRecipient.close();
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<String> getStringFromFile(File file) {
        List<String> strings = new ArrayList<>();
        try (FileReader fileReader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                strings.add(line);
                line = bufferedReader.readLine();
            }
            return strings;
        } catch (IOException ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<Message> convertInMessage(List<String> strings) {
        List<Message> messages = new ArrayList<>();
        for (String tmp : strings) {
            String[] words = tmp.split("\s");
            User sender = userService.findById(Integer.parseInt(words[0]));
            LocalDateTime dateTime = LocalDateTime.parse(words[1], ISO_DATE_TIME);
            String context = words[2];
            messages.add(new Message(0, context, sender, dateTime));
        }
        return messages;
    }


}





