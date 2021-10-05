package com.flaviocr.MuxiAPI.controller;

import com.flaviocr.MuxiAPI.model.TerminalModel;
import com.flaviocr.MuxiAPI.service.TerminalService;
import com.sun.istack.NotNull;
import io.swagger.annotations.Api;
import org.hibernate.resource.jdbc.LogicalConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/api/v1")
@Api(value = "TERMINAL API")
public class TerminalController {

    @Autowired
    private TerminalService terminalService;

    @GetMapping(value = {"/terminal"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<TerminalModel> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        return terminalService.findAll(page, size);
    }

    @GetMapping(value = {"/terminal/{logic}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public TerminalModel findAllByLogic(@PathVariable int logic) {
        return terminalService.findAllByLogic(logic);
    }

    @PostMapping(value = {"/terminal"}, consumes = {"text/html; charset=utf-8"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody @NotNull @Valid String body) {
        return terminalService.save(body);
    }

    @PutMapping(value = {"/terminal/{logic}"}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public String update(TerminalModel terminalModel, int logic) {
        return terminalService.update(terminalModel, logic);
    }



}
