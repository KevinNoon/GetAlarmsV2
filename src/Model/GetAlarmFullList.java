package Model;

import java.util.ArrayList;

public class GetAlarmFullList {
	
	public static ArrayList<AlarmFullList> GetAlarmFull(String SitePath){

		ArrayList<AlarmFullList> alarmFullListArray = new ArrayList<AlarmFullList>();
		ArrayList<AlarmFullList> afl = new ArrayList<AlarmFullList>();

	alarmFullListArray =  GetFNamesAndAssocate.AssociationData(SitePath + "\\System\\associations.xml",SitePath + "\\System\\site.ini");
	String stratName,strategyDir,alarmName,DBName = "";


	for (int a=0;a < alarmFullListArray.size();a++){

		strategyDir = "00" + alarmFullListArray.get(a).getCommCtrlNo();
		strategyDir = strategyDir.substring(strategyDir.length()-3);


		stratName = SitePath + "\\STRAT5\\" + strategyDir + "\\" + 
				alarmFullListArray.get(a).getFile();
		alarmName = SitePath + "\\DBASE\\" + alarmFullListArray.get(a).getCommCtrlNo() + "\\" +
				"ALARMSTR.TXT";
		
		DBName = SitePath + "\\DBASE\\" + alarmFullListArray.get(a).getCommCtrlNo() + "\\" +
   				"UC16_" + alarmFullListArray.get(a).getFieldCtrlNo();	 
		ArrayList<AlarmFullList> Alarms = new ArrayList<AlarmFullList>();

//		System.out.println(alarmName + " " + stratName + " " + DBName + " " + alarmFullListArray.get(a).getCommCtrlNo());
//		System.out.println(alarmFullListArray.get(a).getCommCtrlName() +" " +alarmFullListArray.get(a).getFieldCtrlNo());
//		System.out.println(alarmFullListArray.get(a).getFieldCtrlName());

		Alarms = GetAlarms.GetAlarmsValues(alarmName, stratName,DBName,alarmFullListArray.get(a).getCommCtrlNo()
				,alarmFullListArray.get(a).getCommCtrlName(),alarmFullListArray.get(a).getFieldCtrlNo()
				,alarmFullListArray.get(a).getFieldCtrlName());

		afl.addAll(Alarms);
	}	
	return afl;
	}
}
