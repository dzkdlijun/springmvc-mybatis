package com.leeo.demo;

import com.google.gson.Gson;

/**
 * @author lijun
 * @since 2017/3/9 10:29
 */
public class GsonTest {

    public static void main(String[] args) {
        String jsonString = "{\n" +
                "    \"data\": {\n" +
                "        \"records\": [\n" +
                "            {\n" +
                "                \"name\": \"杨丽琴\",\n" +
                "                \"mobile\": \"18329028353\",\n" +
                "                \"startDate\": \"2016-07-21\",\n" +
                "                \"endDate\": \"2016-07-21\",\n" +
                "                \"valid\": false,\n" +
                "                \"orderItemId\": \"50028077057\",\n" +
                "                \"orderId\": \"50028077056\",\n" +
                "                \"type\": \"TOURISM\",\n" +
                "                \"productId\": \"7351\",\n" +
                "                \"customerId\": \"178456\",\n" +
                "                \"status\": \"FINISHED\",\n" +
                "                \"code\": \"5116225684069141\",\n" +
                "                \"idNo\": \"330106199310253021\",\n" +
                "                \"quantity\": \"1\",\n" +
                "                \"idType\": \"ID\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"杨丽琴\",\n" +
                "                \"mobile\": \"18329028353\",\n" +
                "                \"startDate\": \"2016-07-21\",\n" +
                "                \"endDate\": \"2016-07-21\",\n" +
                "                \"valid\": false,\n" +
                "                \"orderItemId\": \"50028077057\",\n" +
                "                \"orderId\": \"50028077056\",\n" +
                "                \"type\": \"TOURISM\",\n" +
                "                \"productId\": \"7351\",\n" +
                "                \"customerId\": \"178456\",\n" +
                "                \"status\": \"FINISHED\",\n" +
                "                \"code\": \"5116225684069141\",\n" +
                "                \"idNo\": \"330106199310253021\",\n" +
                "                \"quantity\": \"1\",\n" +
                "                \"idType\": \"ID\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";

        Gson gson = new Gson();
        BaseData<BatchAddRequest> data = BaseData.fromJson(jsonString,BatchAddRequest.class);
        System.out.println(data.getData().getRecords());
    }
}
