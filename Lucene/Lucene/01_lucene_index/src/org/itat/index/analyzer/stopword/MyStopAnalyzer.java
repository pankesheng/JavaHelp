package org.itat.index.analyzer.stopword;

import java.io.Reader;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LetterTokenizer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.util.Version;
import org.itat.index.analyzer.utils.AnalyzerUtils;

public class MyStopAnalyzer extends Analyzer {
	@SuppressWarnings("rawtypes")
	private Set stops;
	@SuppressWarnings("unchecked")
	public MyStopAnalyzer(String[]sws) {
		//���Զ����ַ�������ת��ΪSet
		stops = StopFilter.makeStopSet(Version.LUCENE_35, sws, true);
		//��ԭ�е�ͣ�ôʼ��뵽���ڵ�ͣ�ô�
		stops.addAll(StopAnalyzer.ENGLISH_STOP_WORDS_SET);
	}
	
	public MyStopAnalyzer() {
		//��ȡԭ�е�ͣ�ô�
		stops = StopAnalyzer.ENGLISH_STOP_WORDS_SET;
	}

	@Override
	public TokenStream tokenStream(String fieldName, Reader reader) {
		//Ϊ����ִ����趨��������Tokenizer
		return new StopFilter(Version.LUCENE_35,
			   new LowerCaseFilter(Version.LUCENE_35, 
			   new LetterTokenizer(Version.LUCENE_35,reader)), stops);
	}

	public static void main(String[] args) {
		Analyzer a1 = new MyStopAnalyzer(new String[]{"I","you","hate"});
		Analyzer a2 = new MyStopAnalyzer();
		String txt = "how are you thank you I hate you";
		AnalyzerUtils.displayToken(txt, a1);
		AnalyzerUtils.displayToken(txt, a2);
	}
}
