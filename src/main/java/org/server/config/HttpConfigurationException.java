package org.server.config;

// Custom exception class for handling HTTP configuration errors
public class HttpConfigurationException extends RuntimeException {
    // Default constructor
    public HttpConfigurationException() {
    }

    // Constructor that accepts an error message
    public HttpConfigurationException(String message) {
        super(message);
    }

    // Constructor that accepts an error message and a cause
    public HttpConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor that accepts a cause
    public HttpConfigurationException(Throwable cause) {
        super(cause);
    }
}
