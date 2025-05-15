package com.projeto.ads.Enum;

public enum Curso {
    MARKETING("MARKETING"),
    ADMINISTRACAO("ADMINISTRACAO"),
    ADS("ADS"),
    CONTABILIDADE("CONTABILIDADE"),
    ENFERMAGEM("ENFERMAGEM");

    private final String displayName;

    Curso(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
