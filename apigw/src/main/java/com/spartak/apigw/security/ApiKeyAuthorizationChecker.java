package com.spartak.apigw.security;

public interface ApiKeyAuthorizationChecker {
    boolean isAuthorized(String key, String application);
}