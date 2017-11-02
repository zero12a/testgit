import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class TakeDomain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// 리니지 스킨, 메시, 던전앤파이터, 기부단체, 사망신고절차, 보험비교사이트, 머리나게하는방법, 노무현 탄핵 이유, 세금계산서
		// 발행방법, 육아휴직급여신청, 새해 글귀, 배에 가스가 차는 이
		// 페이스북 방문자, 아이폰 음악넣는법, 아이폰 초기화, 홍명보 감독, 걷기운동 효과, 팔굽혀펴기 효과, 이수근 아내, 집에서
		// 하는 유산소운동, 무통장입금 하는법, 월드컵 예선, 메이플스토리
		// 아프리카TV,하,까,감사,알아,습니다, 발걸음, 감, 해몽,
		String arrayMstr[] = {
				"https://search.naver.com/search.naver?where=post&query=%ED%81%AC%EB%A6%AC&ie=utf8&st=sim&sm=tab_opt&date_from=20050601&date_to=20100901&date_option=8&srchby=all&dup_remove=1&post_blogurl=tistory.com%2F&post_blogurl_without=&nso=so%3Ar%2Ca%3Aall%2Cp%3Afrom20050601to20100901&start=",
				"https://search.naver.com/search.naver?where=post&query=%EA%B9%9C%EC%A7%9D&ie=utf8&st=sim&sm=tab_opt&date_from=20050601&date_to=20100901&date_option=8&srchby=all&dup_remove=1&post_blogurl=tistory.com%2F&post_blogurl_without=&nso=so%3Ar%2Ca%3Aall%2Cp%3Afrom20050601to20100901&start=",
				"https://search.naver.com/search.naver?where=post&query=%EA%B9%9C%EB%86%80&ie=utf8&st=sim&sm=tab_opt&date_from=20050601&date_to=20100901&date_option=8&srchby=all&dup_remove=1&post_blogurl=tistory.com%2F&post_blogurl_without=&nso=so%3Ar%2Ca%3Aall%2Cp%3Afrom20050601to20100901&start=",
				"https://search.naver.com/search.naver?where=post&query=%ED%96%89%EC%82%AC&ie=utf8&st=sim&sm=tab_opt&date_from=20050601&date_to=20100901&date_option=8&srchby=all&dup_remove=1&post_blogurl=tistory.com%2F&post_blogurl_without=&nso=so%3Ar%2Ca%3Aall%2Cp%3Afrom20050601to20100901&start=",
				"https://search.naver.com/search.naver?where=post&query=%ED%99%94%EC%82%B0&ie=utf8&st=sim&sm=tab_opt&date_from=20050601&date_to=20100901&date_option=8&srchby=all&dup_remove=1&post_blogurl=tistory.com%2F&post_blogurl_without=&nso=so%3Ar%2Ca%3Aall%2Cp%3Afrom20050601to20100901&start=" };
		for (int k = 0; k < arrayMstr.length; k++) {
			System.out.println("index top: " + k);
			for (int i = 0; i < 100; i++) {
				System.out.println("index : " + i);
				int index = 1 + (i * 10);
				String searchUrl = arrayMstr[k];
				Document doc = Jsoup.connect(searchUrl + index).get();
				// System.out.println(doc.toString());
				Elements links = doc.select("a[href]");
				// Element l: links
				for (int j = 0; j < links.size(); j++) {
					if (links.get(j).attr("abs:href").contains(".tistory.com")) {
						// System.out.println("link : " + l.attr("abs:href"));
						String linkUrl = links.get(j).attr("abs:href");
						// String test[] = linkUrl.split(".com");
						//
						// for(int i=0;i<test.length;i++)
						// {
						// System.out.println("test : " + test[i]);
						//
						// }
						try {
							URL url = new URL(linkUrl);
							URLConnection conn = url.openConnection();
							HttpURLConnection hurlc = (HttpURLConnection) conn;
							// System.out.println("responseCode : " +
							// hurlc.getResponseCode());
							int res = hurlc.getResponseCode();

							if (res == 404) {

								System.out.println("jomni : " + linkUrl);
							} else {

								// System.out.println("jomni : 12" + linkUrl);

								j = j + 2;
							}

							// 헤더값을 설정한다.

						} catch (Exception e) {

							System.out.println("123 : " + e.toString());

						} finally {
							try {

								// System.out.println("finally try : ");
							} catch (Exception e) {
								System.out.println("finally catch : "
										+ e.toString());
							}
						}
					}
				}
			}
		}

	}

}