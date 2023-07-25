package doob.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Dialog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @OneToOne
    @JoinColumn(name = "recipient_id")
    private User recipient;
    @OneToOne
    @JoinColumn(name = "sender_id")
    private User sender;
    private Set<Message> messages;


}
