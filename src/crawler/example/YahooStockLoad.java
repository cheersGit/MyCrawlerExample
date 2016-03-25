package crawler.example;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class YahooStockLoad {
	
	static Logger log = LoggerFactory.getLogger(YahooStockLoad.class);
	
	final static String mongodbServer = "128.199.204.20";
	final static String mongodbDB = "mydb";

	public static void main(String[] args) {
		
		MongoClient mongoClient ;
		try {
			mongoClient = new MongoClient( mongodbServer );

			DB db = mongoClient.getDB( mongodbDB );
			
			DBCollection coll = db.getCollection("TransDetail");
			
			// 總筆數
			log.debug("total row of detail: {}", coll.getCount());
			
			// 不重覆筆數 of time
			log.debug("distinct row of detail: {}", coll.distinct("time").size());
			
			DBObject fields = new BasicDBObject();
			fields.put( "stock", "$stock" );
			fields.put( "day", "$day" );
			fields.put( "time", "$time" );
			fields.put( "strike", "$strike" );
			
			// Contruct group element
			DBObject groupFields = new BasicDBObject( "_id", fields );
			groupFields.put( "volume", new BasicDBObject( "$max", "$volume" ) );
			DBObject group = new BasicDBObject( "$group", groupFields );
			
			// same as bottom sql
			// ---
			// select stock, day, time, strike, max(volume) 
			// from TransDetail
			// group by stock, day, time, strike
			
			Iterator iter = coll.aggregate( group ).results().iterator();
			int i=0;
			while(iter.hasNext()){
				log.debug( iter.next().toString() );
				++i;
			}
			log.debug("group row of detail: {}",i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
