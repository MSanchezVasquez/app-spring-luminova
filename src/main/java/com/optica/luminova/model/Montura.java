package com.optica.luminova.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Montura {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String nombre;

   private String color;

   private String precio;

   private String material;

   private String url;

   @ManyToOne
   @JoinColumn(name = "categoria_id", nullable = false)
   private Categoria categoria;
}
