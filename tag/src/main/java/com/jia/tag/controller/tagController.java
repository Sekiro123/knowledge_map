package com.jia.tag.controller;

import com.jia.tag.entity.tag;
import com.jia.tag.service.tagServiceImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

@Controller
@RequestMapping("/tag")
public class tagController {
    @Autowired
    private tagServiceImpl tagService;
    @RequestMapping("insert")
    @ResponseBody
    public String save(@RequestBody tag tag){
        System.out.println("tag.toString() = " + tag.toString());
        tagService.save(tag);
        return "saved!";
    }
    @RequestMapping("all-insert")
    @ResponseBody
    public String all_insert() throws IOException, JSONException {
        int count=0;
        System.out.println("all insert start!");
//        BufferedReader model = new BufferedReader(new FileReader("C:\\Users\\admin\\Desktop\\all_50_schemas_new"));
        final BufferedReader model = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\admin\\Desktop\\all_50_schemas_new"), "GBK"));
        String str;
        ArrayList<model> models = new ArrayList<>();
        while((str=model.readLine())!=null){
            JSONObject jsonObject = new JSONObject(str);
            models.add(new model(jsonObject.getString("object_type"),jsonObject.getString("predicate"),jsonObject.getString("subject_type")));
        }
//        BufferedReader file2 = new BufferedReader(new FileReader("C:\\Users\\admin\\Desktop\\pred_out"));
        final BufferedReader file2 = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\\\Users\\\\admin\\\\Desktop\\\\pred_out"), "GBK"));
        System.out.println("models.toString() = " + models.toString());
        String tag;
        ArrayList<com.jia.tag.entity.tag> tags = new ArrayList<>();
        while((tag=file2.readLine())!=null){
            JSONObject jsonObject = new JSONObject(tag);
            JSONArray spo_list = jsonObject.getJSONArray("spo_list");
            for(int i=0;i<spo_list.length();i++){
                JSONObject temp = spo_list.getJSONObject(i);
                String predicate = temp.getString("predicate");
                String subject_properties = null;
                String object_properties = null;
                for(int j=0;j<models.size();j++){
                    if(models.get(j).predicate.equals(predicate)){
                        subject_properties=models.get(j).subject_type;
                        object_properties=models.get(j).object_type;
                        com.jia.tag.entity.tag tag1 = new tag("jia", temp.getString("subject"), subject_properties,predicate, temp.getString("object"), object_properties);
                        tagService.save(tag1);
                        System.out.println(count++);
                        break;
                    }

                }
            }

        }

        return "all insert!";
    }
}
