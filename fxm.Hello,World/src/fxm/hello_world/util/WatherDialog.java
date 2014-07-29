package fxm.hello_world.util;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.JDialog;
import javax.swing.JEditorPane;

/**
 * <p>
 * Title: WatherDialog
 * </p>
 * <p>
 * Description: ����ǶԻ����࣬ ������ʾָ�����еĵ��������Ԥ��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company:UF SOFT
 * </p>
 * 
 * @author ����
 * @version 1.0
 */
public class WatherDialog extends JDialog {
	String city = "����";

	private JEditorPane jEditorPane = null;

	/**
	 * This method initializes / public WatherDialog(String city) { super();
	 * this.city=city; initialize(); } /** This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setContentPane(getJEditorPane());
		try {
			// ����URL����
			URL url = new URL(
					"http://weather.news.sina.com.cncgi-bin/figureWeather/simpleSearch.cgi?city="
							+ city);
			String temp = "";
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));
			// ʹ��openStream�õ�һ������ ���ɴ˹���һ��BufferedReader����
			String inputLine;
			// �����������ϵĶ����ݣ� ֱ������Ϊֹ
			while ((inputLine = in.readLine()) != null)
				temp = temp + inputLine + "\n";
			// �ر�������
			in.close();
			String weather = temp.substring(temp.indexOf("<body"),
					temp.lastIndexOf("body>") + 5);

			this.jEditorPane.setText(weather);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setTitle("����Ԥ��");
		this.setSize(400, 166);

	}

	/**
	 * This method initializes jEditorPane
	 * 
	 * @return javax.swing.JEditorPane
	 */
	private JEditorPane getJEditorPane() {
		if (jEditorPane == null) {
			jEditorPane = new JEditorPane();
			jEditorPane.setContentType("text/html");
		}
		return jEditorPane;
	}
}