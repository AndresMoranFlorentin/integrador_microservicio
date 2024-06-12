package com.microservicio.cliente.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_cuenta;
    @Column
    private String nombre_cuenta;
    @Column
    private Float monto;
    @Column
    private LocalDate fecha_de_alta;
    @ManyToMany
    private List<Usuario> usuarios;

    public Cuenta(String nombre,Float monto,LocalDate fecha_de_alta) {
        this.nombre_cuenta=nombre;
        this.monto=monto;
        this.fecha_de_alta=fecha_de_alta;
    }


    public void addUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void descontarMonto(Float cobro) {
        Float resto=(this.monto-cobro);
        if(resto>=0.00){
            this.monto=resto;
        }
    }
}
