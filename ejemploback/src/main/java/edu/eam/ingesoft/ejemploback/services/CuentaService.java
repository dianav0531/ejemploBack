package edu.eam.ingesoft.ejemploback.services;

import edu.eam.ingesoft.ejemploback.model.Cliente;
import edu.eam.ingesoft.ejemploback.model.Cuenta;
import edu.eam.ingesoft.ejemploback.model.InfoTransferencia;
import edu.eam.ingesoft.ejemploback.model.Transaccion;
import edu.eam.ingesoft.ejemploback.repositories.ClienteRepository;
import edu.eam.ingesoft.ejemploback.repositories.CuentaRepository;
import edu.eam.ingesoft.ejemploback.repositories.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private TransaccionRepository transaccionRepository;

    public  Cuenta crearCuenta(Cuenta cuenta) {
        Cliente cliente = clienteRepository.findById(cuenta.getCedulaCliente()).orElse(null);

        if (cliente == null) {
            throw new RuntimeException("No existe el cliente");
        }

        List<Cuenta> cuentasCliente = cuentaRepository.buscarCuentasCliente(cuenta.getCedulaCliente());

        if (cuentasCliente.size() == 3) {
            throw new RuntimeException("El cliente ya tiene el máximo de cuentas");
        }

        cuentaRepository.save(cuenta);

        return cuenta;
    }

    public List<Cuenta> listarCuentasCliente(String cedula) {
        return cuentaRepository.buscarCuentasCliente(cedula);
    }

    public String consignarDinero(Cuenta consignacion) {

        Cuenta cuenta1 = obtenerCuenta(consignacion);

        double dinerocuenta1 = cuenta1.getAmount();
        double dineroConsignacion = consignacion.getAmount();
        double totalConsignacion = dinerocuenta1 + dineroConsignacion;

        cuenta1.setAmount(totalConsignacion);

        Cuenta consignacionGuardada = cuentaRepository.save(cuenta1);

        Transaccion transaccion = new Transaccion(cuenta1.getId(), "consignacion", dineroConsignacion);
        Transaccion transaccionGuardada = transaccionRepository.save(transaccion);

        return transaccionGuardada.getNumero().toString();

    }

    private Cuenta obtenerCuenta(Cuenta cuentaCliente) {
        Cuenta cuentaEncontrada = null;
        List<Cuenta> cuentas = listarCuentasCliente(cuentaCliente.getCedulaCliente());

        for (int i = 0; i < cuentas.size(); i++) {
            if (cuentas.get(i).getId().equals(cuentaCliente.getId())) {
                cuentaEncontrada = cuentas.get(i);
            }
        }
        if (cuentaEncontrada == null) {
            throw new RuntimeException("No existe la cuenta");
        }
        return cuentaEncontrada;
    }


    public String retirarDinero(Cuenta retiro) {

        Cuenta cuenta1 = obtenerCuenta(retiro);

        double dinerocuenta1 = cuenta1.getAmount();
        double dineroRetiro = retiro.getAmount();
        double totalRetiro = dinerocuenta1 - dineroRetiro;

        cuenta1.setAmount(totalRetiro);

        Cuenta retiroGuardado = cuentaRepository.save(cuenta1);

        Transaccion transaccion = new Transaccion(cuenta1.getId(), "retiro", dineroRetiro);
        Transaccion transaccionGuardada = transaccionRepository.save(transaccion);

        return transaccionGuardada.getNumero().toString();
    }

    public void cancelarCuenta(String id) {
        Cuenta cuenta1 = cuentaRepository.getOne(id);

        if (cuenta1 == null) {
            throw new RuntimeException("No existe la cuenta");
        }

        if (cuenta1.getAmount() > 0) {
            throw new RuntimeException("La cuenta tiene saldo");
        }

        cuentaRepository.deleteById(id);
    }

    public InfoTransferencia transferirDinero(InfoTransferencia infoTransferencia) {

        Cuenta cuentaOrigen = cuentaRepository.findById(infoTransferencia.getCuentaOrigen()).orElse(null);
        Cuenta cuentaDestino = cuentaRepository.findById(infoTransferencia.getCuentaDestino()).orElse(null);


        if (cuentaOrigen == null) {
            throw new RuntimeException("La cuenta origen no existe");
        }

        if (cuentaDestino == null) {
            throw new RuntimeException("La cuenta destino no existe");
        }

        if (cuentaOrigen.getAmount() < infoTransferencia.getMonto()) {
            throw new RuntimeException("No tiene saldo suficiente para la transferencia");
        }

        double totalOrigen = cuentaOrigen.getAmount()- infoTransferencia.getMonto();
        double totalDestino = cuentaDestino.getAmount()+ infoTransferencia.getMonto();

        /*Cuenta montoFinalOrigen = cuentaRepository.getOne(infoTransferencia.getCuentaOrigen());
        Cuenta montoFinalDestino = cuentaRepository.getOne(infoTransferencia.getCuentaDestino());*/

        Cuenta montoFinalOrigen = new Cuenta();
        Cuenta montoFinalDestino = new Cuenta();

        montoFinalOrigen.setId(cuentaOrigen.getId());
        montoFinalOrigen.setAmount(totalOrigen);
        montoFinalOrigen.setCedulaCliente(cuentaOrigen.getCedulaCliente());
        montoFinalOrigen.setFechaApertura(cuentaOrigen.getFechaApertura());

        montoFinalDestino.setId(cuentaDestino.getId());
        montoFinalDestino.setAmount(totalDestino);
        montoFinalDestino.setCedulaCliente(cuentaDestino.getCedulaCliente());
        montoFinalDestino.setFechaApertura(cuentaDestino.getFechaApertura());

        Transaccion transaccionOrigen = new Transaccion(cuentaOrigen.getId(), "monto recibido", infoTransferencia.getMonto());
        Transaccion transaccionGuardadaOrigen = transaccionRepository.save(transaccionOrigen);

        Transaccion transaccionDestino = new Transaccion(cuentaDestino.getId(), "monto enviado", infoTransferencia.getMonto());
        Transaccion transaccionGuardadaDestino = transaccionRepository.save(transaccionDestino);

        cuentaRepository.save(montoFinalOrigen);
        cuentaRepository.save(montoFinalDestino);

        return infoTransferencia;

    }
}