package Model;

public class AlarmFullList {

	private String CommCtrlNo;
	private String CommCtrlName;
	private String FieldCtrlNo;
	private String FieldCtrlName;
	private String BlockNo;
	private String InPointDNo;
	private String InPointDName;
	private String InPointDType;
	private String InPointANo;
	private String InPointAName;
	private String InPointAType;
	private String AlarmNo;
	private String ActiveText;
	private String InActiveText;
	private String Piroirty;
	private String Name;
	private String File;	
	
	@Override
	public String toString() {
		return "AlarmFullList [CommCtrlNo=" + CommCtrlNo + ", CommCtrlName=" +CommCtrlName + 
				", FieldCtrlNo=" + FieldCtrlNo + ", FieldCtrlName=" + FieldCtrlName + 
				", BlockNo=" + BlockNo + 
				", InPointDNo=" + InPointDNo + ", InPointDName=" + InPointDName+ 
				", InPointANo=" + InPointANo + ", InPointAName=" + InPointAName+ 
				", AlarmNo=" + AlarmNo + ", ActiveText=" + ActiveText + ", InActiveText=" + InActiveText
				+  ", Piroirty=" + Piroirty + ", Name=" + Name  + ", File=" + File +"]";
	}

	public String getCommCtrlNo() {
		return CommCtrlNo;
	}

	public void setCommCtrlNo(String commCtrlNo) {
		CommCtrlNo = commCtrlNo;
	}

	public String getCommCtrlName() {
		return CommCtrlName;
	}

	public void setCommCtrlName(String commCtrlName) {
		CommCtrlName = commCtrlName;
	}

	public String getFieldCtrlNo() {
		return FieldCtrlNo;
	}

	public void setFieldCtrlNo(String fieldCtrlNo) {
		FieldCtrlNo = fieldCtrlNo;
	}

	public String getFieldCtrlName() {
		return FieldCtrlName;
	}

	public void setFieldCtrlName(String fieldCtrlName) {
		FieldCtrlName = fieldCtrlName;
	}

	public String getBlockNo() {
		return BlockNo;
	}

	public void setBlockNo(String blockNo) {
		BlockNo = blockNo;
	}

	public String getInPointDNo() {
		return InPointDNo;
	}

	public void setInPointDNo(String inPointDNo) {
		InPointDNo = inPointDNo;
	}

	public String getInPointDName() {
		return InPointDName;
	}

	public void setInPointDName(String inPointDName) {
		InPointDName = inPointDName;
	}

	public String getInPointDType() {
		return InPointDType;
	}

	public void setInPointDType(String inPointDType) {
		InPointDType = inPointDType;
	}

	public String getInPointANo() {
		return InPointANo;
	}

	public void setInPointANo(String inPointANo) {
		InPointANo = inPointANo;
	}

	public String getInPointAName() {
		return InPointAName;
	}

	public void setInPointAName(String inPointAName) {
		InPointAName = inPointAName;
	}

	public String getInPointAType() {
		return InPointAType;
	}

	public void setInPointAType(String inPointAType) {
		InPointAType = inPointAType;
	}

	public String getAlarmNo() {
		return AlarmNo;
	}

	public void setAlarmNo(String alarmNo) {
		AlarmNo = alarmNo;
	}

	public String getActiveText() {
		return ActiveText;
	}

	public void setActiveText(String activeText) {
		ActiveText = activeText;
	}

	public String getInActiveText() {
		return InActiveText;
	}

	public void setInActiveText(String inActiveText) {
		InActiveText = inActiveText;
	}

	public String getPiroirty() {
		return Piroirty;
	}

	public void setPiroirty(String piroirty) {
		Piroirty = piroirty;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getFile() {
		return File;
	}

	public void setFile(String file) {
		File = file;
	}

	
}
