package com.kiki.quartz1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestDemo {
    public static void main(String[] args) {
        ArrayList<Map<String,Object>> list = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","sqq");
        map.put("age",20);
        list.add(map);
        list.stream().forEach(stringObjectMap -> {});

    }
}
