package com.flaviocr.MuxiAPI.service;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flaviocr.MuxiAPI.Util.Constants;
import com.flaviocr.MuxiAPI.enumerado.JsonEnum;
import com.flaviocr.MuxiAPI.model.TerminalModel;
import com.flaviocr.MuxiAPI.repository.TerminalRepository;
import com.flaviocr.MuxiAPI.response.ErrorResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

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

        try {
            JSONObject jsonObject = parse(payload);
            JSONObject schema = new JSONObject(JsonEnum.SCHEMA);
            if (validateSchema(jsonObject, schema)) {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
                TerminalModel terminalModel = objectMapper.readValue(jsonObject.toString(), TerminalModel.class);
                if (terminalRepository.findByLogic(terminalModel.getLogic()) == null) {
                    terminalRepository.save(terminalModel);
                    return jsonObject.toString();
                } else {
                    return new ErrorResponse(Constants.ENTITY_DUPLICATED).toString();
                }
            } else {
                return new ErrorResponse(Constants.INVALID_JSON_SCHEMA).toString();
            }
        } catch (Exception e) {
            return new ErrorResponse(Constants.INVALID_PAYLOAD, e.getMessage()).toString();
        }
    }

    private boolean validateSchema(JSONObject jsonObject, JSONObject schema) {
        return false;
    }

    private JSONObject parse(String payload) {
        return null;
    }

    public String update(TerminalModel terminalModel, int logic) {

        try {
            return terminalRepository.update(terminalModel, logic);
        } catch (Exception e) {

            return null;
        }
    }
}
