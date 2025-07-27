package com._1.Inscription.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import com._1.Inscription.dto.CreateClasseResponse;
import com._1.Inscription.response.ApiResponse;

@FeignClient(name = "gestion-scolarite", url = "${services.gestion-scolarite.url}")
public interface ClasseProxy {
    @GetMapping("/api/classes/{id}")
    @ResponseBody
    CreateClasseResponse findClasseById(@PathVariable long id);
} 
