package com.example.quiz.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "quizes")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class QuizEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Column(name = "config_id")
    private Long configId;

    @Column(name = "author_name")
    private String authorName;
}
