package com.microservicio.cliente.services;

import com.microservicio.cliente.clients.ClientFeignAdministracion;
import com.microservicio.cliente.dto.CuentaDto;
import com.microservicio.cliente.dto.UsuarioDto;
import com.microservicio.cliente.models.Monopatin;
import com.microservicio.cliente.models.Viaje;
import com.microservicio.cliente.entities.Cuenta;
import com.microservicio.cliente.entities.Usuario;
import com.microservicio.cliente.repositories.RepositoryCuenta;
import com.microservicio.cliente.repositories.RepositoryUsuario;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioCliente {
    @Autowired
    private RepositoryUsuario repositoryUsuario;
    @Autowired
    private RepositoryCuenta repositoryCuenta;
    @Autowired
    private ClientFeignAdministracion clientFeignAdministracion;

    @Transactional
    public UsuarioDto existeCuenta(@PathVariable("id_usuario") Long id_usuario) {
        Optional<Usuario> usuario=repositoryUsuario.findById(id_usuario);
        if(usuario!=null){
            UsuarioDto nuevo=new UsuarioDto(usuario.get().getNombre(), usuario.get().getCelular(),usuario.get().getEmail(),usuario.get().getApellido());
            return nuevo;
        }
        return null;
    }
    @Transactional
    public Viaje pedirViaje(Long id_usuario, String ubicacion) {
        Viaje viaje=clientFeignAdministracion.pedirViaje(id_usuario,ubicacion);
        if(viaje==null){
            return null;
        }
        return viaje;
    }
    @Transactional
    public List<Monopatin> getMonopatinesCercanos(String ubicacion) {
        List<Monopatin> lista=clientFeignAdministracion.getMonopatinesMasCercanos(ubicacion);
        if(lista.isEmpty()){
            return null;
        }
        return lista;
    }
    @Transactional
    public UsuarioDto registrarUsuario(Usuario usuario) {
        Usuario existe= repositoryUsuario.findById(usuario.getId_usuario()).orElse(null);
        if(existe==null){//no existe ese usuario entonces...guardo este nuevo
            repositoryUsuario.save(usuario);
            UsuarioDto nuevo=new UsuarioDto(usuario.getNombre(),usuario.getCelular(),usuario.getEmail(), usuario.getApellido());
            return nuevo;
        }
        return null;
    }
    @Transactional
    public CuentaDto registrarCuenta(@PathVariable("cuenta") Cuenta cuenta) {
        Cuenta cuentaN=repositoryCuenta.findById(cuenta.getId_cuenta()).orElse(null);
        if(cuentaN==null){
            repositoryCuenta.save(cuenta);//guardo la cuenta
            CuentaDto nuevo=new CuentaDto(cuenta.getNombre_cuenta(),cuenta.getMonto(),cuenta.getFecha_de_alta());
            return nuevo;
        }
        return null;
    }
    @Transactional
    public UsuarioDto asignarCuentaAUsuario(@PathVariable("id_cuenta") Long id_cuenta,@PathVariable("id_usuario")Long id_usuario) {
        Cuenta cuentaN=repositoryCuenta.findById(id_cuenta).orElse(null);
        Usuario usuario=repositoryUsuario.findById(id_usuario).orElse(null);

        if(cuentaN!=null && usuario!=null){//compruebo que existan el usuario y la cuenta
            usuario.addCuenta(cuentaN);
            cuentaN.addUsuario(usuario);
            repositoryCuenta.saveAndFlush(cuentaN);
            repositoryUsuario.saveAndFlush(usuario);

            return new UsuarioDto();
        }
        return null;
    }
    @Transactional
    public boolean deleteCuenta(@PathVariable("id_cuenta") Long id_cuenta) throws Exception {
            try {
                if (repositoryCuenta.existsById(id_cuenta)) {
                    repositoryCuenta.deleteById(id_cuenta);
                    return true;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }
    @Transactional
    public boolean deleteUsuario(@PathVariable("id_usuario") Long id_usuario) throws Exception {
        try {
            if (repositoryUsuario.existsById(id_usuario)) {
                repositoryUsuario.deleteById(id_usuario);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    @Transactional
    public boolean descontarDeLaCuenta(@PathVariable("id_cuenta")Long id_cuenta,
                                       @PathVariable("cobro") Float cobro) throws Exception {
        Cuenta nuevo=repositoryCuenta.findById(id_cuenta).orElse(null);
        try {
            if (nuevo!=null) {
                nuevo.descontarMonto(cobro);
                repositoryCuenta.saveAndFlush(nuevo);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    @Transactional
    public Usuario buscarUsuario(@PathVariable("id_usuario")Long id_usuario) {
        return repositoryUsuario.findById(id_usuario).orElse(null);
    }
    @Transactional
    public Cuenta buscarCuenta(@PathVariable("id_cuenta")Long id_cuenta) {
        Cuenta cuenta =repositoryCuenta.findById(id_cuenta).orElse(null);
        return cuenta;
    }

}
