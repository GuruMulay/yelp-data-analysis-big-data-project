import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

import org.apache.hadoop.util.*;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.json.JSONArray;
import org.json.JSONObject;

	public class WordMapper extends Mapper<LongWritable, Text, Text, Text>{
			
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{
			
			String CurrentLine, attributes = "\t";
			String city = null, businessId = null;
			String zip = null, lat = null, lon = null, stars = null;
			CurrentLine = value.toString();
			JSONObject json;
				
			try {
				json = new JSONObject(CurrentLine);
				businessId = (String) json.get("business_id");
				city = (String) json.get("city");
				city = city.toLowerCase();
				zip = json.get("postal_code").toString();
				lat = json.get("latitude").toString();
				lon = json.get("longitude").toString();
				stars = json.get("stars").toString();				
			
			   		        
				} catch (Exception e) {
				e.printStackTrace();
				}
			
			Text OutKey = new Text (city + "\t" + businessId + "\t" + lat + "\t" + lon + "\t" + stars + "\t" + zip);
			Text OutValue = new Text ("one");
			context.write(OutKey, OutValue);

		} // map
	}

