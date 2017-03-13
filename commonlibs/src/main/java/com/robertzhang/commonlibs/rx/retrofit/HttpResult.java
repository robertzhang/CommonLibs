package com.robertzhang.commonlibs.rx.retrofit;

public class HttpResult<T> {

    public boolean error;
    //@SerializedName(value = "results", alternate = {"result"})
    public T results;
}
