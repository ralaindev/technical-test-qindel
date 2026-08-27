package com.qindel.test.infrastructure.input.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnApplicablePriceWhenQueriedAt10OnJune14() throws Exception {
        mockMvc.perform(get("/api/v1/prices")
                        .param("queryDate", "2020-06-14T10:00:00+02:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.startDate").value("2020-06-14T00:00:00+02:00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31T23:59:59+02:00"))
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    void shouldReturnApplicablePriceWhenQueriedAt16OnJune14() throws Exception {
        mockMvc.perform(get("/api/v1/prices")
                        .param("queryDate", "2020-06-14T16:00:00+02:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(2))
                .andExpect(jsonPath("$.startDate").value("2020-06-14T15:00:00+02:00"))
                .andExpect(jsonPath("$.endDate").value("2020-06-14T18:30:00+02:00"))
                .andExpect(jsonPath("$.price").value(25.45))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    void shouldReturnApplicablePriceWhenQueriedAt21OnJune14() throws Exception {
        mockMvc.perform(get("/api/v1/prices")
                        .param("queryDate", "2020-06-14T21:00:00+02:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.startDate").value("2020-06-14T00:00:00+02:00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31T23:59:59+02:00"))
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    void shouldReturnApplicablePriceWhenQueriedAt10OnJune15() throws Exception {
        mockMvc.perform(get("/api/v1/prices")
                        .param("queryDate", "2020-06-15T10:00:00+02:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(3))
                .andExpect(jsonPath("$.startDate").value("2020-06-15T00:00:00+02:00"))
                .andExpect(jsonPath("$.endDate").value("2020-06-15T11:00:00+02:00"))
                .andExpect(jsonPath("$.price").value(30.50))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    void shouldReturnApplicablePriceWhenQueriedAt21OnJune16() throws Exception {
        mockMvc.perform(get("/api/v1/prices")
                        .param("queryDate", "2020-06-16T21:00:00+02:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(4))
                .andExpect(jsonPath("$.startDate").value("2020-06-15T16:00:00+02:00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31T23:59:59+02:00"))
                .andExpect(jsonPath("$.price").value(38.95))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    void shouldReturnBadRequestWhenQueryDateHasInvalidFormat() throws Exception {
        mockMvc.perform(get("/api/v1/prices")
                        .param("queryDate", "invalid-date")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Parámetros de entrada no válidos."));
    }

    @Test
    void shouldReturnBadRequestWhenBrandIdIsMissing() throws Exception {
        mockMvc.perform(get("/api/v1/prices")
                        .param("queryDate", "2020-06-14T10:00:00+02:00")
                        .param("productId", "35455"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Parámetros de entrada no válidos."));
    }

    @Test
    void shouldReturnBadRequestWhenProductIdIsZero() throws Exception {
        mockMvc.perform(get("/api/v1/prices")
                        .param("queryDate", "2020-06-14T10:00:00+02:00")
                        .param("productId", "0")
                        .param("brandId", "1"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Parámetros de entrada no válidos."));
    }

    @Test
    void shouldReturnBadRequestWhenBrandIdIsNegative() throws Exception {
        mockMvc.perform(get("/api/v1/prices")
                        .param("queryDate", "2020-06-14T10:00:00+02:00")
                        .param("productId", "35455")
                        .param("brandId", "-1"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Parámetros de entrada no válidos."));
    }

    @Test
    void shouldReturnNotFoundWhenNoApplicablePriceExists() throws Exception {
        mockMvc.perform(get("/api/v1/prices")
                        .param("queryDate", "2019-06-14T10:00:00+02:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("No se ha encontrado un precio aplicable."));
    }
}
