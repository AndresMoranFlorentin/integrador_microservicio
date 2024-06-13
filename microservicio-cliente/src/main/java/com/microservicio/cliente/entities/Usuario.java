package com.microservicio.cliente.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_usuario;
    @Column
    private String nombre;
    @Column
    private Long celular;
    @Column
    private String email;
    @Column
    private String apellido;
    @ManyToMany(mappedBy = "usuarios")
    private List<Cuenta> cuentas;

    public void addCuenta(Cuenta cuentaN) {
        this.cuentas.add(cuentaN);
    }
}
