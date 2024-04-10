import java.io.FileWriter;
import java.io.IOException;

public class CreateFileXML {
    public static void main(String[] args) {
        // Tạo nội dung XML
        String xmlContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        xmlContent += "<students>\n";
        xmlContent += "    <student>\n";
        xmlContent += "        <id>3</id>\n";
        xmlContent += "        <name>Nguyễn Quang Huy</name>\n";
        xmlContent += "        <address>Tam Kỳ,Đà Nẵng</address>\n";
        // năm sinh là số nguyên tố
        //tổng ==7
        xmlContent += "        <birthday>2003-01-01</birthday>\n";
        xmlContent += "    </student>\n";
        xmlContent += "    <student>\n";
        xmlContent += "        <id>4</id>\n";
        xmlContent += "        <name>Nguyễn Văn B</name>\n";
        xmlContent += "        <address>Hà Nội</address>\n";
        // năm sinh không phải số nguyên tố
        //tổng ==6
        xmlContent += "        <birthday>2000-01-03</birthday>\n";
        xmlContent += "    </student>\n";

        // Sinh viên thiếu thông tin  
        xmlContent += "    <student>\n";
        xmlContent += "        <id>5</id>\n";
        xmlContent += "        <name>Nguyễn Quang Huy</name>\n";
        xmlContent += "        <address></address>\n";
    
        xmlContent += "        <birthday>2005-04-03</birthday>\n";
        xmlContent += "    </student>\n";
        xmlContent += "</students>";

        // Tên file XML
        String fileName = "students.xml";

        // Ghi nội dung XML vào file
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(xmlContent);
            writer.close();
            System.out.println("File XML đã được tạo thành công.");
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi tạo file XML: " + e.getMessage());
        }
    }
}
