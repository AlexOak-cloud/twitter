package doob.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Getter

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@AllArgsConstructor
public class Dialog {
    private User recipient;
    private User sender;
    private Set<Message> messages;

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message message){
        messages.add(message);
    }


}
