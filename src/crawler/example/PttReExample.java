package crawler.example;

import com.github.abola.crawler.CrawlerPack;
import org.apache.commons.logging.impl.SimpleLog;
import org.jsoup.select.Elements;


/**
 * 爬蟲包程式的全貌，就只有這固定的模式
 * 
 * @author Abola Lee
 *
 */
public class PttReExample {
	// commit test test
	public static void main(String[] args) {

		// set to debug level
		//CrawlerPack.setLoggerLevel(SimpleLog.LOG_LEVEL_DEBUG);

		// turn off logging
		CrawlerPack.setLoggerLevel(SimpleLog.LOG_LEVEL_OFF);

		// 遠端資料路徑
		//String uri = "https://pm25.lass-net.org/data/last-all-airbox.json";
		//String uri="http://www.twse.com.tw/exchangeReport/MI_INDEX?response=html&date=20171204&type=MS";
		String uri="https://www.ptt.cc/bbs/CVS/M.1512786866.A.DF1.html";


		Elements jsoup=CrawlerPack.start()
				.addCookie("over18","1")
				.getFromHtml(uri)
				.select("#main-content");
		System.out.println(jsoup.select("span,div").remove());
		//.setRemoteEncoding("big5")// 設定遠端資料文件編碼



	}
}
