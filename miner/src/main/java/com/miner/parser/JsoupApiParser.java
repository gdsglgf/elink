package com.miner.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.miner.util.Runner;

public class JsoupApiParser implements IParser {

	@Override
	public void parse(String html, int index) {
		// TODO Auto-generated method stub
	//	System.out.printf("-----JsoupApiParser--------");
		System.out.printf("-------------file:%d\n", index);
		StringBuffer save=new StringBuffer();
		if(html.length()>0){
			try {
				Document doc=Jsoup.parse(html);
				if(save.length() < save.capacity()){
					save.append("docno:"+doc.getElementsByTag("docno").text()+"\n");
					
					save.append("url:"+doc.getElementsByTag("url").text()+"\n");
					if(doc.getElementsByTag("title")!=null)
					    save.append("title:"+doc.getElementsByTag("title").text()+"\n");
					if(doc.getElementsByAttributeValue("name", "keywords")!=null)
					    save.append("keywords:"+doc.getElementsByAttributeValue("name", "keywords").attr("content")+"\n");
					if(doc.getElementsByAttributeValue("name", "description")!=null)
					    save.append("description:"+doc.getElementsByAttributeValue("name", "description").attr("content")+"\n");
					}else{
						save.ensureCapacity(2*save.capacity());
						save.append("docno:"+doc.getElementsByTag("docno").text()+"\n");
						
						save.append("url:"+doc.getElementsByTag("url").text()+"\n");
						
						if(doc.getElementsByTag("title")!=null)
						    save.append("title:"+doc.getElementsByTag("title").text()+"\n");
						if(doc.getElementsByAttributeValue("name", "keywords")!=null)
						    save.append("keywords:"+doc.getElementsByAttributeValue("name", "keywords").attr("content")+"\n");
						if(doc.getElementsByAttributeValue("name", "description")!=null)
						    save.append("description:"+doc.getElementsByAttributeValue("name", "description").attr("content")+"\n");

					}
				}catch(IllegalArgumentException e1){
					System.out.println("IllegalArgumentException has happended in");
					e1.printStackTrace();
					//System.out.println(sb.toString());
				}finally{
				    
				     System.out.println(save.toString());
				   //  continue;
				}
			}
		}
	public static void main(String[] args) {
		Runner.benchmark(new JsoupApiParser());
		//String html="<doc><docno>0015e77674b7604f-07dd0ee2a1c1ef92-469b9cc3ce78f64d392776be46976e8f</docno><url>http://www.test.com/</url><!DOCTYPE html><html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en\" xml:lang=\"en\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /> <title>tales from the thousand and one nights</title><meta name=\"keywords\" content=\"一千零一夜 天方夜谭 阿拉伯之夜\" /><meta name=\"description\" content=\"Once upon a time..\" /> ";
		//int index = 1 ;
		//new JsoupApiParser().parse(html, index);
		}

}
