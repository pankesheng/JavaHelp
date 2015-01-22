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
			msgs.add(new Message("4","基于java bean的添加",
					 new String[]{"通过java bean完成添加","java bean的添加附件"}));
			msgs.add(new Message("5","基于java bean的列表数据的添加",
					 new String[]{"测试如何通过一个对象完成添加","通过对象完成添加的附件"}));
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
			SolrQuery query = new SolrQuery("测试");
			query.setStart(0).setRows(5);
			// 如果需要高亮，则需要设置以下内容
			query.setHighlight(true)
					.setHighlightSimplePre("<span class='highligter'>")
					.setHighlightSimplePost("</span>")
					.setParam("hl.fl", "msg_title,msg_content");
			QueryResponse resp = server.query(query);
			SolrDocumentList sdl = resp.getResults();
			System.out.println(sdl.getNumFound());
			for(SolrDocument sd : sdl) {
				System.out.println(sd.getFieldValue("msg_content"));// 只显示未加高亮的内容
				String id = (String)sd.getFieldValue("id");
				System.out.println(resp.getHighlighting().get(id).get("msg_content"));// 显示高亮后的内容
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
	}
}
