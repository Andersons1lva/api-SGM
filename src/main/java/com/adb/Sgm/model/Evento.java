package com.adb.Sgm.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "eventos")
@Table(name = "eventos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String titulo;
//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") // Formato ISO-8601
    private LocalDate inicio;
//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDate fim;
    private boolean diaTodo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
