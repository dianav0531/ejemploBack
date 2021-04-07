package edu.eam.ingesoft.ejemploback.controllers;


import edu.eam.ingesoft.ejemploback.model.Transaccion;
import edu.eam.ingesoft.ejemploback.services.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @GetMapping("accounts/{id}/transactions")
    public List<Transaccion> listarTransacciones(@PathVariable String id){ return transaccionService.listarTransacciones(id);
    }

}
