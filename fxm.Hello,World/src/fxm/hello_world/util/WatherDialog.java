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
 * Description: 这个是对话框类， 用于显示指定城市的当天的天气预报
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company:UF SOFT
 * </p>
 * 
 * @author 赵勇
 * @version 1.0
 */
public class WatherDialog extends JDialog {
	String city = "北京";

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
			// 构建URL对象
			URL url = new URL(
					"http://weather.news.sina.com.cncgi-bin/figureWeather/simpleSearch.cgi?city="
							+ city);
			String temp = "";
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));
			// 使用openStream得到一输入流 并由此构造一个BufferedReader对象
			String inputLine;
			// 从输入流不断的读数据， 直到读完为止
			while ((inputLine = in.readLine()) != null)
				temp = temp + inputLine + "\n";
			// 关闭输入流
			in.close();
			String weather = temp.substring(temp.indexOf("<body"),
					temp.lastIndexOf("body>") + 5);

			this.jEditorPane.setText(weather);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setTitle("天气预报");
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