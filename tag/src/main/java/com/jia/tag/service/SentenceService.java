package com.jia.tag.service;

import com.jia.tag.dao.SentenceDao;
import com.jia.tag.entity.Sentence;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SentenceService {
    @Autowired
    private SentenceDao sentenceDao;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public String findOneSentence(String field,String account){
        Sentence sentence = sentenceDao.findOneSentence(field);
        sentenceDao.incTagTimes(sentence.getId());
        rabbitTemplate.convertAndSend("amq.direct","incNumSentences",account);
        return sentence.getContent();
    }
    public void insertSentencesFromArticle(String article,String field){
        char[] endSymbol={'；','。','！','？'};
        char[] separateSymbol={'，','、','：'};
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < endSymbol.length; i++) {
            buffer.append(endSymbol[i]);
            buffer.append('|');
        }
        buffer.deleteCharAt(buffer.length()-1);

        System.out.println("buffer.toString() = " + buffer.toString());
        article = article.replace("\n", " ");
        String[] wholeSentence = article.split(buffer.toString());
        for (String s : wholeSentence) {
            System.out.println("s = " + s);
        }
        buffer.delete(0,buffer.length());
        for (int i = 0; i < separateSymbol.length; i++) {
            buffer.append(separateSymbol[i]);
            buffer.append('|');
        }
        buffer.deleteCharAt(buffer.length()-1);
        System.out.println("buffer.toString() = " + buffer.toString());
        int max_lines=3;
        int max_chars=50;
        StringBuffer qualified_sentence = new StringBuffer();
        StringBuffer qualified_wholeSentence = new StringBuffer();
        boolean flag=true;

        for (int i = 0; i < wholeSentence.length; i++) {
//         System.out.println("wholeSentence[i] = " + wholeSentence[i]);
            flag=true;
            qualified_wholeSentence.delete(0,qualified_wholeSentence.length());
            String[] sentences = wholeSentence[i].split(buffer.toString());
//         for (String sentence : sentences) {
//            System.out.println("sentence = " + sentence);
//         }
            int current_line=1;
            int current_char=0;
            for (int j = 0; j < sentences.length; j++) {
                if(sentences[j].length()>50){
                    flag=false;
                    break;
                }
                else{
                    if(current_char+sentences[j].length()>max_chars){
                        qualified_wholeSentence.append(qualified_sentence.toString()+"|");
                        qualified_sentence.delete(0,qualified_sentence.length());
                        current_line+=1;
                        if(current_line>max_lines){
                            flag=false;
                            break;
                        }
                        current_char=sentences[j].length();
                        qualified_sentence.append(sentences[j]);
                    }
                    else{
                        current_char+=sentences[j].length()+1;
                        qualified_sentence.append(sentences[j]+",");
                    }
                }
            }

            if(flag){
                qualified_wholeSentence.append(qualified_sentence.toString());
                qualified_sentence.delete(0,qualified_sentence.length());
                System.out.println("qualified_wholeSentence.toString() = " + qualified_wholeSentence.toString());
                sentenceDao.save(new Sentence(0,field,"0","0","0",qualified_wholeSentence.toString(),0));

            }
        }
    }
}
