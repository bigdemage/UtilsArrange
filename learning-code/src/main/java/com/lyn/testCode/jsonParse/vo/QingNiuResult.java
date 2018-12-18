package com.lyn.testCode.jsonParse.vo;

//最外层
public class QingNiuResult<T> {

    private QingNiuJsonState state;

    private QingNiuJsonResult<T> result;

    public QingNiuJsonResult<T> getResult() {
        return result;
    }

    public void setResult(QingNiuJsonResult<T> result) {
        this.result = result;
    }

    public QingNiuJsonState getState() {
        return state;
    }

    public void setState(QingNiuJsonState state) {
        this.state = state;
    }


}
