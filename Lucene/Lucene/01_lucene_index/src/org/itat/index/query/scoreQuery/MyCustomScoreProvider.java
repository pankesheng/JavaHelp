package org.itat.index.query.scoreQuery;

import java.io.IOException;
import java.util.Date;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.FieldCache;
import org.apache.lucene.search.function.CustomScoreProvider;

public class MyCustomScoreProvider extends CustomScoreProvider {
	String[] filenames = null;
	long[] dates = null;
	public MyCustomScoreProvider(IndexReader reader) {
		super(reader);
		try {
			// 在reader没有关闭之前，所有的数据会存储要一个域缓存中，可以通过域缓存获取很多有用的信息
			filenames = FieldCache.DEFAULT.getStrings(reader, "filename");
			dates = FieldCache.DEFAULT.getLongs(reader, "date");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/** subQueryScore表示默认文档的打分;valSrcScore表示评分域的打分 */
	@Override
	public float customScore(int doc, float subQueryScore, float valSrcScore) throws IOException {
		float result = subQueryScore;
		String filename = filenames[doc];
		long date = dates[doc];
		if(filename.endsWith(".txt")||filename.endsWith(".ini")) {
			result = result*1.5f;
		}
		if(new Date().getTime()-date<=1000*60*60*24*365) {
			result = result*2.5f;
		}
		return result;
	}
}
