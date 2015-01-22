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
			// ��readerû�йر�֮ǰ�����е����ݻ�洢Ҫһ���򻺴��У�����ͨ���򻺴��ȡ�ܶ����õ���Ϣ
			filenames = FieldCache.DEFAULT.getStrings(reader, "filename");
			dates = FieldCache.DEFAULT.getLongs(reader, "date");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/** subQueryScore��ʾĬ���ĵ��Ĵ��;valSrcScore��ʾ������Ĵ�� */
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
