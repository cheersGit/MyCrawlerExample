package crawler.example;

import com.github.abola.crawler.CrawlerPack;
import org.apache.commons.logging.impl.SimpleLog;

/**
 * 爬蟲包程式的全貌，就只有這固定的模式
 * 
 * @author Abola Lee
 *
 */
public class PttExample {
	// commit test test
	public static void main(String[] args) {

		// set to debug level
		//CrawlerPack.setLoggerLevel(SimpleLog.LOG_LEVEL_DEBUG);

		// turn off logging
		CrawlerPack.setLoggerLevel(SimpleLog.LOG_LEVEL_OFF);

		// 遠端資料路徑
		//String uri = "https://pm25.lass-net.org/data/last-all-airbox.json";
		//String uri="http://www.twse.com.tw/exchangeReport/MI_INDEX?response=html&date=20171204&type=MS";
		String uri="https://www.ptt.cc/bbs/CVS/M.1512819517.A.AF8.html";
		System.out.println(
				CrawlerPack.start()
				
				// 參數設定
			    //.addCookie("key","value")	// 設定cookie
				.addCookie("over18","1")
						//.setRemoteEncoding("big5")// 設定遠端資料文件編碼
				
				// 選擇資料格式 (三選一)
				//.getFromJson(uri)
			    .getFromHtml(uri)
			    //.getFromXml(uri)

			    // 這兒開始是 Jsoup Document 物件操作
			    //.select(".css .selector ")
				//取出作者
					//	.select("#main-content > div:nth-child(1) > span.article-meta-value")

						//.select("span.hl.push-tag:contains(推)")
						.select("span.hl.push-tag:contains(推)+span.f3.hl.push-userid")
		);
	}
}
