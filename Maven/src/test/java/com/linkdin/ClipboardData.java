package com.linkdin;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ClipboardData {
	public static String clipboardPaste() throws UnsupportedFlavorException, IOException {
		Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable t = c.getContents(null);
		if (t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
			String data = (String) t.getTransferData(DataFlavor.stringFlavor);
			return data;
		}
		return null;
	}

}