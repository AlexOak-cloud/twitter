package doob.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.File;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Transient
    private File file;
    @Column(name = "path")
    private String path;
    @OneToOne
    @JoinColumn(name = "sender_id")
    private User sender;




}
