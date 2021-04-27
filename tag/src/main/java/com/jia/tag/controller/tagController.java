package com.jia.tag.controller;

import com.google.gson.Gson;
import com.jia.tag.entity.tag;
import com.jia.tag.service.tagServiceImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
//@RequestMapping("/tag")
public class tagController {
    @Autowired
    private tagServiceImpl tagService;
    @RequestMapping("insert")
    @ResponseBody
    public String save(@RequestParam(value = "result") String tags, HttpServletResponse response) throws JSONException {
        System.out.println("tag/insert controller start!");
        JSONObject jsonObject = new JSONObject(tags);
        for(int i=0;i<jsonObject.length();i++){
            JSONArray jsonArray = jsonObject.getJSONArray(String.valueOf(i + 1));
            tag tag = new tag();
            tag.setAccount(jsonArray.getString(0));
            tag.setSubject(jsonArray.getString(1));
            tag.setSubject_properties(jsonArray.getString(2));
            tag.setObject(jsonArray.getString(3));
            tag.setObject_properties(jsonArray.getString(4));
            tag.setRelation(jsonArray.getString(5));
            tag.setText(jsonArray.getString(6));
            tag.setTime(new Date());
            System.out.println("tag.toString() = " + tag.toString());
            tagService.save(tag);
        }
        response.setHeader("Access-Control-Allow-Origin", "*");
//        tagService.save(tag);
        return "saved!";
    }
    @RequestMapping("all-insert")
    @ResponseBody
    public String all_insert() throws IOException, JSONException {
        int count=0;
        System.out.println("all insert start!");
//        BufferedReader model = new BufferedReader(new FileReader("C:\\Users\\admin\\Desktop\\all_50_schemas_new"));
        final BufferedReader model = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\admin\\Desktop\\all_50_schemas_new.txt"), "UTF-8"));
        String str;
        ArrayList<model> models = new ArrayList<>();
        while((str=model.readLine())!=null){
            JSONObject jsonObject = new JSONObject(str);
            models.add(new model(jsonObject.getString("object_type"),jsonObject.getString("predicate"),jsonObject.getString("subject_type")));
        }
//        BufferedReader file2 = new BufferedReader(new FileReader("C:\\Users\\admin\\Desktop\\pred_out"));
        final BufferedReader file2 = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\\\Users\\\\admin\\\\Desktop\\\\pred_out.txt"), "UTF-8"));
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
//                        com.jia.tag.entity.tag tag1 = new tag("jia", temp.getString("subject"), subject_properties,predicate, temp.getString("object"), object_properties);
//                        tagService.save(tag1);
                        System.out.println(count++);
                        break;
                    }

                }
            }

        }

        return "all insert!";
    }
    @RequestMapping("findAll")
    @ResponseBody
    public List<tag> findAll(){
        return tagService.findAll();
    }
    @RequestMapping("updateNeo4j")
    @ResponseBody
    public String updateNeo4j(){
        return tagService.updateNeo4j();
    }
    public void testPool(){
        
    }
}
