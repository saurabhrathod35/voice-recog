package voicerecognition.com;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

public class VoiceRecognition {

	static final PopupMenu popup = new PopupMenu();
	static final TrayIcon trayIcon = new TrayIcon(createImage(
			"/voicerecognition/com/micro phone.gif", "tray icon"));
	static final SystemTray tray = SystemTray.getSystemTray();

	// Create a popup menu components
	static MenuItem aboutItem = new MenuItem("Start");
	static MenuItem cb1 = new MenuItem("Help");
	static MenuItem cb2 = new MenuItem("Exit");

	private static void createAndShowGUI() {
		// Check the SystemTray support
		if (!SystemTray.isSupported()) {
			System.out.println("SystemTray is not supported");
			return;
		}
		trayIcon.displayMessage("Sun TrayIcon Demo",
				"Application was startes",
				TrayIcon.MessageType.NONE);
		
		popup.add(aboutItem);
		popup.addSeparator();
		popup.addSeparator();

		popup.add(cb1);
		popup.addSeparator();

		popup.add(cb2);
		popup.addSeparator();

		trayIcon.setPopupMenu(popup);

		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			System.out.println("TrayIcon could not be added.");
			return;
		}

		trayIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"This dialog box is run from System Tray");
			}
		});

		trayIcon.setImageAutoSize(true);
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MenuItem item = (MenuItem) e.getSource();
				// TrayIcon.MessageType type = null;
				System.out.println(item.getLabel());
				if ("error".equals(item.getLabel())) {
					// type = TrayIcon.MessageType.ERROR;

					System.out.println("saurabh");
					trayIcon.displayMessage("Sun TrayIcon Demo",
							"This is an error message",
							TrayIcon.MessageType.ERROR);

				} else if ("Warning".equals(item.getLabel())) {
					// type = TrayIcon.MessageType.WARNING;
					trayIcon.displayMessage("Sun TrayIcon Demo",
							"This is a warning message",
							TrayIcon.MessageType.WARNING);

				} else if ("None".equals(item.getLabel())) {
					// type = TrayIcon.MessageType.NONE;
					trayIcon.displayMessage("Sun TrayIcon Demo",
							"This is an ordinary message",
							TrayIcon.MessageType.NONE);
				}
			}
		};

		cb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tray.remove(trayIcon);
				System.exit(0);
			}
		});
	}

	// Obtain the image URL
	protected static Image createImage(String path, String description) {
		URL imageURL = VoiceRecognition.class.getResource(path);

		if (imageURL == null) {
			System.err.println("Resource not found: " + path);
			return null;
		} else {
			return (new ImageIcon(imageURL, description)).getImage();
		}
	}

	// main

	public static void main(final String[] args) {
		/* Use an appropriate Look and Feel */
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			aboutItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {

					trayIcon.displayMessage("Sun TrayIcon Demo",
							"Application was started on tray",
							TrayIcon.MessageType.NONE);
					new HelloWorld().name(args);

				}
			});

			cb1.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					trayIcon.displayMessage("Voice Recognition Help",
							"Help menu", TrayIcon.MessageType.INFO);

					new HelpMenu().heplmenu();
				}
			});
			// UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		/* Turn off metal's use of bold fonts */
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
