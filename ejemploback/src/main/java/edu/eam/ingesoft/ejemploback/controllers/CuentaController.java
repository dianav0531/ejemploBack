package edu.eam.ingesoft.ejemploback.controllers;

import edu.eam.ingesoft.ejemploback.model.Cuenta;
import edu.eam.ingesoft.ejemploback.model.InfoTransferencia;
import edu.eam.ingesoft.ejemploback.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @PostMapping("/accounts")
    public Cuenta crearCuenta(@RequestBody Cuenta cuenta){

        return cuentaService.crearCuenta(cuenta);
    }

    @GetMapping("/customers/{cedula}/accounts")
    public List<Cuenta> listarCuentasCliente(@PathVariable String cedula) {
        return cuentaService.listarCuentasCliente(cedula);
    }

    @PutMapping("/accounts/consign")
    public String consignarDinero(@RequestBody Cuenta cuenta) {
        return cuentaService.consignarDinero(cuenta);
    }

    @PutMapping("/accounts/withdraw")
    public String retirarDinero(@RequestBody Cuenta cuenta) {
        return cuentaService.retirarDinero(cuenta);
    }

    @DeleteMapping("empty/accounts/{id}")
    public void cancelarCuenta(@PathVariable String id) {cuentaService.cancelarCuenta(id);}

    @PutMapping("/transactions")
    public InfoTransferencia transferencia(@RequestBody InfoTransferencia infoTransferencia) {
        return cuentaService.transferirDinero(infoTransferencia);
    }

}
