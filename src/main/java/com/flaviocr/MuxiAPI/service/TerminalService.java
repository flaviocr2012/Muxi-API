package com.flaviocr.MuxiAPI.service;

import com.flaviocr.MuxiAPI.model.TerminalModel;
import com.flaviocr.MuxiAPI.repository.TerminalRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TerminalService {

    @Autowired
    static
    private TerminalRepository terminalRepository;

    public List<TerminalModel> findAll(int page, int size) {
        return terminalRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    public TerminalModel findAllByLogic(int logic) {
        return terminalRepository.findByLogic(logic);
    }

    public String save(String payload) {
        return payload;
    }

    public String update(TerminalModel terminalModel, int logic) {

        try {
            return terminalRepository.update(terminalModel, logic);
        } catch (Exception e) {

            return null;
        }
    }
}
