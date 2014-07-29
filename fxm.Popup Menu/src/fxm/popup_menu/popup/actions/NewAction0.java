package fxm.popup_menu.popup.actions;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchWindow;


public class NewAction0 implements IObjectActionDelegate {

	private Shell shell;

	/**
	 * Constructor for Action1.
	 */
	public NewAction0() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		// MessageDialog.openInformation(
		// shell,
		// "Popup_Menu",
		// "操作 was executed.");

		// MessageDialog.openInformation(shell, "Cc",
		// "New Action was executed.");
		try {
			Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
			setClipboardText(
					clip,
					"@Override\n" +
					"public List<Bean> getListBy(Bean bean) throws Exception {\n" +
					"\tList<Bean> list = new ArrayList<Bean>();\n" +
					"\tStringBuffer sql = new StringBuffer();\n" +
					"\t// 拼接sql beg\n" +
					"\tsql.append(\"\");\n" +
					"\t\n" +
					"\t// 拼接sql end\n" +
					"\tsql.append(\"where 1=1\");\n" +
					"\tList<String> codition = new ArrayList<String>();\n" +
					"\tif (!StringUtil.isBlank(bean.get())) {\n" +
					"\t\tsql.append(\" and xxx = ?\"); //sql.append(\" and xxx like ?\");\n" +
					"\t\tcodition.add(StringUtil.deNull(bean.get()));//codition.add(\"%\"+StringUtil.deNull(bean.get())+\"%\");\n" +
					"\t}\n" +
					"\tPreparedDBUtil pdb = new PreparedDBUtil();\n" +
					"\tpdb.preparedSelect(dsName, sql.toString());\n" +
					"\tint c=1; \n" +
					"\tfor (int i = 0; i < codition.size(); i++) { \n" +
					"\t\tpdb.setString(c++, codition.get(i)); \n" +
					"\t }\n" +
					"\tpdb.executePrepared(); \n" +
					"\tfor (int i = 0; i < pdb.size(); i++) { \n" +
					"\t\tBean temp = new Bean(); \n" +
					"\t\t// 封装bean beg\n" +
					"\t\ttemp.set(pdb.getString(i, \"xxx\")); \n" +
					"\t\n" +
					"\t\t// 封装bean end\n" +
					"\t\tlist.add(temp); \n" +
					"\t }\n" +
					"\treturn list;\n" +
					"}");
			WorkbenchWindow workbenchWindow = (WorkbenchWindow) PlatformUI
					.getWorkbench().getActiveWorkbenchWindow();
			IActionBars bars = workbenchWindow.getActionBars();
			IStatusLineManager statusLineManager = bars.getStatusLineManager();
			statusLineManager
					.setErrorMessage("get function has been appended to your clipboard! paste it where you like!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void setClipboardText(Clipboard clip, String writeMe) {
		// 创建能传输指定 String 的 Transferable,定义为传输操作 提供数据所使用的类 的接口
		Transferable tText = new StringSelection(writeMe);
		/*
		 * Clipboard:此类实现一种使用剪切/复制/粘贴操作传输数据的机制,
		 * 将剪贴板的当前内容设置到指定的 transferable 对象，并将指定的剪贴板所有者作为新内容的所有者注册
		 */
		clip.setContents(tText, null);
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

}
