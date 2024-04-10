import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class StudentController {
	private List<Students> studentList;

	public StudentController(String fileName) {
		this.studentList = readStudentsFromXML(fileName);
	}

	public List<Students> getStudentList() {
		return studentList;
	}
	// hàm hiển thị sinh viên ra màn hình
	public void displayStudents() {
		studentList.forEach(student -> System.out.println(student));
	}
	//tính tuổi
	public void calculateStudentAges() {
		studentList.forEach(student -> {
			int age = student.calculateAge();
			System.out.println("Tuổi của sinh viên: " + student.getName() + ": " + age);
		});
	}
	//mã hóa code
	public void encodeBirthDates() {
		studentList.forEach(student -> {
			String encodedDate = student.encodeBirthDate();
			System.out.println("Code mã hóa theo ngày sinh " + student.getName() + " : " + encodedDate);
		});
	}
	//giải mã
	public void decodeBirthDates() {
		studentList.forEach(student -> {
			String decodedDate = student.decodeBirthDate(student.encodeBirthDate());
			System.out.println("Ngày sinh giải mã của " + student.getName() + " theo code: "+student.encodeBirthDate()+" là :" + decodedDate);
		});
	}

	public void checkPrimeBirthYears() {
		studentList.forEach(student -> {
			String isPrime = student.isPrime();
			System.out.println(student.getName() + " có tổng các chữ số ngày sinh có phải là số nguyên tố không: " + isPrime);
		});
	}
	
	// ghi kết quả vào file xml
	public void writeResultsToXML(String fileName) {
	    try {
	        FileWriter writer = new FileWriter(fileName);
	        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
	        writer.write("<results>\n");

	        for (Students student : studentList) {
	            String birthday = student.getBirthday().isEmpty() ? "Missing Birthday" : student.getBirthday();
	            
	            writer.write("    <student>\n");
	            writer.write("        <name>" + student.getName() + "</name>\n");
	            writer.write("        <age>" + student.calculateAge() + "</age>\n");
	            writer.write("        <sum>" + student.calculateBirthdayDigitsSum() + "</sum>\n");
	            writer.write("        <isDigit>" + student.isPrime() + "</isDigit>\n");
	            writer.write("    </student>\n");
	        }
	        writer.write("</results>\n");
	        writer.close();
	        System.out.println("Kết quả đã được ghi vào file:  " + fileName);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	//dọc dữ liệu từ file
	private List<Students> readStudentsFromXML(String fileName) {
		List<Students> studentList = new ArrayList<>();
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				System.out.println("File " + fileName + " không tồn tại.");
				return studentList;
			}

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);

			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName("student");

			for (int temp = 0; temp < nodeList.getLength(); temp++) {
				Node node = nodeList.item(temp);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
					String name = element.getElementsByTagName("name").item(0).getTextContent();
					String address = element.getElementsByTagName("address").item(0).getTextContent();
					String birthday = element.getElementsByTagName("birthday").item(0).getTextContent();
					// Kiểm tra xem sinh viên có đủ thông tin không
					boolean hasAllInfo = !(name.isEmpty() || address.isEmpty() || birthday.isEmpty());

					// Nếu sinh viên không có đủ thông tin
					if (!hasAllInfo) {
						// Kiểm tra từng trường thông tin và chỉ ghi vào thông tin thiếu
						String studentName = name.isEmpty() ? "Thiếu thông tin" : name;
						String studentAddress = address.isEmpty() ? "Thiếu thông tin" : address;
						String studentBirthday = birthday.isEmpty() ? "Thiếu thông tin" : birthday;

						// Tạo đối tượng sinh viên mới và thêm vào danh sách
						Students emptyStudent = new Students(id, studentName, studentAddress, studentBirthday);
						studentList.add(emptyStudent);
					} else {
						// Nếu có đủ thông tin, thêm thông tin sinh viên vào danh sách
						Students student = new Students(id, name, address, birthday);
						studentList.add(student);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return studentList;
	}


}