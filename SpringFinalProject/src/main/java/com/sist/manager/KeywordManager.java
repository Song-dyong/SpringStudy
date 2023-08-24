package com.sist.manager;

import org.snu.ids.ha.index.Keyword;
import org.snu.ids.ha.index.KeywordExtractor;
import org.snu.ids.ha.index.KeywordList;
import org.springframework.stereotype.Component;

public class KeywordManager {
	public static void main(String[] args) {
		String keyword="두시간 밖에(?!) 안기다리고 들어가서 먹을수 있었습니다. 다음에 가시는 분들은 꼭 예약하고 가세요.\n" + 
				"\n" + 
				"\n" + 
				"\n" + 
				"막국수 곱배기 12,000원, 닭갈비 14,000원에 먹었습니다. 뷰 값하고 더하면 적당한 가격이었던 것 같아요. 닭갈비는 양념이 적당히 매콤하고, 적당히 쫄깃해서 적당히 구우면  적당히 타서 먹기 딱 좋아졌구요. 막국수도 비벼 먹으면 적당한 채소와 적당한 양념에 버무려져서 먹기 딱 좋은 맛이 됐습니다. \n" + 
				"\n" + 
				"\n" + 
				"\n" + 
				"서울에서 가는 길이 가깝지는 않아서 가는 길이 1시간 넘게 걸려서 가는 도중에 내비가 길을 맞게 안내하는지 확인하러 차를 세우기까지 하고, 닭갈비 먹으러 춘천까지 가는거냐며 와이프가 놀리기도 했었는데요. 춘천 갈일은 잘 없을것 같은 맛이었습니다.";
		KeywordExtractor ke=new KeywordExtractor();
		KeywordList list=ke.extractKeyword(keyword, true);
		
		for(int i=0;i<list.size();i++) {
			Keyword wrd=list.get(i);
			System.out.println(wrd.getString()+":"+wrd.getCnt());
		}
	}
}
