package com.stryjewski.branchgithubapi.exception;

public class GithubApiException extends RuntimeException {
    public GithubApiException(String message) {
        super(message);
    }
}
