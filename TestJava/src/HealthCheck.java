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

		// URL Connection ����
		URL url;
		URLConnection conn;
		HttpURLConnection urlConnection;
		int TIMEOUT_VALUE = 5000;   // 5��

		// Check Result ����
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

		} catch (SocketTimeoutException e) {	// TimeOut ����ó��

			resultMsg = "[SocketTimeoutException]" + e.toString();
			System.out.println("[resultMsg] " + sysList + ", " + e.toString());

		} catch (Exception e) {

			resultMsg = e.toString();

		} finally {

			////////////////////////////////////////////////////////////////////////////////
			// ����(200)�� �ƴ� ��� ����� ����
			if(res != 200) {

				// ����� �����
				for(int listDtlCnt=0; listDtlCnt<ownerList.split(",").length; listDtlCnt++) {

					System.out.println("["+listDtlCnt+"]" + ownerList.split(",")[listDtlCnt]);
					// �����ڰ� �ִ� ��쿡�� ����
					if(ownerList.split(",")[listDtlCnt] != null){
					}
				}
				// ����� �޼���
				System.out.println("[" + sysList + "] " + resultMsg);
			}
			////////////////////////////////////////////////////////////////////////////////
		}
	}
	
	public static void main(String[] args) throws IOException {

		// üũ �ý��� ���
		String arraySysList[] = {
				"PSM (IN)",
				"PSM (OUT)",
				"SHUB (OUT)",
				"SHUB (IN)"
				};

		// URL üũ ��� ���
		String searchUrlList[] = {
				"https://psm.shinsegae.com/check.jsp",
				"https://psm.shinsegae.com:444/check.jsp",
				"https://hub.shinsegae.com/check.jsp",
				"https://shub.shinsegae.com/check.jsp"
				};

		// URL �� ����� ���
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