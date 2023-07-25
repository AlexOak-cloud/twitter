package doob.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@AllArgsConstructor
public class Dialog {
    private User recipient;
    private User sender;
    private Set<Message> messages;
}
