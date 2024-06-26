package com.microservicio.cliente.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cuenta;
    @Column
    private String nombre_cuenta;
    @Column
    private Double monto;
    @Column
    private boolean habilitado=true;
    @Column
    private LocalDateTime fecha_de_alta;
    @ManyToMany(mappedBy = "cuentas")
    @JsonIgnore
    private List<Usuario> usuarios;

    public Cuenta(String nombre,Double monto) {
        this.nombre_cuenta=nombre;
        this.monto=monto;
        this.fecha_de_alta=LocalDateTime.now();
    }


    public void addUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void descontarMonto(Double cobro) {
        Double resto=(this.monto-cobro);
        if(resto>=0.00){
            this.monto=resto;
        }
    }
    public void deshabilitar(){
        this.habilitado=false;
    }
    public void habilitar(){
        this.habilitado=true;
    }
}
