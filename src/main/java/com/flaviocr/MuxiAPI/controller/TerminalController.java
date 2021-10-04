package com.flaviocr.MuxiAPI.controller;

import com.flaviocr.MuxiAPI.model.TerminalModel;
import com.flaviocr.MuxiAPI.service.TerminalService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/api/v1")
@Api(value = "TERMINAL API")
public class TerminalController {

    @Autowired
    TerminalService terminalService;

    @GetMapping(value = {"/terminal"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<TerminalModel> getAll(int page, int size) {
        return TerminalService.findAll(page, size);
    }

}
