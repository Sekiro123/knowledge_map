package com.jia.tag.controller;

import com.google.gson.Gson;
import com.jia.tag.entity.TagTable;
import com.jia.tag.entity.tag;
import com.jia.tag.service.ArticleService;
import com.jia.tag.service.SentenceService;
import com.jia.tag.service.tagServiceImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
//@RequestMapping("/tag")
@CrossOrigin
public class tagController {

    @Autowired
    private tagServiceImpl tagService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private SentenceService sentenceService;
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
        final BufferedReader model = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/jiaxiwang/Downloads/all_50_schemas_new.txt"), "UTF-8"));
        String str;
        ArrayList<model> models = new ArrayList<>();
        while((str=model.readLine())!=null){
            JSONObject jsonObject = new JSONObject(str);
            models.add(new model(jsonObject.getString("object_type"),jsonObject.getString("predicate"),jsonObject.getString("subject_type")));
        }
//        BufferedReader file2 = new BufferedReader(new FileReader("C:\\Users\\admin\\Desktop\\pred_out"));
        final BufferedReader file2 = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/jiaxiwang/Downloads/pred_out.txt"), "UTF-8"));
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
//                        com.jia.tag.entity.tag tag1 = new tag("ming", temp.getString("subject"), subject_properties,predicate, temp.getString("object"), object_properties," ",new Date());
//                        tagService.save(tag1);
//                        System.out.println(count++);
                        break;
                    }

                }
            }

        }

        return tagService.updateNeo4j();
    }
    @RequestMapping("findAll")
    @ResponseBody
    public List<tag> findAll(){
        return tagService.findAll();
    }

    @RequestMapping("backFindByPage")
    @ResponseBody
    public TagTable backFindByPage(@RequestParam("page")int page,@RequestParam("limit") int limit,@RequestParam(defaultValue = "null",value="searchParams",required = false) String searchParams) throws JSONException {


        String account="";
        String field="";
        System.out.println("searchParams = " + searchParams);
        if(!searchParams.equals("null")){
            JSONObject jsonObject = new JSONObject(searchParams);
            account=jsonObject.getString("account");
            field=jsonObject.getString("field");
            System.out.println(account+" "+field);
        }
        int code=0;
        String msg="";
        List<tag> all = tagService.findByPage(page,limit,account,field);
        int count= tagService.count(account,field);
        TagTable tagTable = new TagTable(code, msg, count, all);
        return tagTable;
    }

    @RequestMapping("updateNeo4j")
    @ResponseBody
    public String updateNeo4j(){
        return tagService.updateNeo4j();
    }
    public void testPool(){
        
    }
    @RequestMapping("findOneArticle")
    @ResponseBody
    public String findOneArticle(String field,String account){
        return articleService.findOneArticle(field,account);
    }
    @RequestMapping("findOneSentence")
    @ResponseBody
    public String findOneSentence(String field,String account){
        return sentenceService.findOneSentence(field,account);
    }
    @RequestMapping("InsertSentencesFromArticle")
    @ResponseBody
    public String InsertSentencesFromArticle(@RequestParam("article") String article,@RequestParam("field") String field){
        sentenceService.insertSentencesFromArticle(article,field);
        return "success";
    }

    @RequestMapping("qualified")
    @ResponseBody
    public String qualified(@RequestParam("data") String tag){
        Gson gson = new Gson();
        com.jia.tag.entity.tag tag1 = gson.fromJson(tag, tag.class);
        System.out.println("tag1.toString() = " + tag1.toString());
        tagService.qualified(tag1);
        return "success";
    }
    @RequestMapping("unqualified")
    @ResponseBody
    public String unqualified(@RequestParam("data") String tag){
        Gson gson = new Gson();
        com.jia.tag.entity.tag tag1 = gson.fromJson(tag, tag.class);
        System.out.println("tag1.toString() = " + tag1.toString());
        tagService.unqualified(tag1);
        return "success";
    }
    @RequestMapping("tagCount")
    @ResponseBody
    public String tagCount(){
        return String.valueOf(tagService.count("",""));
    }
    @RequestMapping("qualifiedTagCount")
    @ResponseBody
    public String qualifiedTagCount(){
        return String.valueOf(tagService.quailifiedTagCount("",""));
    }
    @RequestMapping("unQualifiedTagCount")
    @ResponseBody
    public String unQualifiedTagCount(){
        return String.valueOf(tagService.unQualifiedTagCount("",""));
    }
}
