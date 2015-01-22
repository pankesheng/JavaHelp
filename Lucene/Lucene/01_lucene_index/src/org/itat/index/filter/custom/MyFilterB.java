package org.itat.index.filter.custom;

import java.io.IOException;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermDocs;
import org.apache.lucene.search.DocIdSet;
import org.apache.lucene.search.Filter;
import org.apache.lucene.util.OpenBitSet;

/**
 * �Զ�������������ݹ��캯�����ݹ���������
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
		//����һ��bit,Ĭ�����е�Ԫ�ض���0
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
				// ����ѯ�����Ķ����λ�ô洢��docs�У����ֵ�Ƶ�ʴ洢��freqs�У����ػ�ȡ������
				int count = tds.read(docs, freqs);
				if(count==1) {
					// �浽���˽����
					obs.set(docs[0]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void clear(IndexReader reader,OpenBitSet obs) {
		try {
			//�Ȱѹ��˽���������������κμ�¼��
			obs.set(0, reader.maxDoc());
			int[] docs = new int[1];
			int[] freqs = new int[1];
			for (String v : accessor.getValues()) {
				TermDocs tds = reader.termDocs(new Term(accessor.getField(), v));
				int count = tds.read(docs, freqs);
				if (count == 1) {
					// �ڹ��˽���н����λ�õļ�¼ɾ��
					obs.clear(docs[0]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
