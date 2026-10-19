package tn.esprit.springhome.services;

import java.math.BigDecimal;

final class ServiceValidation {
    private ServiceValidation() { }

    static void required(String value, String message) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(message);
    }

    static void nonNegative(BigDecimal value, String label) {
        if (value == null || value.signum() < 0) throw new IllegalArgumentException(label + " ne peut pas être négatif ou nul (absent)");
    }
}
