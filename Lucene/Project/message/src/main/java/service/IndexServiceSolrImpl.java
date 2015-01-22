package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import util.SolrContext;
import bean.Index;
import bean.IndexField;
import bean.PageObject;

public class IndexServiceSolrImpl implements IndexService {

	@Override
	public void addIndex(IndexField fields, boolean inDatabase) throws Exception {
		SolrContext.getServer().addBean(fields);
		if (inDatabase) {
			//TODO 保存到TempIndex表中
		}
	}

	@Override
	public void deleteIndex(String id, String type) throws Exception {
		SolrContext.getServer().deleteById(id);
		//TODO 保存到TempIndex表中
	}

	@Override
	public void updateIndex(IndexField fields) throws Exception {
		SolrContext.getServer().deleteById(fields.getId());
		SolrContext.getServer().addBean(fields);
		//TODO 保存到TempIndex表中
	}
	
	@Override
	public void updateCommitIndex() throws Exception {
		SolrContext.getServer().commit();
		//TODO 清空TempIndex表
	}

	@Override
	public void updateReconstructorIndex() throws Exception {
		SolrContext.getServer().deleteByQuery("*:*");
		//TODO 查出所有的留言数据，转成IndexField类型，调用addIndex方法
		SolrContext.getServer().commit();
		//TODO 清空TempIndex表
	}
	
	@Override
	public void updateSetIndex() throws Exception {
		//TODO 查出所有的TempIndex数据，查出对应的message，转成IndexField类型，调用addIndex方法
		SolrContext.getServer().commit();
		//TODO 清空TempIndex表
	}
	
	@Override
	public PageObject<Index> findByIndex(String condition) {
		if(condition==null) condition = "";
		PageObject<Index> pages = new PageObject<Index>();
		List<Index> datas = new ArrayList<Index>();
		try {
			int pageSize = 15;
			int pageOffset = 1;
			SolrQuery query = new SolrQuery(condition);
			query.setHighlight(true)
				.setHighlightSimplePre("<span class='lighter'>")
				.setHighlightSimplePost("</span>")
				.setParam("hl.fl", "msg_title,msg_content")
				.setStart(pageOffset).setRows(pageSize);
			QueryResponse resp = SolrContext.getServer().query(query);
			SolrDocumentList sdl = resp.getResults();
			for(SolrDocument sd : sdl) {
				
				// 查索引信息
				String id = (String)sd.getFieldValue("id");
				int msgId = (Integer)sd.getFieldValue("msg_id");
				String title = (String)sd.getFieldValue("msg_title");
				Date date = (Date)sd.getFieldValue("msg_date");
				@SuppressWarnings({ "unchecked", "rawtypes" })
				List<String> contents = (List)sd.getFieldValues("msg_content");
				StringBuffer sb = new StringBuffer();
				for(String con:contents) {
					sb.append(con);
				}
				System.out.println(sb.toString());
				String sc = sb.toString();
				
				// 生成用于显示到页面的记录
				Index index = new Index();
				index.setCreateDate(date);
				index.setTitle(title);
				if(sc.length()>=150) {
					sc = sc.substring(0,150);
				}
				index.setSummary(sc);
				index.setMsgId(msgId);
				if(resp.getHighlighting().get(id)!=null) {
					List<String> htitle = resp.getHighlighting().get(id).get("msg_title");
					if(htitle!=null) index.setTitle(htitle.get(0));
					List<String> hcontent = resp.getHighlighting().get(id).get("msg_content");
					if(hcontent!=null)index.setSummary(hcontent.get(0));
				}
				
				datas.add(index);
			}
			
			pages.setDatas(datas);
			pages.setOffset(pageOffset);
			pages.setPageSize(pageSize);
			pages.setTotalRecord(new Long(sdl.getNumFound()).intValue());
		} catch (SolrServerException e) {
			e.printStackTrace();
		} 
		return pages;
	}

}
