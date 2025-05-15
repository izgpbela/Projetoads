package com.projeto.ads.Enum;

public enum Status {
    ATIVO ("ativo"),
    INATIVO("inativo"),
    TRANCADO ("trancando"),
    CANCELADO ("cancelado");

    private String status;

    private Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
