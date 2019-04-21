package com.shenrs.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author shenrs
 * @Description
 * @create 2019-03-30 14:54
 **/
@ConfigurationProperties(prefix = "shenrs.hello")
public class HelloProperties {

    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
