package org.itat.lucene.solr.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Before;
import org.junit.Test;

public class SolrTest {
	private final static String URL = "http://localhost:8080/solr";
	private CommonsHttpSolrServer server = null;
	
	@Before
	public void init() {
		try {
			server = new CommonsHttpSolrServer(URL);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test01() {
		try {
			List<Message> msgs = new ArrayList<Message>();
			msgs.add(new Message("4","����java bean�����",
					 new String[]{"ͨ��java bean������","java bean����Ӹ���"}));
			msgs.add(new Message("5","����java bean���б����ݵ����",
					 new String[]{"�������ͨ��һ������������","ͨ�����������ӵĸ���"}));
			server.addBeans(msgs);
			server.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test02() {
		try {
			SolrQuery query = new SolrQuery("����");
			query.setStart(0).setRows(5);
			// �����Ҫ����������Ҫ������������
			query.setHighlight(true)
					.setHighlightSimplePre("<span class='highligter'>")
					.setHighlightSimplePost("</span>")
					.setParam("hl.fl", "msg_title,msg_content");
			QueryResponse resp = server.query(query);
			SolrDocumentList sdl = resp.getResults();
			System.out.println(sdl.getNumFound());
			for(SolrDocument sd : sdl) {
				System.out.println(sd.getFieldValue("msg_content"));// ֻ��ʾδ�Ӹ���������
				String id = (String)sd.getFieldValue("id");
				System.out.println(resp.getHighlighting().get(id).get("msg_content"));// ��ʾ�����������
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
	}
}
