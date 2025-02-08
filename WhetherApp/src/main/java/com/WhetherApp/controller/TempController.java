package com.WhetherApp.controller;

import com.WhetherApp.res.ApiRes;
import com.WhetherApp.service.TempService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temp")
public class TempController {

    @Autowired
    private TempService tempService;

    @GetMapping("/{stateCode}/{cityName}")
    public ResponseEntity<ApiRes> findTemperature(@PathVariable String stateCode, @PathVariable String cityName) throws JsonProcessingException {
        ApiRes res = tempService.getTempacture(stateCode, cityName);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
