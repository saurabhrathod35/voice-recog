/*
 * Copyright 1999-2004 Carnegie Mellon University.
 * Portions Copyright 2004 Sun Microsystems, Inc.
 * Portions Copyright 2004 Mitsubishi Electric Research Laboratories.
 * All Rights Reserved.  Use is subject to license terms.
 *
 * See the file "license.terms" for information on usage and
 * redistribution of this file, and for a DISCLAIMER OF ALL
 * WARRANTIES.
 *
 */

package voicerecognition.com;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import edu.cmu.sphinx.util.props.PropertyException;

/**
 * A simple HelloWorld demo showing a simple speech application built using
 * Sphinx-4. This application uses the Sphinx-4 endpointer, which automatically
 * segments incoming audio into utterances and silences.
 */
public class HelloWorld {

	/**
	 * Main method for running the HelloWorld demo.
	 */
	public static void name(String[] args) {
		try {
			System.out.println(System.getProperty("sun.arch.data.model"));

			URL url;
			if (args.length > 0) {
				url = new File(args[0]).toURI().toURL();
			} else {
				url = HelloWorld.class.getResource("helloworld.config.xml");
			}

			System.out.println("Loading...");

			ConfigurationManager cm = new ConfigurationManager(url);

			Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
			Microphone microphone = (Microphone) cm.lookup("microphone");

			/* allocate the resource necessary for the recognizer */
			recognizer.allocate();

			/* the microphone will keep recording until the program exits */
			boolean loop = true;
			if (microphone.startRecording()) {
				while (loop) {
					System.out
							.println("Start speaking. Press Ctrl-C to quit.\n");

					/*
					 * This method will return when the end of speech is
					 * reached. Note that the endpointer will determine the end
					 * of speech.
					 */

					Result result = recognizer.recognize();
					String resultText = result.getBestFinalResultNoFiller();
					if (result != null) {
						System.out.println("You said: " + resultText + "\n");

						// paint//
						if (resultText.equals("open paint")) {
							Runtime rt = Runtime.getRuntime();
							rt.exec(new String[] { "cmd.exe", "/c", "mspaint" });
						}

						if (resultText.equals("close paint")) {
							Runtime rt = Runtime.getRuntime();
							rt.exec(new String[] { "cmd.exe", "/c",
									"taskkill /f /im mspaint.exe" });
						}

						// word//
						if (resultText.equals("open word")) {
							Runtime rt = Runtime.getRuntime();
							rt.exec(new String[] { "cmd.exe", "/c",
									"start winword.EXE" });
						}

						if (resultText.equals("close word")) {
							Runtime rt = Runtime.getRuntime();
							rt.exec(new String[] { "cmd.exe", "/c",
									"taskkill /im winword.exe /f" });
						}

						// notepad//
						if (resultText.equals("open note pad")) {
							Runtime rt = Runtime.getRuntime();
							rt.exec(new String[] { "cmd.exe", "/c", "notepad" });
						}
						if (resultText.equals("close note pad")) {
							Runtime rt = Runtime.getRuntime();
							rt.exec(new String[] { "cmd.exe", "/c",
									"taskkill /f /im notepad.exe" });
						}

						// browser//
						if (resultText.equals("open browser")) {
							Runtime rt = Runtime.getRuntime();
							rt.exec(new String[] { "cmd.exe", "/c",
									"start chrome.exe" });
						}
						if (resultText.equals("close browser")) {
							Runtime rt = Runtime.getRuntime();
							rt.exec(new String[] { "cmd.exe", "/c",
									"taskkill /f /im chrome.exe" });
						}
						// task manager//
						if (resultText.equalsIgnoreCase("open task manager")) {
							Runtime rt = Runtime.getRuntime();
							rt.exec(new String[] { "cmd.exe", "/c",
									"taskmgr.exe" });
						}
						if (resultText.equals("close task manager")) {
							Runtime rt = Runtime.getRuntime();
							rt.exec(new String[] { "cmd.exe", "/c",
									"taskkill /im taskmgr.exe /f" });
						}

						// music//

						if (resultText.equals("open music")) {
							Runtime rt = Runtime.getRuntime();
							rt.exec(new String[] { "cmd.exe", "/c",
									"start wmplayer.exe" });
						}
						if (resultText.equals("close music")) {
							Runtime rt = Runtime.getRuntime();
							rt.exec(new String[] { "cmd.exe", "/c",
									"taskkill /IM wmplayer.exe /f" });
						}

						// excel//

						if (resultText.equals("open excel")) {
							Runtime rt = Runtime.getRuntime();
							rt.exec(new String[] { "cmd.exe", "/c",
									"start excel.exe" });
						}
						if (resultText.equals("close excel")) {
							Runtime rt = Runtime.getRuntime();
							rt.exec(new String[] { "cmd.exe", "/c",
									"taskkill /IM excel.exe /f" });
						}

						// calc//

						if (resultText.equals("open calculator")) {
							Runtime rt = Runtime.getRuntime();
							rt.exec(new String[] { "cmd.exe", "/c",
									"start calc.exe" });
						}

						if (resultText.equals("close calculator")) {
							Runtime rt = Runtime.getRuntime();
							rt.exec(new String[] { "cmd.exe", "/c",
									"taskkill /IM calculator.exe /f" });
						}

						if (resultText.equalsIgnoreCase("start fire wall")) {
							try {
								Runtime rt = Runtime.getRuntime();
								rt.exec("cmd /c start firewall.cpl");

								System.out.println("inside");
							} catch (Exception ae) {
								System.out.println(ae);
							}
						}

						if (resultText.equalsIgnoreCase("close fire wall")) {
							try {
								Process p; // resultText="";
								String status = "status eq Windows Firewall";
								p = Runtime.getRuntime().exec(
										"cmd /c taskkill /f /fi " + status);
								// System.out.println("inside");
							} catch (Exception ae) {
							}
						}
						// ////////////////////////////////////////////////////////////////////////about
						// close /////////////////////
						if (resultText
								.equalsIgnoreCase("speech recognize complete")) {
							try {
								System.out.println("Thanks for using !");
								recognizer.deallocate();
								System.exit(0);
							} catch (Exception ecomp) {
							}
						}

						if (resultText
								.equalsIgnoreCase("speech recognize start")) {
							try {

								recognizer.notify();
								System.out.println("Hello again :-) ");
								System.exit(0);
							} catch (Exception estart) {
							}
						}

						if (resultText.equalsIgnoreCase("stop function")) {
							try {
								// recognizer.wait();
								System.out.println("See you later!");
								System.exit(0);
							} catch (Exception estop) {
							}
						}

						if (resultText.equals("open command")
								|| resultText.equalsIgnoreCase("open promt")) {
							Runtime rt = Runtime.getRuntime();
							rt.exec(new String[] { "cmd.exe", "/c",
									"start start cmd" });
						}
						if (resultText.equals("close command")
								|| resultText.equalsIgnoreCase("close promt")) {
							Runtime rt = Runtime.getRuntime();
							rt.exec(new String[] { "cmd.exe", "/c",
									"cmd /c start taskkill /im cmd.exe /f" });
						}

						// power option
						if (resultText.equals("open power option")) {
							Runtime rt = Runtime.getRuntime();
							rt.exec(new String[] { "cmd.exe", "/c",
									"powercfg.cpl" });
						}
						if (resultText.equals("close power option")) {
							Runtime rt = Runtime.getRuntime();
							rt.exec(new String[] { "cmd.exe", "/c",
									"cmd /c start taskkill /im powercfg.exe /f" });
							System.out.println("inside power option ");
						}


				/*		// control penal
						if (resultText.equals("control")) {
							try {
								Process p;
								p = Runtime.getRuntime().exec("cmd /c control");
							} catch (Exception ae) {
								System.out.println(ae);
							}
							System.out.println("control");
						}
						if (resultText.equals("close control")) {
							Runtime rt = Runtime.getRuntime();
							rt.exec(new String[] { "cmd.exe", "/c",
									"cmd /c start taskkill /im control.exe /f" });
						}

						// face book
						if (resultText.equals("site face book")) {
							try {
								Process p;
								// resultText="";
								p = Runtime.getRuntime().exec(
										"cmd /c start chrome www.facebook.com");
								// System.out.println("inside");
							} catch (Exception ae) {
								System.out.println(ae);
							}
							System.out.println("fb");
						}

						// google
						if (resultText.equals("site go girl")) {
							try {
								Process p;
								// resultText="";
								p = Runtime.getRuntime().exec(
										"cmd /c start chrome www.google.com");
								System.out.println("google");
							} catch (Exception ae) {
								System.out.println(ae);
							}
						}

						else if (resultText.equalsIgnoreCase("site mail")) {
							try {
								Process p;
								// resultText="";
								p = Runtime
										.getRuntime()
										.exec("cmd /c start chrome https://mail.google.com");
								// System.out.println("inside");
							} catch (Exception ae) {
							}
						}*/

						else if (resultText
								.equalsIgnoreCase("open my computer")) {
							try {
								Process p;
								// resultText="";
								p = Runtime.getRuntime().exec(
										"cmd /c start explorer.exe");
								// System.out.println("inside");
							} catch (Exception ae) {
							}
						}

						else if (resultText
								.equalsIgnoreCase("close my computer")) {
							try {
								Process p;
								// resultText="";
								p = Runtime
										.getRuntime()
										.exec("cmd /c start taskkill /f /im explorer.exe && start explorer");
								// System.out.println("inside");
							} catch (Exception ae) {
							}
						}

					}

					// can't here you
					if (resultText.equals("")) {
						System.out.println("I can't hear what you said.\n");
					}

				}
			} else {
				System.out.println("Cannot start microphone.");
				recognizer.deallocate();
				System.exit(1);
			}
		} catch (IOException e) {
			System.err.println("Problem when loading Class: " + e);
			e.printStackTrace();
		} catch (PropertyException e) {
			System.err.println("Problem configuring Class: " + e);
			e.printStackTrace();
		} catch (InstantiationException e) {
			System.err.println("Problem creating Class: " + e);
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new HelloWorld().name(args);
	}

	public void start(String resultText) {

	}
}
