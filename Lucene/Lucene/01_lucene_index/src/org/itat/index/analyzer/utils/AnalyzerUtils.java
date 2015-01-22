package org.itat.index.analyzer.utils;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.apache.lucene.util.Version;
import org.itat.index.analyzer.sameword.MySameAnalyzer;
import org.itat.index.analyzer.sameword.SamewordContextImpl;

import com.chenlb.mmseg4j.analysis.MMSegAnalyzer;


public class AnalyzerUtils {

	public static void displayToken(String str,Analyzer a) {
		try {
			TokenStream stream = a.tokenStream("content",new StringReader(str));
			/** ����һ�����ԣ�������Ի�������У��������TokenStream���� */
			CharTermAttribute cta = stream.addAttribute(CharTermAttribute.class);
			while(stream.incrementToken()) {
				System.out.print("["+cta+"]");
			}
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void displayAllTokenInfo(String str,Analyzer a) {
		try {
			TokenStream stream = a.tokenStream("content",new StringReader(str));
			/** λ�����������ԣ��洢��㵥Ԫ֮��ľ��� */
			PositionIncrementAttribute pia = stream.addAttribute(PositionIncrementAttribute.class);
			/** ÿ����㵥Ԫ��λ��ƫ���� */
			OffsetAttribute oa = stream.addAttribute(OffsetAttribute.class);
			/** �洢ÿһ����㵥Ԫ����Ϣ���ִʵ�Ԫ��Ϣ�� */
			CharTermAttribute cta = stream.addAttribute(CharTermAttribute.class);
			/** ʹ�õķִ�����������Ϣ */
			TypeAttribute ta = stream.addAttribute(TypeAttribute.class);
			for(;stream.incrementToken();) {
				System.out.print(pia.getPositionIncrement()+":");
				System.out.print(cta+"["+oa.startOffset()+"-"+oa.endOffset()+"]-->"+ta.type()+"\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Analyzer a1 = new StandardAnalyzer(Version.LUCENE_35);
		Analyzer a2 = new StopAnalyzer(Version.LUCENE_35);
		Analyzer a3 = new SimpleAnalyzer(Version.LUCENE_35);
		Analyzer a4 = new WhitespaceAnalyzer(Version.LUCENE_35);
		Analyzer a5 = new MMSegAnalyzer(new File("D:\\tools\\javaTools\\lucene\\mmseg4j-1.8.5\\data"));
		Analyzer a6 = new MySameAnalyzer(new SamewordContextImpl());
		String txt = "�������й�������ͨ������ʦר";
		
		AnalyzerUtils.displayToken(txt, a1);
		AnalyzerUtils.displayToken(txt, a2);
		AnalyzerUtils.displayToken(txt, a3);
		AnalyzerUtils.displayToken(txt, a4);
		AnalyzerUtils.displayToken(txt, a5);
		AnalyzerUtils.displayToken(txt, a6);
		
		AnalyzerUtils.displayAllTokenInfo(txt, a1);
		AnalyzerUtils.displayAllTokenInfo(txt, a2);
		AnalyzerUtils.displayAllTokenInfo(txt, a3);
		AnalyzerUtils.displayAllTokenInfo(txt, a4);
		AnalyzerUtils.displayAllTokenInfo(txt, a5);
		AnalyzerUtils.displayAllTokenInfo(txt, a6);
	}
}
