package doob.entity;

import lombok.*;

import java.util.List;


@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@AllArgsConstructor
public class Dialog {
    private User recipient;
    private User sender;
    private List<Message> messages;

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message message){
        messages.add(message);
    }


}
