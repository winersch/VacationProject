package com.example.vacationproject;

import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class XMLParsing extends AsyncTask<Object, Object, ArrayList<NewsData>> {
    private String url = "http://rss.segye.com/segye_recent.xml";

    protected void onPreExecute(){
        super.onPreExecute();
    }

    protected ArrayList<NewsData> doInBackground(Object... params){
        ArrayList<NewsData> list = new ArrayList<NewsData>();
        try {
            URL url = new URL(this.url);

            // RSS의 값들을 XmlParser 를 통하여 가져온다.
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            BufferedInputStream bis = new BufferedInputStream(url.openStream());
            parser.setInput(bis,null);

            String tag="";

            String title = null, link = null, description = null, time = null;

            boolean isItemTag = false;

            // getEventType()는
            // 문서 시작/끝, 태그 시작/끝, 태그의 내용을 표시하는 값을 표시한다.
            int parserEvent = parser.getEventType();

            while (parserEvent != XmlPullParser.END_DOCUMENT) { //문서가 끝날때까지 동작해라.
                switch (parserEvent) {
                    case XmlPullParser.START_TAG: // TAG 시작. 예시) <title> 태그
                        tag = parser.getName();
                        if(tag.equals("item")){ // news 들만 가져오기 위한 boolean 확인
                            isItemTag = true;
                        }
                        break;
                    case XmlPullParser.TEXT: // TAG 안의 문자열. 예시) <title> 과 </title> 사이의 문자열
                        if(isItemTag) {
                            if (tag.equals("title")) { // title TAG 확인
                                title = parser.getText();
                            }
                            if (tag.equals("link")) { // link TAG 확인
                                link = parser.getText();
                            }
                            if (tag.equals("description")) { // description TAG 확인
                                description = parser.getText();
                            }
                            if (tag.equals("pubDate")) { // dc:date TAG 확인
                                time = parser.getText();
                            }
                        }
                        break;

                    case XmlPullParser.END_TAG: // TAG 종료. 예시) </title> 태그
                        tag = parser.getName();
                        if(tag.equals("item")){ // news 태그 종료확인
                            list.add(new NewsData(title, link, description, time)); // 한개의 news 를 저장
                            title ="";
                            link ="";
                            description ="";
                            time ="";
                            isItemTag = false;
                        }
                        tag = ""; // tag 값 초기화 하자
                        break;
                }
                // 다음 TAG 로 이동
                // 예시) </title>의 다음 Tag 인 <link>로 이동.
                parserEvent = parser.next();
            }
        } catch (Exception e) {
            Log.d("rss_ Exception==", ""+e);
        }
        return list;


    }
    @Override
    protected void onPostExecute(ArrayList<NewsData> parsingDatas) {
        super.onPostExecute(parsingDatas);
    }

    // AsyncTask 실행하여 RSS 의 내용을 읽어오는 함수
    public ArrayList<NewsData> getData() {
        ArrayList<NewsData> list = new ArrayList<NewsData>();
        try {
            list = execute().get();
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {}
        return list;
    }

}
