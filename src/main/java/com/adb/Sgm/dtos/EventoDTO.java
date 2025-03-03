package com.adb.Sgm.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventoDTO {
   private UUID id;
   private String titulo;
   private String inicio;
   private String fim;
   private boolean dia_todo;
   private int dia;

   public LocalDateTime getInicioLocalDateTime() {
      return OffsetDateTime.parse(inicio).toLocalDateTime();
   }

   public LocalDateTime getFimLocalDateTime() {
      return OffsetDateTime.parse(fim).toLocalDateTime();
   }
}
