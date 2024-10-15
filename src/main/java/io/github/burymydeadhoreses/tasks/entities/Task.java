package io.github.burymydeadhoreses.tasks.entities;

import io.github.burymydeadhoreses.tasks.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Setter
    @Column(nullable = false)
    private String description;

    @Setter
    private Status status = Status.Created;

    private Date date = new Date();
}
