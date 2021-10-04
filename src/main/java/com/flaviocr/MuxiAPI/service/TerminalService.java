package com.flaviocr.MuxiAPI.service;

import com.flaviocr.MuxiAPI.model.TerminalModel;
import com.flaviocr.MuxiAPI.repository.TerminalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TerminalService {

    @Autowired
    static
    TerminalRepository terminalRepository;

    public List<TerminalModel> findAll(int page, int size) {
        return terminalRepository.findAll(PageRequest.of(page, size)).getContent();
    }
}