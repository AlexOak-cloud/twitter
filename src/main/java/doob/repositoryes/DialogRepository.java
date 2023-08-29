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

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

@Repository
public class DialogRepository {


    @Autowired
    private Utils utils;

    @Autowired
    private UserService userService;

    public List<File> getAllFilesByUser(User user) {
        List<File> rtnList = new ArrayList<>();
        File folder = new File(utils.getFolder(user));
        File[] files = folder.listFiles();
        if(files == null) return rtnList;
        for (File file : files) {
            if (file.isFile()) {
                rtnList.add(file);
            }
        }
        return rtnList;
    }



    public File getFileByUsers(User sender, User recipient) {
        return new File(utils.getFile(sender,recipient));
    }

    public List<Dialog> getDialogs(User user) {
        List<Dialog> rtnList = new ArrayList<>();
        List<File> allFilesByUser = getAllFilesByUser(user);
        for (File file : allFilesByUser) {
            User recipient = userService.findById(Integer.parseInt(file.getName().substring(0, file.getName().length() - 4)));
            List<String> stringFromFile = getStringFromFile(file);
            List<Message> messages = convertInMessage(stringFromFile);
            rtnList.add(new Dialog(recipient, user, messages));

        }
        return rtnList;
    }

    public File[] getFileByUser(User sender, User recipient) {
        File folderSender = new File(utils.getFolder(sender));
        File folderRecipient = new File(utils.getFolder(recipient));
        File fileForSender = new File(utils.getFile(sender,recipient));
        File fileForRecipient = new File(utils.getFile(recipient,sender));
        if (!folderSender.exists()) folderSender.mkdir();
        if (!folderRecipient.exists()) folderRecipient.mkdir();
        try {
            if (!fileForSender.exists()) fileForSender.createNewFile();
            if (!fileForRecipient.exists()) fileForRecipient.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        File[] files = new File[2];
        files[0] = fileForSender;
        files[1] = fileForRecipient;
        return files;
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





