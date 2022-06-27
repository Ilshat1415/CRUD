package ru.liga.crud.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class ResourceBundleServiceTest {
    private final ResourceBundleService resourceBundleService = new ResourceBundleService();

    @Test
    void getMessage_ValidKey_NotEquals() {
        assertThat(resourceBundleService.getMessage("uncheckedField"))
                .isNotEqualTo(ResourceBundleService.KEY_IS_NULL);
    }

    @Test
    void getMessage_EmptyKey_Equals() {
        assertThat(resourceBundleService.getMessage(null))
                .isEqualTo(ResourceBundleService.KEY_IS_NULL);
    }
}