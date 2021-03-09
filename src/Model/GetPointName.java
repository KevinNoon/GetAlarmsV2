package Model;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

final public class GetPointName {
	public static void main(String[] args) {
	//	System.out.println(readDBFileArray("C:\\UnitronUC32\\DS5\\DBASE\\196\\UC16_60"));
		System.out.println(readDBFileArray("C:\\UnitronUC32V7\\APPLUNIT\\DBASE\\1\\UC16_1"));

	/*	try {
			readDBFile("C:\\UnitronUC32\\DS5\\DBASE\\196\\UC16_60", 149, 2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} */
	}

	public static Map<Integer , String> readDBFileArray(String FileName) {
		Map<Integer , String> names = new HashMap<Integer, String>();
		Integer number, lastnumber = 0, lastnumber1 = 0, pointNo = 0, pointCount = 0, pointsRead = 0, pointType = 0; // To
		boolean endOfFile = false, first = true; // End of file flag
		// Open Numbers.dat as a binary file.
		FileInputStream fstream;
		try {
			fstream = new FileInputStream(FileName);
			DataInputStream inputFile = new DataInputStream(fstream);
			// Read data from the file.
			while (!endOfFile) {
				try {
					number = inputFile.readUnsignedByte();
					lastnumber1 = lastnumber;
					lastnumber = number;
					String PointName = "";
					if (((lastnumber1 == 3) && (lastnumber == 128)) || (first)) {
						if (first) {
							inputFile.skipBytes(14);
							pointCount = inputFile.readUnsignedByte();
							inputFile.skipBytes(17);
							first = false;
						}
						if (pointCount == pointsRead)
							break;
						number = inputFile.readUnsignedByte();
						for (int n = 0; n < number; n++) {
							PointName = PointName + (char) inputFile.readUnsignedByte();
						}
						pointNo = inputFile.readUnsignedByte();
						pointNo = pointNo + 256 * (Integer) inputFile.readUnsignedByte();
						inputFile.skipBytes(6);
						pointType = inputFile.readUnsignedByte();
						pointsRead++;
						
						names.put(pointType * 10000 + pointNo, PointName);
					}
				} catch (EOFException e) {

					endOfFile = true;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// Close the file.
			inputFile.close();
			
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return names;
	}

	public static String readDBFile(String FileName, Integer PointNo, Integer PointType) throws IOException {
		Integer number, lastnumber = 0, lastnumber1 = 0, pointNo = 0, pointCount = 0, pointsRead = 0, pointType = 0; // To
																														// hold
																														// a
																														// number
		boolean endOfFile = false, first = true; // End of file flag

		// Open Numbers.dat as a binary file.
		FileInputStream fstream = new FileInputStream(FileName);
		DataInputStream inputFile = new DataInputStream(fstream);

		// Read data from the file.
		while (!endOfFile) {
			try {
				number = inputFile.readUnsignedByte();
				lastnumber1 = lastnumber;
				lastnumber = number;
				String PointName = "";
				if (((lastnumber1 == 3) && (lastnumber == 128)) || (first)) {
					if (first) {
						inputFile.skipBytes(14);
						pointCount = inputFile.readUnsignedByte();
						inputFile.skipBytes(17);
						first = false;
					}
					if (pointCount == pointsRead)
						break;

					number = inputFile.readUnsignedByte();
					for (int n = 0; n < number; n++) {
						PointName = PointName + (char) inputFile.readUnsignedByte();

					}

					pointNo = inputFile.readUnsignedByte();
					pointNo = pointNo + 256 * (Integer) inputFile.readUnsignedByte();

					inputFile.skipBytes(6);
					pointType = inputFile.readUnsignedByte();

					if ((pointNo.equals(PointNo) && (PointType.equals(pointType))))
						return PointName;
					pointsRead++;
				}

			} catch (EOFException e) {

				endOfFile = true;
			}
		}

		// Close the file.
		inputFile.close();
		return "";
	}

}
