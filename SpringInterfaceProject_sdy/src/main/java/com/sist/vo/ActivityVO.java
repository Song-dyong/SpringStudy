package com.sist.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityVO {
	private int acino, accno, price;
	private String title, review_count, reviewer, review_content, hours_use,location_name, location_poster,
					how_use, poster, main_poster;
	private double score;
}
