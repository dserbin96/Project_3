package com.example.dns.project_3.usecase;

public interface UseCaseCallback<T> {
    void onSuccess(T t);
    void onError(String s);
}
