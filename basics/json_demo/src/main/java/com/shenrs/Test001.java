package com.shenrs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * fastjson:json字符串转json
 */
public class Test001 {
    public static String jsonStr = "{\"id\":\"20\",\"name\":\"张三\",\"items\":[{\"itemId\":\"20\",\"itemName\":\"哈哈end-1\"},{\"itemId\":\"21\",\"itemName\":\"哈哈end\"}]}";

    public static void main(String[] args) {
        // 1.先转换成jsonobject对象
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");
        System.out.println("id: " + id + ", name: " + name);

        JSONArray items = jsonObject.getJSONArray("items");
        for (int i = 0; i < items.size(); i++ ) {
            JSONObject itemJson = items.getJSONObject(i);
            Integer itemId = itemJson.getInteger("itemId");
            String itemName = itemJson.getString("itemName");
            System.out.println("itemId: " + itemId + ", itemName: " + itemName);
        }

        // json 转对象
        User user = JSON.parseObject(jsonStr, User.class);
        System.out.println(user);
    }
}

/**
 * fastjson:对象转json字符串
 */
class Test0011{
    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "22");
        jsonObject.put("name", "张洒");

        JSONArray jsonArray = new JSONArray();
        JSONObject json1 = new JSONObject();
        json1.put("itemId", "19");
        json1.put("itemName", "张珊");
        JSONObject json2 = new JSONObject();
        json2.put("itemId", "23");
        json2.put("itemName", "李四");
        jsonArray.add(json1);
        jsonArray.add(json2);
        jsonObject.put("items", jsonArray);
        String jsonStr = JSON.toJSONString(jsonObject);
        System.out.println("jsonStr: " +jsonStr);

        // 对象转json字符串
        User user = new User();
        user.setId("18");
        user.setName("赵丽");
        List<Item> items = new ArrayList<Item>();
        Item itme1 = new Item();
        itme1.setItemId("20");
        itme1.setItemName("李婉");
        Item itme2 = new Item();
        itme2.setItemId("22");
        itme2.setItemName("张举");
        items.add(itme1);
        items.add(itme2);
        user.setItems(items);
        String userJson = JSON.toJSONString(user);
        System.out.println("userJson: " + userJson);
    }
}