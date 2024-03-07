package com.example.rqchallenge.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Response {

    private String status;
    private Object data;
    private String message;

    public Response(String status, Object data) {
        this.status = status;
        this.data = data;
    }

    public Response(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public Response(String message) {
        this.status = "error";
        this.message = message;
    }
}
