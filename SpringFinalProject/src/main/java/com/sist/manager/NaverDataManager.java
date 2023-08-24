package com.sist.manager;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

@Component
public class NaverDataManager {

	public List<String> jsonParser(String json){
		// 들어오는 형식 => {......, title:'', items:[] } => {}로 묶여있기 때문에 JSONObject로 받아야 한다. 
		//												  []로 묶여있을 경우, JSONArray로 받아야함.
		// items => [{},{},{},.... 100개]
		List<String> list=new ArrayList<String>();
		try {
			JSONParser jp=new JSONParser();
			JSONObject root=(JSONObject)jp.parse(json);
			JSONArray arr=(JSONArray)root.get("items");
			for(int i=0;i<arr.size();i++) {
				JSONObject obj=(JSONObject)arr.get(i);
				// 블로그 내의 포스팅 내용을 desc로 받아오기
				String desc=(String)obj.get("description");
				list.add(desc);
			}
		} catch (Exception e) {}
		return list;
	}
	
	
    public String foodRecommand(String ss) {
        String clientId = "bvhjeEtgJPEqVvlw4wlb"; //애플리케이션 클라이언트 아이디
        String clientSecret = "BVt_8s7qbw"; //애플리케이션 클라이언트 시크릿


        String text = null;
        try {
            text = URLEncoder.encode(ss+ " 서울 맛집 추천", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }

        														// display -> 검색 결과 (디폴트 10개)
        String apiURL = "https://openapi.naver.com/v1/search/blog?display=100&query=" + text;    // JSON 결과
        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // XML 결과


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);


        //System.out.println(responseBody);
        return responseBody;
    }


    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 오류 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }
}
