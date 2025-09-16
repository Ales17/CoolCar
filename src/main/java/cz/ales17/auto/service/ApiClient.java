package cz.ales17.auto.service;

import cz.ales17.auto.dto.ApiResponseData;
import cz.ales17.auto.dto.ApiResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
@Service
public class ApiClient {
    private final RestClient restClient;

    public ApiResponseData fetchFromRegistry(String vin) throws Exception {
        ApiResponseWrapper res = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v2")
                        .queryParam("vin", vin)
                        .build()
                )
                .retrieve()
                .body(ApiResponseWrapper.class);
        if (res == null || res.getData() == null) {
            throw new Exception("No data found for VIN: " + vin);
        }
        return res.getData();
    }
}
