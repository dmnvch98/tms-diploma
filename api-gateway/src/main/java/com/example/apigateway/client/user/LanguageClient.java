package com.example.apigateway.client.user;

import com.example.apigateway.model.Language;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "user-service-languages",
    url = "${services.user.url}/api/v1/languages")
public interface LanguageClient {
    @GetMapping
    List<Language> getAllLanguages();

    @GetMapping("/{languageId}")
    Language findAllByLanguageId(@PathVariable("languageId") Long languageId);
}
