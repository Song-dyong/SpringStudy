package com.sist.main;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/*
	XAXP
	----
	 DOM
	 SAX => Sprimg , MyBatis (실제 데이터만 추출)
	 
	<beans>
		<bean id="sa"
			class="com.sist.main.Sawon"
			p:sabun="1"
			p:name="hong"
			p:dept="dev"
			p:job="대리"
		/>
	</beans>
	
	<?xml version="1.0" encoding="UTF-8"?>
		<beans> 	=> startElement()
			<bean> 	=> startElement()
				=> characters()
			</bean> => endElement()
		</beans>	=> endElement()
	
	
	Spring bean Configuration File 작성시, 체크 => 연결된 url에 태그 정보 => 어시스트 가능
	
	
*/
public class XMLParse extends DefaultHandler {
	private Map map=new HashMap();
	public Map getMap() {
		return map;
	}
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		try {
//			qName은 태그의 이름 => beans는 읽을 필요가 없으므로, bean 태그만 읽기 
			if(qName.equals("bean")) {
				String id=attributes.getValue("id");
				String cls=attributes.getValue("class");
				String sabun=attributes.getValue("p:sabun");
				String name=attributes.getValue("p:name");
				String dept=attributes.getValue("p:dept");
				String job=attributes.getValue("p:job");
				String[] aName= {sabun,name,dept,job};
				String[] ss= {attributes.getQName(2),attributes.getQName(3),attributes.getQName(4),
						attributes.getQName(5)};
				// 메모리 할당
				Class clsName=Class.forName(cls);
				Object obj=clsName.getDeclaredConstructor().newInstance();
				Method[] methods=clsName.getDeclaredMethods();
				for(Method m:methods) {
					String mName=m.getName(); // 메소드 이름
					for(int i=0;i<ss.length;i++) {
						if(mName.equalsIgnoreCase("set"+ss[i].substring(ss[i].indexOf(":")+1))) {
							if(i==0) {
								m.invoke(obj, Integer.parseInt(aName[i]));
							}else {
								m.invoke(obj, aName[i]);
							}
						}
					}
					map.put(id, obj);
					//if(mName.equalsIgnoreCase("set"+))
				}
			}
		} catch (Exception e) {}
	}
}
