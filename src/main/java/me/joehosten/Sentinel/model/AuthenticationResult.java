package me.joehosten.Sentinel.model;

import org.jetbrains.annotations.NotNull;

public enum AuthenticationResult {
    INVALID("Invalid license key."),
    EXPIRED("License key has expired."),
    BLACKLISTED("License key has been blacklisted."),
    EXCESSIVE_SERVERS("License key has been used by too many servers."),
    EXCESSIVE_IPS("License key has been used by too many IPs."),
    INVALID_PRODUCT("License key is linked to a different product."),
    UNKNOWN_DOWNLOAD_ID("Unknown download ID."),
    UNKNOWN_CONNECTION("Unknown connection."),
    UNEXPECTED("An unexpected error occurred."),
    SUCCESS("Successfully authenticated.");

    private final String message;

    private AuthenticationResult(String message) {
        this.message = message;
    }

    public @NotNull String message() {
        return this.message;
    }
}