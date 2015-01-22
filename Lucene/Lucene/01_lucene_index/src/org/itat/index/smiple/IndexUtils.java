package org.itat.index.smiple;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.TermVector;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;
import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;

import com.chenlb.mmseg4j.analysis.MMSegAnalyzer;

public class IndexUtils {
	
	private static Directory directory = null;
	private static IndexReader reader = null;
	
	static{
		try {
//			directory = new RAMDirectory();
			directory = FSDirectory.open(new File("d:/lucene/files/"));
			reader = IndexReader.open(directory, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Directory getDirectory() {
		return directory;
	}
	
	public static IndexReader getIndexReader() throws Exception {
		if (reader == null) {
			reader = IndexReader.open(directory, false);
		} else {
			/** 当使用Writer修改了索引之后需要手动更新信息 */
			IndexReader tr = IndexReader.openIfChanged(reader);
			if (tr != null) {
				reader.close();
				reader = tr;
			}
		}
		
		/** 索引的统计 */
//		System.out.println("numDocs:" + reader.numDocs());
//		System.out.println("maxDocs:" + reader.maxDoc());
//		System.out.println("deleteDocs:" + reader.numDeletedDocs());
		
		return reader;
	}
	
	public static IndexSearcher getSearcher() throws Exception {
		return new IndexSearcher(getIndexReader());
	}
	
	public static void index(boolean hasNew) {
		IndexWriter writer = null;
		try {
			writer = new IndexWriter(directory, new IndexWriterConfig(Version.LUCENE_35, new MMSegAnalyzer()));
			if(hasNew) {
				/** 删除所有索引 */
				writer.deleteAll();
			}
			File file = new File("d:/lucene/example2");
			Document doc = null;
			for(File f:file.listFiles()) {
				doc = createDoc(f);
				if(doc!=null) {
					writer.addDocument(doc);
				}
			}
			
			/** 放入回收站(参数可以是一个Query和Term) */
//			writer.deleteDocuments(new Term("id", "1"));
			
			/** 清空回收站 */
//			writer.forceMergeDeletes();
			
			/** 更新某条(Lucene并没有提供更新，这里的更新操作其实是如下两个操作的合集----先删除之后再添加) */
//			Document doc2 = new Document();
//			doc2.add(new Field("id", "11", Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
//			doc2.add(new Field("email", emails[0], Field.Store.YES, Field.Index.NOT_ANALYZED));
//			doc2.add(new Field("content", contents[0], Field.Store.NO, Field.Index.ANALYZED));
//			doc2.add(new Field("name", names[0], Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
//			writer.updateDocument(new Term("id", "1"), doc2);
			
			/** 提交之后，修改的才生效 */
//			writer.commit();
			
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(writer!=null) {
					/** 用完之后必须关闭(关闭之后，重新打开，修改的才生效) */
					writer.close();
				}
			} catch (CorruptIndexException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static Document createDoc(File f) throws IOException {
		if(f.isDirectory()) return null;
		Document doc = new Document();
		Metadata metadata = new Metadata();
		
		/** 
		 Field.Store(存储域选项)
		 	YES:	存储+可还原+可索引
		 	NO：		不存储+无法还原(可通过ID在数据库查整条记录再看信息)+可索引
		 Field.Index(索引选项)
		 	Index.ANALYZED:					分词+norms		适用于标题、内容等
		 	Index.NOT_ANALYZED:				不分词+norms		适用于Email、电话号码，身份证号，日期
		 	Index.ANALYZED_NOT_NORMS:		分词+不加权		
		 	Index.NOT_ANALYZED_NOT_NORMS:	不分词+不norms	适用于标识符(主键、文件名)、姓名
		 	Index.NO:不进行索引
		 */
		doc.add(new Field("content",	new Tika().parse(new FileInputStream(f),metadata),	TermVector.WITH_POSITIONS_OFFSETS));
		doc.add(new Field("title",		FilenameUtils.getBaseName(f.getName()),				Field.Store.YES,Field.Index.ANALYZED,	TermVector.WITH_POSITIONS_OFFSETS));
		doc.add(new Field("filename",	f.getName(),										Field.Store.YES,Field.Index.NOT_ANALYZED));
		doc.add(new Field("path",		f.getAbsolutePath(),								Field.Store.YES,Field.Index.NOT_ANALYZED));
		doc.add(new Field("type",		FilenameUtils.getExtension(f.getName()),			Field.Store.YES,Field.Index.NOT_ANALYZED_NO_NORMS));
		int page = 0;
		try {
			page = Integer.parseInt(metadata.get("xmpTPg:NPages"));
		} catch (NumberFormatException e) {}
		doc.add(new NumericField("page",Field.Store.YES,true).setIntValue(page));
		doc.add(new NumericField("size",Field.Store.YES,true).setIntValue((int)(f.length()/1024)));
		doc.add(new NumericField("date",Field.Store.YES,true).setLongValue(f.lastModified()));
		doc.add(new NumericField("now", Field.Store.YES,true).setLongValue(new Date().getTime()));
		
		/** 加权 */
		doc.setBoost(1.5f);
		
		return doc;
	}
}
