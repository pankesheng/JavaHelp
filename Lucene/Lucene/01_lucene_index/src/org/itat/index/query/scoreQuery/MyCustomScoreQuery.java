package org.itat.index.query.scoreQuery;

import java.io.IOException;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.function.CustomScoreProvider;
import org.apache.lucene.search.function.CustomScoreQuery;

public class MyCustomScoreQuery extends CustomScoreQuery {
	private static final long serialVersionUID = 1L;
	
	public MyCustomScoreQuery(Query subQuery) {
		super(subQuery);
	}
	@Override
	protected CustomScoreProvider getCustomScoreProvider(IndexReader reader) throws IOException {
		return new MyCustomScoreProvider(reader);
	}
}