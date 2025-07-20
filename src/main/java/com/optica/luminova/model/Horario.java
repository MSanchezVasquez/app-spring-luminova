package com.optica.luminova.model;

public class Horario {
   private String nombre;
   private String especialidad;
   private String dias;
   private String horario;

   public Horario(String nombre, String especialidad, String dias, String horario) {
      this.nombre = nombre;
      this.especialidad = especialidad;
      this.dias = dias;
      this.horario = horario;
   }

   public String getNombre() {
      return nombre;
   }

   public String getEspecialidad() {
      return especialidad;
   }

   public String getDias() {
      return dias;
   }

   public String getHorario() {
      return horario;
   }
}
