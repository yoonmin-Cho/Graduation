package kr.ac.zebra.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.zebra.dao.AppReviewDAO;
import kr.ac.zebra.dto.Review;

@Service("appReviewService") // service로 bean에 등록시켜준다
public class AppReviewService {

	private AppReviewDAO reviewDAO;

	@Autowired // DI 주입
	public void setReviewDAO(AppReviewDAO reviewDAO) {
		this.reviewDAO = reviewDAO;
	}

	public List<Review> getReviews(String barcode) {

		return reviewDAO.getReviews(barcode);

	}

	public Review getReview(String barcode) {

		return reviewDAO.getReview(barcode);

	}

	public List<Review> getReviewsById(String id) {

		return reviewDAO.getReviewsById(id);

	}

	public void setReview(String id, String barcode, String reviewText, double starPoint, String memberUrl,
			String productUrl, String level) {

		if (reviewDAO.isExit(id, barcode) == null) {
			reviewDAO.setReview(id, barcode, reviewText, starPoint, memberUrl, productUrl, level);
		} else {

			reviewDAO.updateReview(id, barcode, reviewText, starPoint, memberUrl, productUrl, level);
		}

	}

	public List<Integer> getStarPoint(String barcode) {

		List<Integer> star = reviewDAO.getStarPoint(barcode);
		System.out.println("getStarPoint"+star);
		if(star.get(5)==0) return null;
		System.out.println("getStarPointAfter"+star);
		List<Integer> starAvarage = new ArrayList<Integer>();
		int sum = star.get(star.size() - 1);
		int count;

		for (int i = 0; i < 6; i++) {
			count = (star.get(i)*100)/sum;
			starAvarage.add(i, count);
			System.out.println(count);
		}
		
		System.out.println(starAvarage);

		return starAvarage;
	}
}
