package com.sist.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.GoodsVO;

public interface GoodsService {
//	@Select("SELECT no, goods_name as name, goods_poster as poster, goods_price as price, num "
//			+ "FROM (SELECT no, goods_name, goods_poster, goods_price, rownum as num "
//			+ "FROM (SELECT no, goods_name, goods_poster, goods_price "
//			+ "FROM goods_all ORDER BY no ASC)) "
//			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<GoodsVO> goodsListData(Map map);
	// 상세보기 => Session (장바구니)
//	@Update("UPDATE goods_all SET hit=hit+1 WHERE no=#{no}")
//	public void hitIncrement(int no);
//	hitIncrement는 DAO에서 이미 증가 처리를 했으므로, service 필요 없음
	// 서비스를 통해 의존성이 낮은 프로그램 작성
	
//	@Select("SELECT no, goods_name as name, goods_sub as sub, goods_price as price, goods_discount as discount, "
//			+ "goods_first_price as first_price, goods_delivery as delivery, goods_poster as poster, hit, account "
//			+ "FROM goods_all WHERE no=#{no}")
	public GoodsVO goodsDetailData(int no);
	
	public int goodsTotalPage();
}
