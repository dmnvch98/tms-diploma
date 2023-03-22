package com.example.convservice.client;

import com.example.convservice.dto.LanguageLevel;
import com.example.convservice.dto.LanguageLevelDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service-language-levels",
    url = "${services.user.url}/api/v1/language-levels")
public interface LanguageLevelClient {
    @GetMapping("/languageId/{languageId}/levelId/{levelId}")
    LanguageLevelDto findLanguageLevelByLanguageIdAndLevelId(@PathVariable("languageId") Long languageId,
                                                             @PathVariable("levelId") Long levelId);
}
