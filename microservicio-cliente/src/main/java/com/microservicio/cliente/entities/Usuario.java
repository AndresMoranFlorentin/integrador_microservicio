package com.microservicio.cliente.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "\"usuario\"")
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
   // @OneToMany(fetch = FetchType.LAZY,mappedBy = "id_usuario",cascade = CascadeType.ALL)
   @ManyToMany
   @JoinTable(
           name = "usuario_cuenta",
           joinColumns = @JoinColumn(name = "usuario_id"))
   @JsonIgnore
    private List<Cuenta> cuentas;

    public Usuario (String nombre,Long cel,String mail,String apellido){
        this.nombre=nombre;
        this.apellido=apellido;
        this.celular=cel;
        this.email=mail;
    }
    public void addCuenta(Cuenta cuentaN) {
        this.cuentas.add(cuentaN);
    }
}
