package ru.liga.crud.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class ResourceBundleServiceTest {
    private final ResourceBundleService resourceBundleService = new ResourceBundleService();

    @Test
    void getMessage() {
        assertThat(resourceBundleService.getMessage("uncheckedField")).isEqualTo("Failed to validate due to invalid position");
    }
}