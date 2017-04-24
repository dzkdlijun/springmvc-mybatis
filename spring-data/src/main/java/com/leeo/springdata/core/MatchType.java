package com.leeo.springdata.core;

/**
 * @author lijun
 * @since 2017/3/21 10:08
 */
public enum  MatchType {
    ;

    private String value;

    MatchType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
