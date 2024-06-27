package com.microservicio.cliente.services;

import com.microservicio.cliente.clients.ClientFeignAdministracion;
import com.microservicio.cliente.clients.ClientMonopatin;
import com.microservicio.cliente.dto.*;
import com.microservicio.cliente.models.MonopatinDTO;
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
    @Autowired
    private ClientMonopatin clientMonopatin;

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
    public Viaje pedirViaje(@PathVariable("id_usuario")Long id_usuario,@PathVariable("ubicacion")String ubicacion) {
        Viaje viaje=clientFeignAdministracion.pedirViaje(id_usuario,ubicacion);
        if(viaje==null){
            return null;
        }
        return viaje;
    }
    @Transactional
    public List<MonopatinDto> getMonopatinesCercanos(@PathVariable("latitud") Double latitud, @PathVariable("longitud") Double longitud) {
        List<MonopatinDto> lista=clientMonopatin.getMonopatinesCercanos(latitud,longitud);
        if(lista.isEmpty()){
            return null;
        }
        return lista;
    }
    @Transactional
    public UsuarioDto registrarUsuario(@PathVariable("usuario")UsuarioDto usuario) {
        Usuario nuevo=new Usuario(usuario.getNombre(),usuario.getCelular(),usuario.getEmail(), usuario.getApellido());
        repositoryUsuario.save(nuevo);
        return usuario;
    }
    @Transactional
    public CuentaDTO registrarCuenta(@PathVariable("cuenta") CuentaDTO cuenta) {
            Cuenta c = new Cuenta(cuenta.getNombre_cuenta(),cuenta.getMonto());
            cuenta.setFecha_de_alta(c.getFecha_de_alta());
            repositoryCuenta.save(c);//guardo la cuenta
        return cuenta;
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
                                       @PathVariable("cobro") Double cobro) throws Exception {
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
    @Transactional
    public CuentaDTO inhabilitarCuenta(Long id_cuenta) {
        Cuenta cuenta=repositoryCuenta.findById(id_cuenta).orElse(null);
        if(cuenta.isHabilitado()==true){
            cuenta.deshabilitar();
            repositoryCuenta.saveAndFlush(cuenta);
            CuentaDTO resp=new CuentaDTO(cuenta.getNombre_cuenta(),cuenta.getMonto(),cuenta.isHabilitado(),cuenta.getFecha_de_alta());
            return resp;
        }
        else{
            return null;
        }
    }
    @Transactional
    public CuentaDTO habilitarCuenta(@PathVariable("id_cuenta")Long id_cuenta) {
        Cuenta cuenta = repositoryCuenta.findById(id_cuenta).orElse(null);
        if (cuenta != null) {
            cuenta.habilitar();
            repositoryCuenta.saveAndFlush(cuenta);
            CuentaDTO resp = new CuentaDTO(cuenta.getNombre_cuenta(), cuenta.getMonto(), cuenta.isHabilitado(),cuenta.getFecha_de_alta());
            return resp;
        } else {
            return null;
        }
    }

    public ViajeDto generarViaje(ViajeInicioDto viaje) {
        ViajeDto nuevo=clientMonopatin.save(viaje);
        return nuevo;
    }
    @Transactional
    public void finalizarViaje(@PathVariable Long idViaje) {
        clientMonopatin.finViaje(idViaje);
    }
}
