package com.robertzhang.commonlibs.rx.retrofit.func;

import com.robertzhang.commonlibs.rx.retrofit.HttpResult;
import com.robertzhang.commonlibs.utils.json.JsonConvert;
import rx.functions.Func1;

/**
 * Created by _SOLID
 * Date:2016/7/28
 * Time:11:04
 */
public class ResultFunc<T> implements Func1<String, HttpResult<T>> {
    @Override
    public HttpResult<T> call(String result) {
        JsonConvert<HttpResult<T>> convert = new JsonConvert<HttpResult<T>>() {
        };
        return convert.parseData(result);
    }
}
