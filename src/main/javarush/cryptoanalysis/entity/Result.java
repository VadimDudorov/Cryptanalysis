package main.javarush.cryptoanalysis.entity;

import main.javarush.cryptoanalysis.exception.CryptanalysisException;
import main.javarush.cryptoanalysis.repository.ResultCode;

public class Result {
    private ResultCode resultCode;
    private CryptanalysisException exception;

    public Result(ResultCode resultCode){
        this.resultCode = resultCode;
    }

    public Result(ResultCode resultCode, CryptanalysisException exception){
        this.resultCode = resultCode;
        this.exception = exception;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public CryptanalysisException getException() {
        return exception;
    }
}
