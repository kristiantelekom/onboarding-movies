package com.onboarding.movies.proxy;

import com.onboarding.movies.model.dto.ActorsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "onboarding-actors", url = "http://localhost:8089", path = "/actors")
public interface ActorsClient {

    @GetMapping("/{actorId:[0-9]+}")
    public ResponseEntity<ActorsDTO> getActorById(@PathVariable Integer actorId);
}
