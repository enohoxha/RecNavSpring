package com.recnav.app.ResponseModels;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Response {
    public static String ALL_GOOD = "200";
    public static String AUTH_FAIL = "403";
    public static String TOKEN_NOT_VALID = "404";
    public static String USER_DO_NOT_EXIST = "101";

    public static  String ERROR = "error";
    public static  String SUCCESS = "success";

    private static  String TYPE_KEY = "type";
    private static  String CODE_KEY = "code";
    private static  String MESSAGE_KEY = "message";


    private Map<String, String> message;

    private String type = null;
    private String code = null;
    private String messageKey = null;
    private String messageText = null;
    private Gson json ;

    public Response() {
        json = new Gson();
        message = new HashMap<String, String>();
    }

    /**
     * get type
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * set type
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * get code
     * @return
     */
    public String getCode() {
        return code;
    }

    /**
     * set code
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * get message key
     * @return
     */
    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    /**
     * This function build an response message
     */
    public void buildMessage(){

        if(this.getType() == null){
            throw new NullPointerException("Type can not be null");
        }
        if (this.getCode() == null){
            throw new NullPointerException("Code can not be null");
        }
        this.message.put(TYPE_KEY, this.getType());
        this.message.put(CODE_KEY, this.getCode());

        if(this.getMessageKey() != null && this.getMessageText() != null){
            this.message.put(this.getMessageKey(), this.getMessageText());
        }

    }

    /**
     * Return json response from object
     * @return
     */
    /**
     * Return json response from object
     * @return
     */
    public  String responseJSON() {
        return this.json.toJson(this.message);
    }
}
