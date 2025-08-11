import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {
    private String studentId;
    private String name;
    private String grade;

    public Student(String studentId, String name, String grade) {
        this.studentId = studentId;
        this.name = name;
        this.grade = grade;
    }

    // Getters and setters
    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getGrade() { return grade; }

    public void setStudentId(String studentId) { this.studentId = studentId; }
    public void setName(String name) { this.name = name; }
    public void setGrade(String grade) { this.grade = grade; }
}

public class AttendanceRecord {
    private String studentId;
    private LocalDate date;
    private boolean present;

    public AttendanceRecord(String studentId, LocalDate date, boolean present) {
        this.studentId = studentId;
        this.date = date;
        this.present = present;
    }

    // Getters
    public String getStudentId() { return studentId; }
    public LocalDate getDate() { return date; }
    public boolean isPresent() { return present; }
}


public class AttendanceManager {
    private Map<String, Student> students;
    private List<AttendanceRecord> attendanceRecords;

    public AttendanceManager() {
        this.students = new HashMap<>();
        this.attendanceRecords = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.put(student.getStudentId(), student);
    }

    public void markAttendance(String studentId, LocalDate date, boolean present) {
        if (students.containsKey(studentId)) {
            attendanceRecords.add(new AttendanceRecord(studentId, date, present));
            System.out.println("Attendance marked for " + studentId + " on " + date + ": " + (present ? "Present" : "Absent"));
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }

    public List<AttendanceRecord> getAttendanceByStudent(String studentId) {
        List<AttendanceRecord> studentAttendance = new ArrayList<>();
        for (AttendanceRecord record : attendanceRecords) {
            if (record.getStudentId().equals(studentId)) {
                studentAttendance.add(record);
            }
        }
        return studentAttendance;
    }

    // Other methods for reporting, etc.
}


public class SchoolAttendanceSystem {
    public static void main(String[] args) {
        AttendanceManager manager = new AttendanceManager();

        // Add students
        manager.addStudent(new Student("S001", "Alice Smith", "10A"));
        manager.addStudent(new Student("S002", "Bob Johnson", "10A"));

        // Mark attendance
        manager.markAttendance("S001", LocalDate.of(2025, 8, 11), true);
        manager.markAttendance("S002", LocalDate.of(2025, 8, 11), false);
        manager.markAttendance("S001", LocalDate.of(2025, 8, 12), true);

        // View attendance for a student
        System.out.println("\nAttendance for Alice Smith (S001):");
        for (AttendanceRecord record : manager.getAttendanceByStudent("S001")) {
            System.out.println("Date: " + record.getDate() + ", Present: " + record.isPresent());
        }
    }
}