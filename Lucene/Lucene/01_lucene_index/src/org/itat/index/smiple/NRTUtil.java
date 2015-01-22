package org.itat.index.smiple;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NRTManager;
import org.apache.lucene.search.NRTManagerReopenThread;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.SearcherManager;
import org.apache.lucene.search.SearcherWarmer;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.chenlb.mmseg4j.analysis.MMSegAnalyzer;

public class NRTUtil {

	private Directory directory = null;
	private IndexWriter writer = null;
	private NRTManager nrtMgr = null;
	private SearcherManager mgr = null;
	
	public NRTUtil() {
		try {
			directory = FSDirectory.open(new File("d:/lucene/index"));
			writer = new IndexWriter(directory, new IndexWriterConfig(Version.LUCENE_35, new MMSegAnalyzer()));
			nrtMgr = new NRTManager(writer, new SearcherWarmer() {
				@Override
				public void warm(IndexSearcher arg0) throws IOException {
					System.out.println("reopen");
				}
			});
			
			/** ����NRTManager��Reopen�߳� */
			NRTManagerReopenThread reopen = new NRTManagerReopenThread(nrtMgr, 5.0, 0.025);
			reopen.setDaemon(true); // ��̨����
			reopen.setName("NrtManager Reopen Thread");// �߳���
			reopen.start();
			
			mgr = nrtMgr.getSearcherManager(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** ɾ��ĳ�� */
	public void delete() throws IOException {
		nrtMgr.deleteDocuments(new Term("id","2"));
	}
	
	/** ����ĳ��(Lucene��û���ṩ���£�����ĸ��²�����ʵ���������������ĺϼ�----��ɾ��֮�������) */
	public void update() throws IOException {
		Document doc = new Document();
		doc.add(new Field("id","11",Field.Store.YES,Field.Index.NOT_ANALYZED_NO_NORMS));
		doc.add(new Field("email","zou@sina.com",Field.Store.YES,Field.Index.NOT_ANALYZED));
		doc.add(new Field("content","dsfasglovegfdg",Field.Store.NO,Field.Index.ANALYZED));
		doc.add(new Field("name","zouchongjin",Field.Store.YES,Field.Index.NOT_ANALYZED_NO_NORMS));
		nrtMgr.updateDocument(new Term("id","1"), doc);
	}
	
	/** �ύ�������� */
	public void commit() throws CorruptIndexException, IOException {
		writer.commit();
	}
	
	/** ��ʵʱ���� */
	public void search() {
		
		/** ԭ���ķ�����Directory ==> IndexReader ==> IndexSearcher */
		/** ���ڵķ�����Directory ==> IndexWriter ==> NRTManager ==> SearcherManager ==> IndexSearcher */
		IndexSearcher searcher = mgr.acquire();
		try {
			TermQuery query = new TermQuery(new Term("content","love"));
			/** Query + IndexSearcher ==> TopDocs */
			TopDocs tds = searcher.search(query, 10);
			/** TopDocs ==> ScoreDoc */
			for (ScoreDoc sd : tds.scoreDocs) {
				/** ScoreDoc + IndexSearcher ==> Document */
				Document doc = searcher.doc(sd.doc);
				System.out.println(doc.get("id"));
			}
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				mgr.release(searcher);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/** ��ҳ���� */
	public void searchByPage() {
		
	}
	
	/** ���� */
	public static void main(String[] args) throws IOException {
		NRTUtil iu = new NRTUtil();
		for(int i=0;i<5;i++) {
			iu.search();
			iu.delete();
			iu.update();
			System.out.println("-----------------------------");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		iu.commit();
	}
	
}
