package com.lt.utils.utils.tools;

import org.json.JSONObject;
import org.json.XML;

public class XmlJsonMain {
    public XmlJsonMain() {
    }

    public static String json2xml(String jsonStr) {
        JSONObject jsonObj = new JSONObject(jsonStr);
        return XML.toString(jsonObj);
    }

    public static String xml2json(String xml) {
        JSONObject xmlJSONObj = XML.toJSONObject(xml.replace("<xml>", "").replace("</xml>", ""));
        return xmlJSONObj.toString();
    }
}
