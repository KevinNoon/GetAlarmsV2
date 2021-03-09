package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GetFNamesAndAssocate {

	public static ArrayList<AlarmFullList> AssociationData(String associationXML, String SiteINI) {
		ArrayList<AlarmFullList> AssList = new ArrayList<AlarmFullList>();
			
		AssList = GetAssociations(associationXML);

		GetControllerNames(AssList, SiteINI);

		for (int i = AssList.size() - 1; i >= 0; i--) {
			if ((AssList.get(i).getCommCtrlName() == null)) AssList.remove(i);
		}


//		for (int i = 0; i < AssList.size(); i++) {
//			System.out.println(AssList.get(i));
//		}
		return AssList;
	}

	private static void GetControllerNames(ArrayList<AlarmFullList> AssList, String SiteINI) {
		BufferedReader alarmReader = null;
		try {
			alarmReader = new BufferedReader(new FileReader(SiteINI));
			String lineContentsA, CommsName = "", FieldName = "";
			String CommsNo = "", FieldNo= "";

			try {
				while ((lineContentsA = alarmReader.readLine()) != null) {
					if (lineContentsA.length()> 2){
					alarmReader.mark(10000);
					if (lineContentsA.substring(0, 3).equals("[OS")) {
						CommsNo = lineContentsA.substring(3, lineContentsA.indexOf("]"));

						while ((lineContentsA = alarmReader.readLine()) != null) {

							if (lineContentsA.indexOf("]") > 0) {
								alarmReader.reset();
								break;
							}						

							if (lineContentsA.contains("Name")) {
								CommsName = lineContentsA.substring(5);
							}
							if (lineContentsA.contains("UC16_") && (!lineContentsA.contains("UC16_B"))) {
								FieldNo = lineContentsA.substring(5, lineContentsA.indexOf("="));
								FieldName = lineContentsA.substring(lineContentsA.indexOf("=") + 1);
								AlarmFullList Ass = null;
								Iterator<AlarmFullList> iter = AssList.iterator();
								while (iter.hasNext()) {
									Ass = iter.next();
									if ((Ass.getCommCtrlNo().equals(CommsNo)) && ((Ass.getFieldCtrlNo().equals(FieldNo)))) {
										Ass.setCommCtrlName(CommsName);
										Ass.setFieldCtrlName(FieldName);
										iter.remove();
										AssList.add(Ass);
										break;
									}
								}  
								
							} 
						}

					}
				}}

			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Please enter a vaild path!", "Invaild Path GNA",
						JOptionPane.WARNING_MESSAGE);
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Please enter a vaild path!", "Invaild Path GNA2",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private static ArrayList<AlarmFullList> GetAssociations(String fileName) {
		ArrayList<AlarmFullList> AssList = new ArrayList<AlarmFullList>();
		try {

			// Make the document a URL so relative DTD works.
			String uri = "file:" + new File(fileName).getAbsolutePath();
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			org.w3c.dom.Document doc = builder.parse(uri);
			String CommsNo = "";
			String FieldNo = "";
			doRecursive(doc, AssList, CommsNo, FieldNo);
		} catch (Exception ex) {
		}
		return AssList;
	}

	/* Process all the nodes, recursively. */
	protected static void doRecursive(Node p, ArrayList<AlarmFullList> AssList, String CommsNo, String FieldNo) {
		if (p == null) {
			return;
		}
		NodeList nodes = p.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node n = nodes.item(i);
			if (n == null) {
				continue;
			}
			doNode(n, AssList, CommsNo, FieldNo);
		}
	}

	protected static void doNode(Node n, ArrayList<AlarmFullList> AssList, String CommsNo, String FieldNo) {
		switch (n.getNodeType()) {
		case Node.ELEMENT_NODE:
			if (n.getNodeName().equals("comms_controller")) {
				CommsNo = n.getAttributes().item(0).toString();
				CommsNo = CommsNo.substring(9, CommsNo.lastIndexOf("\""));
			}

			if (n.getNodeName().equals("field_controller")) {
				FieldNo = n.getAttributes().item(0).toString();
				FieldNo = FieldNo.substring(9, FieldNo.lastIndexOf("\""));
			}

			doRecursive(n, AssList, CommsNo, FieldNo);
			break;
		case Node.TEXT_NODE:
			AlarmFullList Ass = new AlarmFullList();

			Ass.setCommCtrlNo(CommsNo);
			Ass.setFieldCtrlNo(FieldNo);
			Ass.setFile(n.getNodeValue());
			if ((Ass.getCommCtrlNo().length() > 0) &&(Ass.getFieldCtrlNo().length() > 0) ) AssList.add(Ass);
			break;

		}
	}
}