
import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class AvgStockVolPerMonthCombiner extends Reducer<TextPair, LongPair, TextPair, LongPair> {
	
	private static Long totalVolume = new Long(0);
	private static Long noOfRecords = new Long(0);
	private static LongPair result = new LongPair();
	
	public void reduce(TextPair key, Iterable<LongPair> values, Context context) throws IOException, InterruptedException {
		totalVolume = new Long(0);
		noOfRecords = new Long(0);
		for(LongPair value : values) {
			totalVolume += value.getFirst().get();
			noOfRecords += value.getSecond().get();
		}
		result.setFirst(new LongWritable(totalVolume));
		result.setSecond(new LongWritable(noOfRecords));
		context.write(key, result);
	}

}
