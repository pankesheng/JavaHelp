package org.itat.index.filter.custom;

import java.io.IOException;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermDocs;
import org.apache.lucene.search.DocIdSet;
import org.apache.lucene.search.Filter;
import org.apache.lucene.util.OpenBitSet;

/**
 * 自定义过滤器（根据构造函数传递过滤条件）
 * @author ZCJ
 * @data 2012-5-16
 */
public class MyFilterB extends Filter {
	
	private static final long serialVersionUID = 1L;
	
	private MyFilterAccessor accessor;
	
	public MyFilterB(MyFilterAccessor accessor) {
		this.accessor = accessor;
	}

	@Override
	public DocIdSet getDocIdSet(IndexReader reader) throws IOException {
		//创建一个bit,默认所有的元素都是0
		OpenBitSet obs = new OpenBitSet(reader.maxDoc());
		if(accessor.isSet()) {
			set(reader,obs);
		} else {
			clear(reader, obs);
		}
		return obs;
	}
	
	private void set(IndexReader reader,OpenBitSet obs) {
		try {
			int[] docs = new int[1];
			int[] freqs = new int[1];
			for (String v : accessor.getValues()) {
				TermDocs tds = reader.termDocs(new Term(accessor.getField(), v));
				// 将查询出来的对象的位置存储到docs中，出现的频率存储到freqs中，返回获取的条数
				int count = tds.read(docs, freqs);
				if(count==1) {
					// 存到过滤结果里
					obs.set(docs[0]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void clear(IndexReader reader,OpenBitSet obs) {
		try {
			//先把过滤结果填满（不过滤任何记录）
			obs.set(0, reader.maxDoc());
			int[] docs = new int[1];
			int[] freqs = new int[1];
			for (String v : accessor.getValues()) {
				TermDocs tds = reader.termDocs(new Term(accessor.getField(), v));
				int count = tds.read(docs, freqs);
				if (count == 1) {
					// 在过滤结果中将这个位置的记录删除
					obs.clear(docs[0]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
