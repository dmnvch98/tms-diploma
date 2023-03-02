package com.example.convservice.client;

import com.example.convservice.dto.LanguageLevelDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service-language-levels",
    url = "${services.user.url}/api/v1/language-levels")
public interface LanguageLevelClient {

    @GetMapping("/")
    Long findLanguageLevelId(@RequestBody LanguageLevelDto languageLevelDto);

    @GetMapping("/{languageLevelId}")
    LanguageLevelDto findLanguageLevelById(@PathVariable("languageLevelId") Long languageLevelId);
}
