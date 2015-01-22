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
			/** ��ʹ��Writer�޸�������֮����Ҫ�ֶ�������Ϣ */
			IndexReader tr = IndexReader.openIfChanged(reader);
			if (tr != null) {
				reader.close();
				reader = tr;
			}
		}
		
		/** ������ͳ�� */
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
				/** ɾ���������� */
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
			
			/** �������վ(����������һ��Query��Term) */
//			writer.deleteDocuments(new Term("id", "1"));
			
			/** ��ջ���վ */
//			writer.forceMergeDeletes();
			
			/** ����ĳ��(Lucene��û���ṩ���£�����ĸ��²�����ʵ���������������ĺϼ�----��ɾ��֮�������) */
//			Document doc2 = new Document();
//			doc2.add(new Field("id", "11", Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
//			doc2.add(new Field("email", emails[0], Field.Store.YES, Field.Index.NOT_ANALYZED));
//			doc2.add(new Field("content", contents[0], Field.Store.NO, Field.Index.ANALYZED));
//			doc2.add(new Field("name", names[0], Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
//			writer.updateDocument(new Term("id", "1"), doc2);
			
			/** �ύ֮���޸ĵĲ���Ч */
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
					/** ����֮�����ر�(�ر�֮�����´򿪣��޸ĵĲ���Ч) */
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
		 Field.Store(�洢��ѡ��)
		 	YES:	�洢+�ɻ�ԭ+������
		 	NO��		���洢+�޷���ԭ(��ͨ��ID�����ݿ��������¼�ٿ���Ϣ)+������
		 Field.Index(����ѡ��)
		 	Index.ANALYZED:					�ִ�+norms		�����ڱ��⡢���ݵ�
		 	Index.NOT_ANALYZED:				���ִ�+norms		������Email���绰���룬���֤�ţ�����
		 	Index.ANALYZED_NOT_NORMS:		�ִ�+����Ȩ		
		 	Index.NOT_ANALYZED_NOT_NORMS:	���ִ�+��norms	�����ڱ�ʶ��(�������ļ���)������
		 	Index.NO:����������
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
		
		/** ��Ȩ */
		doc.setBoost(1.5f);
		
		return doc;
	}
}
