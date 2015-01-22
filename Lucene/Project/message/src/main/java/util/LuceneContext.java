package util;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NRTManager;
import org.apache.lucene.search.NRTManagerReopenThread;
import org.apache.lucene.search.SearcherManager;
import org.apache.lucene.search.SearcherWarmer;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.chenlb.mmseg4j.analysis.MMSegAnalyzer;

public class LuceneContext {
	
	private static LuceneContext instance;
	private static final String INDEX_PATH = "d:/lucene/message";
	private static final String DATA_PATH = "data";
	private static IndexWriter writer;
	private static Analyzer analyzer;
	private static Version version;
	private static NRTManager nrtMgr;
	private static SearcherManager mgr;
	private static Directory directory;
	private LuceneContext(){}
	
	public static LuceneContext getInstance() {
		if(instance==null){
			init();
			instance = new LuceneContext();
		}
		return instance;
	}
	
	private static void init() {
		try {
			directory = FSDirectory.open(new File(INDEX_PATH));
			version = Version.LUCENE_35;
			analyzer = new MMSegAnalyzer(LuceneContext.class.getClassLoader().getResource(DATA_PATH).getPath());
			writer = new IndexWriter(directory,new IndexWriterConfig(version,analyzer));
			nrtMgr = new NRTManager(writer, new SearcherWarmer() {
				@Override
				public void warm(IndexSearcher arg0) throws IOException {
					System.out.println("reopen index");
				}
			});
			mgr = nrtMgr.getSearcherManager(true);
			
			/** 启动NRTManager的Reopen线程 */
			NRTManagerReopenThread reopenThread = new NRTManagerReopenThread(nrtMgr, 5.0,0.025);
			reopenThread.setName("NRTManager reopen thread");// 线程名
			reopenThread.setDaemon(true);// 后台运行
			reopenThread.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public IndexSearcher getSearcher() {
		return mgr.acquire();
	}
	
	public void releaseSearcher(IndexSearcher searcher) {
		try {
			mgr.release(searcher);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void commitIndex() {
		try {
			writer.commit();
			writer.forceMerge(3);
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public NRTManager getNRTManager() {
		return nrtMgr;
	}
	
	public Version getVersion() {
		return version;
	}
	
	public Analyzer getAnalyzer() {
		return analyzer;
	}
}
