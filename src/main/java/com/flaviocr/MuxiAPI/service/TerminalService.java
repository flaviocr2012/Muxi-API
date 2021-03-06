package com.flaviocr.MuxiAPI.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flaviocr.MuxiAPI.Util.Constants;
import com.flaviocr.MuxiAPI.enumerado.JsonEnum;
import com.flaviocr.MuxiAPI.model.TerminalModel;
import com.flaviocr.MuxiAPI.repository.TerminalRepository;
import com.flaviocr.MuxiAPI.response.ErrorResponse;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
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

    private boolean validateSchema(JSONObject jsonSchema, JSONObject obj) {

        try {
            SchemaLoader loader = SchemaLoader.builder()
                    .schemaJson(jsonSchema)
                    .draftV6Support()
                    .build();
            Schema schema = loader.load().build();
            schema.validate(obj);
            return true;
        } catch (ValidationException e) {
            return false;
        }
    }

    private JSONObject parse(String payload) {

        String splitedPayLoad[] = payload.split(Constants.PAYLOAD_DIVIDER);

        JSONObject objectMap = new JSONObject();

        objectMap.put(Constants.PROPERTY_LOGIC, Integer.valueOf(splitedPayLoad[Constants.INDEX_LOGIC]));
        objectMap.put(Constants.PROPERTY_SERIAL,(splitedPayLoad[Constants.INDEX_SERIAL]));
        objectMap.put(Constants.PROPERTY_LOGIC, Integer.valueOf(splitedPayLoad[Constants.INDEX_LOGIC]));
        objectMap.put(Constants.PROPERTY_LOGIC, Integer.valueOf(splitedPayLoad[Constants.INDEX_LOGIC]));
        objectMap.put(Constants.PROPERTY_LOGIC, Integer.valueOf(splitedPayLoad[Constants.INDEX_LOGIC]));
        objectMap.put(Constants.PROPERTY_LOGIC, Integer.valueOf(splitedPayLoad[Constants.INDEX_LOGIC]));
        objectMap.put(Constants.PROPERTY_LOGIC, Integer.valueOf(splitedPayLoad[Constants.INDEX_LOGIC]));
        objectMap.put(Constants.PROPERTY_LOGIC, Integer.valueOf(splitedPayLoad[Constants.INDEX_LOGIC]));
        objectMap.put(Constants.PROPERTY_LOGIC, Integer.valueOf(splitedPayLoad[Constants.INDEX_LOGIC]));

        return objectMap;
    }

    public String update(TerminalModel terminalModel, int logic) {

        TerminalModel temp = terminalRepository.findByLogic(logic);

        if (temp == null) {
            return new ErrorResponse(Constants.ENTITY_NOT_FOUND).toString();
        }

        if (terminalModel.getLogic() != logic) {
            return new ErrorResponse(Constants.INVALID_ID).toString();
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
            try {
                JSONObject jsonObject = new JSONObject(objectMapper.writeValueAsString(terminalModel));
                JSONObject schema = new JSONObject(JsonEnum.SCHEMA);
                if (validateSchema(schema, jsonObject)) {
                    terminalRepository.save(terminalModel);
                    return jsonObject.toString();
                } else {
                    return new ErrorResponse(Constants.INVALID_JSON_SCHEMA).toString();
                }
            } catch (JsonProcessingException e) {
                return new ErrorResponse(Constants.PARSE_ERROR).toString();
            }
        }
    }
}
