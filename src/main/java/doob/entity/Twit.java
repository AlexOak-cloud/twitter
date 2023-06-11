package doob.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Twit implements Comparable<Twit> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "context")
    private String context;
    @Column(name = "dateTime")
    private LocalDateTime dataTime;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public int compareTo(Twit twit) {
        return getDataTime().compareTo(twit.getDataTime());
    }
}
