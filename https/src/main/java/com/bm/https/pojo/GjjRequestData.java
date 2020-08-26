package com.bm.https.pojo;

import javax.validation.constraints.NotBlank;

/**公积金请求实体实体
 * Created by 86133 on 2020/6/10.
 */

public class GjjRequestData {


    private String pubkeyId;
    @NotBlank(message = "ciphertext不能为空")
    private String ciphertext;
    @NotBlank(message = "sign_s不能为空")
    private String sign_s;
    @NotBlank(message = "sign_r不能为空")
    private String sign_r;
    @NotBlank(message = "skey不能为空")
    private String skey;
    private String stateCode;
    private String message;

    public String getPubkeyId() {
        return pubkeyId;
    }

    public void setPubkeyId(String pubkeyId) {
        this.pubkeyId = pubkeyId;
    }

    public String getCiphertext() {
        return ciphertext;
    }

    public void setCiphertext(String ciphertext) {
        this.ciphertext = ciphertext;
    }

    public String getSign_s() {
        return sign_s;
    }

    public void setSign_s(String sign_s) {
        this.sign_s = sign_s;
    }

    public String getSign_r() {
        return sign_r;
    }

    public void setSign_r(String sign_r) {
        this.sign_r = sign_r;
    }

    public String getSkey() {
        return skey;
    }

    public void setSkey(String skey) {
        this.skey = skey;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
