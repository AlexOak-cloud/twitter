package doob.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@AllArgsConstructor
public class Message {
    private String context;
    private User sender;
    private User recipient;
    private LocalDateTime dateTime;
    private String path;
}
