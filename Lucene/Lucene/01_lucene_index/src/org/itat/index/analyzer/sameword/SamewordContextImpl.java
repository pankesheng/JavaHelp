package org.itat.index.analyzer.sameword;

import java.util.HashMap;
import java.util.Map;

public class SamewordContextImpl implements SamewordContext {
	
	Map<String,String[]> maps = new HashMap<String,String[]>();
	
	public SamewordContextImpl() {
		maps.put("�й�",new String[]{"�쳯","��½"});
		maps.put("��",new String[]{"��","��"});
	}

	@Override
	public String[] getSamewords(String name) {
		return maps.get(name);
	}

}
