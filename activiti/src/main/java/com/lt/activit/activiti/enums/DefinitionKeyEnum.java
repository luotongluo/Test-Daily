package com.lt.activit.activiti.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luotong
 * @description DefinitionKeyEnum
 * @date 2020/4/16 17:42
 */
public enum DefinitionKeyEnum {
    TEST(1, "testKey"),
    ;
    private int code;
    private String name;

    public List<DefinitionKeyEnum> getRetMap() {
        List<DefinitionKeyEnum> definitionKeyEnums = new ArrayList<>();
        DefinitionKeyEnum[] keyEnums = DefinitionKeyEnum.values();
        for (DefinitionKeyEnum keyEnum : keyEnums) {
            definitionKeyEnums.add(keyEnum);
        }
        return definitionKeyEnums;
    }

    DefinitionKeyEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
