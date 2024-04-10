public class Main {
    public static void main(String[] args) {
        StudentController controller = new StudentController("students.xml");
        //thread 1 đọc dữ liệu sinh viên
        Thread thread1 = new Thread(() -> {
            controller.displayStudents();
        });

        Thread thread2 = new Thread(() -> {
            try {
                // Chờ thread1 hoàn thành trước khi tính toán tuổi và mã hóa ngày sinh
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            controller.calculateStudentAges();
            controller.encodeBirthDates();
        });

        Thread thread3 = new Thread(() -> {
            try {
                // Chờ thread1 hoàn thành trước khi kiểm tra năm sinh là số nguyên tố
                thread1.join();
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            controller.checkPrimeBirthYears();
        });
        
        Thread thread4 = new Thread(() -> {
            try {
                // Chờ thread2 hoàn thành mã hóa
                thread2.join();
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            controller.decodeBirthDates();
            });

        // Khởi chạy các thread tính toán
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        
        // Chờ các thread tính toán hoàn thành
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Ghi kết quả vào file sau khi tất cả các thread đã hoàn thành
        controller.writeResultsToXML("kq.xml");
    }
}
