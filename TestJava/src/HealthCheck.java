import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.ws.http.HTTPException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HealthCheck {

	
	public static void check(String sysList, String searchUrl, String ownerList){

		// URL Connection 변수
		URL url;
		URLConnection conn;
		HttpURLConnection urlConnection;
		int TIMEOUT_VALUE = 5000;   // 5초

		// Check Result 변수
		int res = 0;
		String resultMsg = "";
		
		try {
			url = new URL(searchUrl);
			conn = url.openConnection();
			urlConnection = (HttpURLConnection) conn;
            urlConnection.setReadTimeout(TIMEOUT_VALUE);

			res = urlConnection.getResponseCode();

		} catch (HTTPException e) {

			resultMsg = "[HTTPException]" + e.toString();
			System.out.println("[resultMsg] " + sysList + ", " + e.toString());

		} catch (SocketTimeoutException e) {	// TimeOut 에러처리

			resultMsg = "[SocketTimeoutException]" + e.toString();
			System.out.println("[resultMsg] " + sysList + ", " + e.toString());

		} catch (Exception e) {

			resultMsg = e.toString();

		} finally {

			////////////////////////////////////////////////////////////////////////////////
			// 성공(200)이 아닌 경우 블라섬톡 전송
			if(res != 200) {

				// 블라섬톡 대상자
				for(int listDtlCnt=0; listDtlCnt<ownerList.split(",").length; listDtlCnt++) {

					System.out.println("["+listDtlCnt+"]" + ownerList.split(",")[listDtlCnt]);
					// 수신자가 있는 경우에만 전송
					if(ownerList.split(",")[listDtlCnt] != null){
					}
				}
				// 블라섬톡 메세지
				System.out.println("[" + sysList + "] " + resultMsg);
			}
			////////////////////////////////////////////////////////////////////////////////
		}
	}
	
	public static void main(String[] args) throws IOException {

		// 체크 시스템 목록
		String arraySysList[] = {
				"PSM (IN)",
				"PSM (OUT)",
				"SHUB (OUT)",
				"SHUB (IN)"
				};

		// URL 체크 대상 목록
		String searchUrlList[] = {
				"https://psm.shinsegae.com/check.jsp",
				"https://psm.shinsegae.com:444/check.jsp",
				"https://hub.shinsegae.com/check.jsp",
				"https://shub.shinsegae.com/check.jsp"
				};

		// URL 별 대상자 목록
		String arrayOwnerList[] = {
				"153711,,",
				"153711,,",
				"153711,,",
				"153711,,"
				};

		for (int cnt=0; cnt<searchUrlList.length; cnt++) {
			System.out.println(cnt + ". " + arraySysList[cnt] + ", " + searchUrlList[cnt] + ", " + arrayOwnerList[cnt]);

			// Url Check
			check(arraySysList[cnt], searchUrlList[cnt], arrayOwnerList[cnt]);
		}

	}

}