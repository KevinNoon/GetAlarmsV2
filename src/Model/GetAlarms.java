package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

// This will return a list of alarms in a strategy with the following information
// Alarm Strings (Active and Inactive),  Alarm Block Number, Alarm Number, 
// Digital Input Number, Analogue Input Number, Alarm Name

public class GetAlarms {

	public static ArrayList<AlarmFullList> GetAlarmsValues(String AlarmTxt, String S32File, String DBFile,
			String CommsNo, String CommsName, String FieldNo, String FieldName) {
		ArrayList<AlarmFullList> AlarmsList = new ArrayList<AlarmFullList>();
		TreeMap<Integer, String> AlarmsString = new TreeMap<Integer, String>();

		Map<Integer, String> names = new HashMap<Integer, String>();
		names = GetPointName.readDBFileArray(DBFile);

		File file = new File(AlarmTxt);
		if (file.exists()) {
			BufferedReader alarmReader = null;
			try {
				alarmReader = new BufferedReader(new FileReader(AlarmTxt));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			String lineContentsA, AlarmString;
			Integer AlarmNo;
			AlarmsString.clear();

			try {
				while ((lineContentsA = alarmReader.readLine()) != null) {
					if (lineContentsA.length() > 2) {
					AlarmNo = Integer.parseInt(lineContentsA.substring(0, lineContentsA.indexOf((char) 9)).trim());
					AlarmString = lineContentsA.substring(lineContentsA.indexOf((char) 9) + 1);
					AlarmsString.put(AlarmNo, AlarmString);
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		BufferedReader bReader = null;
		try {

            bReader = new BufferedReader(new FileReader(S32File));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String lineContents;
		String AlarmName, AlarmNameASCII, Active, InActive, PointType;
		Integer AlarmStringNo, LoopCount = 0, PointNo;


        try {
			while ((lineContents = bReader.readLine()) != null) {
                System.out.println(lineContents);
//                if ((LoopCount == 0) && (!lineContents.substring(0, 3).contains("000")))
//					break;
				LoopCount++;
				if (lineContents.length() > 20) {
					if (S32File.toLowerCase().contains(".s32")) {
                        System.out.println(lineContents.substring(12,15) + " " + lineContents.substring(16,19));
                        if (lineContents.substring(12, 15).equals("041")) {
							AlarmFullList Alm = new AlarmFullList();
							Alm.setCommCtrlNo(CommsNo);
							Alm.setCommCtrlName(CommsName);
							Alm.setFieldCtrlNo(FieldNo);
							Alm.setFieldCtrlName(FieldName);
							Alm.setBlockNo(lineContents.substring(4, 7));

							PointType = (lineContents.substring(16, 19));
							if (PointType.equals("028"))
								PointType = "25";
							else
								PointType = "2";
							Alm.setInPointDType(PointType);

							PointNo = Integer.parseInt(lineContents.substring(20, 23));
							if (PointType.equals("25"))
								PointNo++;
							Alm.setInPointDNo(PointNo + "");

							Integer pointNo = Integer.parseInt(Alm.getInPointDNo());
							Integer pointType = Integer.parseInt(Alm.getInPointDType());
							String pointName = "";

							if (names.containsKey(pointType * 10000 + pointNo))
								pointName = names.get(pointType * 10000 + pointNo);
							Alm.setInPointDName(pointName);

							PointType = (lineContents.substring(32, 35));
							if (PointType.equals("028"))
								PointType = "24";
							else
								PointType = "1";

							Alm.setInPointAType(PointType);

							PointNo = Integer.parseInt(lineContents.substring(36, 39));
							if (PointType.equals("24"))
								PointNo++;
							Alm.setInPointANo(PointNo + "");

							Integer.parseInt(Alm.getInPointANo());
							Integer.parseInt(Alm.getInPointAType());

							pointName = "";
							if (names.containsKey(pointType * 10000 + pointNo))
								pointName = names.get(pointType * 10000 + pointNo);
							Alm.setInPointAName(pointName);
							Alm.setAlarmNo(lineContents.substring(28, 31));
							AlarmStringNo = Integer.parseInt(lineContents.substring(52, 55));
							if (AlarmsString.containsKey(AlarmStringNo)) {
								Active = AlarmsString.get(AlarmStringNo);
							} else {
								Active = "";
							}

							Alm.setActiveText(Active);
							AlarmStringNo = Integer.parseInt(lineContents.substring(60, 63));

							if (AlarmsString.containsKey(AlarmStringNo)) {
								InActive = AlarmsString.get(AlarmStringNo);
							} else {
								InActive = "";
							}

							Alm.setInActiveText(InActive);
							Alm.setPiroirty(lineContents.substring(64, 67));
							if (lineContents.length() > 68) {
								AlarmNameASCII = lineContents.substring(68);
								AlarmNameASCII = AlarmNameASCII.substring(0, AlarmNameASCII.indexOf("000"));
								AlarmName = "";
								while (AlarmNameASCII.length() > 2) {
									char c = (char) Integer.parseInt(AlarmNameASCII.substring(0, 3));
									AlarmNameASCII = AlarmNameASCII.substring(4);
									AlarmName = AlarmName + c;
								}
								Alm.setName(AlarmName);
							} else {
								Alm.setName("");
							}
							AlarmsList.add(Alm);
						}
					} else {
						{
							if (lineContents.substring(4, 7).equals("041")) {
								AlarmFullList Alm = new AlarmFullList();
								Alm.setCommCtrlNo(CommsNo);
								Alm.setCommCtrlName(CommsName);
								Alm.setFieldCtrlNo(FieldNo);
								Alm.setFieldCtrlName(FieldName);
								Alm.setBlockNo(lineContents.substring(0, lineContents.indexOf((char) 9)));
								lineContents = lineContents.substring(lineContents.indexOf((char) 9) + 1);
								lineContents = lineContents.substring(lineContents.indexOf((char) 9) + 1);
								Alm.setInPointDNo(lineContents.substring(0, lineContents.indexOf((char) 9)));
								if (Integer.parseInt(Alm.getInPointDNo()) > 16) {
									Alm.setInPointDType("02");
								} else {
									Alm.setInPointDType("19");
								}
								Integer pointNo = Integer.parseInt(Alm.getInPointDNo());
								Integer pointType = Integer.parseInt(Alm.getInPointDType());
								String pointName = "";
								if (names.containsKey(pointType * 10000 + pointNo))
									pointName = names.get(pointType * 10000 + pointNo);
								Alm.setInPointDName(pointName);
								lineContents = lineContents.substring(lineContents.indexOf((char) 9) + 1);
								Alm.setPiroirty(lineContents.substring(0, lineContents.indexOf((char) 9)));
								lineContents = lineContents.substring(lineContents.indexOf((char) 9) + 1);
								Alm.setAlarmNo(lineContents.substring(0, lineContents.indexOf((char) 9)));
								lineContents = lineContents.substring(lineContents.indexOf((char) 9) + 1);
								Alm.setInPointANo(lineContents.substring(0, lineContents.indexOf((char) 9)));
								if (Integer.parseInt(Alm.getInPointANo()) > 16) {
									Alm.setInPointAType("01");
								} else {
									Alm.setInPointAType("18");
								}
								pointNo = Integer.parseInt(Alm.getInPointANo());
								pointType = Integer.parseInt(Alm.getInPointAType());
								pointName = "";
								if (names.containsKey(pointType * 10000 + pointNo))
									pointName = names.get(pointType * 10000 + pointNo);
								Alm.setInPointAName(pointName);
								lineContents = lineContents.substring(lineContents.indexOf((char) 9) + 1);
								AlarmName = "";
								for (int x = 0; x < 8; x++) {
									AlarmNameASCII = lineContents.substring(0, lineContents.indexOf((char) 9) + 1);
									AlarmNameASCII = AlarmNameASCII.trim();
									if (Integer.parseInt(AlarmNameASCII) > 31) {
										char c = (char) Integer.parseInt(AlarmNameASCII);
										AlarmName = AlarmName + c;
									}
									lineContents = lineContents.substring(lineContents.indexOf((char) 9) + 1);
								}
								Alm.setName(AlarmName);
								AlarmStringNo = Integer
										.parseInt(lineContents.substring(0, lineContents.indexOf((char) 9)));
								if (AlarmsString.containsKey(AlarmStringNo)) {
									Active = AlarmsString.get(AlarmStringNo);
								} else {
									Active = "";
								}
								Alm.setActiveText(Active);
								lineContents = lineContents.substring(lineContents.indexOf((char) 9) + 1);
								AlarmStringNo = Integer
										.parseInt(lineContents.substring(0, lineContents.indexOf((char) 9)));
								if (AlarmsString.containsKey(AlarmStringNo)) {
									InActive = AlarmsString.get(AlarmStringNo);
								} else {
									InActive = "";
								}
								Alm.setInActiveText(InActive);
								AlarmsList.add(Alm);
							}
						}
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return AlarmsList;
	}
}
