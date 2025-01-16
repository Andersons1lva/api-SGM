package com.adb.Sgm.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventoDTO{

   private UUID id;
   private String titulo;
   private String inicio;
   private String fim;
   private boolean dia_todo;
}
