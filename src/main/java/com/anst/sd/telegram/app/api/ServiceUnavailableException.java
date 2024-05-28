package com.anst.sd.telegram.app.api;

public class ServiceUnavailableException extends RuntimeException {
    public ServiceUnavailableException(Exception e) {
        super(e);
    }
}
