package org.itat.index.analyzer.sameword;

import java.util.HashMap;
import java.util.Map;

public class SamewordContextImpl2 implements SamewordContext {
	
	Map<String,String[]> maps = new HashMap<String,String[]>();
	public SamewordContextImpl2() {
		maps.put("�й�",new String[]{"�쳯","��½"});
	}

	@Override
	public String[] getSamewords(String name) {
		return maps.get(name);
	}

}
