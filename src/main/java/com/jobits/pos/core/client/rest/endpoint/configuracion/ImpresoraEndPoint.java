/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.endpoint.configuracion;

import com.jobits.pos.core.module.PosCoreModule;
import com.jobits.pos.servicios.impresion.Impresora;
import com.jobits.pos.servicios.impresion.ImpresoraService;
import java.util.List;
import javax.print.Doc;
import javax.print.PrintException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Home
 */
@RestController
@RequestMapping(path = "pos/printing")
public class ImpresoraEndPoint implements ImpresoraService {

    public static final String ADD_PRINTER_PATH = "/add";

    public static final String UPDATE_PRINTER_PATH = "/update";

    public static final String DELETE_PRINTER_PATH = "/remove/{id}";

    public static final String FIND_BY_PRINTER_PATH = "/{nameVirtualPrinter}";

    public static final String LIST_ALL_PRINTER_PATH = "/list";

    public static final String GET_NOMBRE_IMPRESORAS_PATH = "/get-system-printers";

    public static final String GET_NOMBRE_GRUPOS_PATH = "/get-group-names";

    ImpresoraService service = PosCoreModule.getInstance().getImplementation(ImpresoraService.class);

    @Override
    @PostMapping(ADD_PRINTER_PATH)
    public Impresora agregarImpresora(@RequestBody Impresora impresora) {
        return service.agregarImpresora(impresora);
    }

    @Override
    @PutMapping(UPDATE_PRINTER_PATH)
    public Impresora updateImpresora(@RequestBody Impresora impresora) {
        return service.updateImpresora(impresora);
    }

    @Override
    @DeleteMapping(DELETE_PRINTER_PATH)
    public Impresora deleteImpresora(@PathVariable("id") int idImpresora) {//TODO: cambiar a id
        return service.deleteImpresora(idImpresora);
    }

    @Override
    @GetMapping(FIND_BY_PRINTER_PATH)
    public Impresora findBy(String nombreVirtualImpresora) {
        return service.findBy(nombreVirtualImpresora);
    }

    @Override
    @GetMapping(LIST_ALL_PRINTER_PATH)
    public List<Impresora> findAll() {
        return service.findAll();
    }

    @Override
    public void imprimirEnGrupo(String nombreGrupo, Doc docToPrint) throws PrintException {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @GetMapping(GET_NOMBRE_IMPRESORAS_PATH)
    public List<String> getNombreImpresorasSistema() {
        return service.getNombreImpresorasSistema();
    }

    @Override
    @GetMapping(GET_NOMBRE_GRUPOS_PATH)
    public List<String> getNombreGrupos() {
        return service.getNombreGrupos();
    }

}
