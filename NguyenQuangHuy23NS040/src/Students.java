import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Students {
	private int id;
	private String name;
	private String address;
	private String birthday;

	public Students(int id, String name, String address, String birthday) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.birthday = birthday;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
    //hàm tính tuổi
	public int calculateAge() {
		int birthYear = Integer.parseInt(birthday.split("-")[0]);
		int currentYear = java.time.LocalDate.now().getYear();
		return currentYear - birthYear;
	}
    //hàm tính ngày
	public long calculateDaysSinceBirth() {
		java.time.LocalDate birthDate = java.time.LocalDate.parse(birthday);
		java.time.LocalDate currentDate = java.time.LocalDate.now();
		return java.time.temporal.ChronoUnit.DAYS.between(birthDate, currentDate);
	}
    //hàm tính năm
	public long calculateYearsSinceBirth() {
		LocalDate birthDate = LocalDate.parse(birthday);
		LocalDate currentDate = LocalDate.now();
		return ChronoUnit.YEARS.between(birthDate, currentDate);
	}
    //hàm tính tháng
	public int calculateMonthsSinceBirth() {
		LocalDate birthDate = LocalDate.parse(birthday);
		LocalDate currentDate = LocalDate.now();
		Period period = Period.between(birthDate, currentDate);
		return period.getMonths();
	}
	//hàm mã hóa code
	public String encodeBirthDate() {
		String encodedDate = Base64.getEncoder().encodeToString(birthday.getBytes());
		return encodedDate;
	}
	
	//giải mã
	public String decodeBirthDate(String encodedDate) {
	    byte[] decodedBytes = Base64.getDecoder().decode(encodedDate);
	    return new String(decodedBytes);
	}
	//hàm tính tổng các chữ số của ngày sinh
	public int calculateBirthdayDigitsSum() {
		String[] parts = birthday.split("-");
		int sum = 0;
		for (String part : parts) {
			for (int i = 0; i < part.length(); i++) {
				char c = part.charAt(i);
				if (Character.isDigit(c)) {
					sum += Character.getNumericValue(c);
				}
			}
		}
		return sum;
	}
	//hàm kiểm tra ngày sinh có phải số nguyên tố
	public String isPrime() {
		int number = calculateBirthdayDigitsSum();
		if (number <= 1) {
			return "Không";
		}
		for (int i = 2; i <= Math.sqrt(number); i++) {
			if (number % i == 0) {
				return "Không";
			}
		}
		return "Có";
	}

	@Override
	public String toString() {
		return "Students{" +
				"id=" + id +
				", name='" + name + '\'' +
				", address='" + address + '\'' +
				", birthday='" + birthday + '\'' +
				'}';
	}
}
