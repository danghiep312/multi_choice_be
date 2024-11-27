package com.example.config.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

@Entity
@Table(name = "configs")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class ConfigEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time")
    private OffsetDateTime startTime;

    @Column(name = "duration")
    private Long duration;

}
