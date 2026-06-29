package db;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void createTables() {
            try (Connection conn = DatabaseConnection.getConnection();
                 Statement stmt = conn.createStatement()) {

                //  جدول Role
                stmt.execute("""
                CREATE TABLE IF NOT EXISTS roles (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    name VARCHAR(50) NOT NULL UNIQUE
                );
            """);

                //  جدول Users
                stmt.execute("""
                CREATE TABLE IF NOT EXISTS users (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    username VARCHAR(50) UNIQUE NOT NULL,
                    password VARCHAR(255) NOT NULL,
                    role_name VARCHAR(50),
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (role_id) REFERENCES roles(id)
                );
            """);

                // جدول PatientProfile
                stmt.execute("""
                CREATE TABLE IF NOT EXISTS patient_profiles (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    user_id INT UNIQUE,
                    full_name VARCHAR(100),
                    phone VARCHAR(20),
                    national_code VARCHAR(20),
                    birth_date DATE,
                    FOREIGN KEY (user_id) REFERENCES users(id)
                );
            """);

                // جدول Specialty
                stmt.execute("""
                CREATE TABLE IF NOT EXISTS specialties (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    name VARCHAR(100) NOT NULL
                );
            """);

                // جدول DoctorProfile
                stmt.execute("""
                CREATE TABLE IF NOT EXISTS doctor_profiles (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    user_id INT UNIQUE,
                    specialty_id INT,
                    full_name VARCHAR(100),
                    city VARCHAR(100),
                    phone VARCHAR(20),
                    FOREIGN KEY (user_id) REFERENCES users(id),
                    FOREIGN KEY (specialty_id) REFERENCES specialties(id)
                );
            """);

                // جدول Schedule
                stmt.execute("""
                CREATE TABLE IF NOT EXISTS schedules (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    doctor_id INT,
                    day_of_week VARCHAR(20),
                    start_time TIME,
                    end_time TIME,
                    FOREIGN KEY (doctor_id) REFERENCES doctor_profiles(id)
                );
            """);

                //  جدول Appointment
                stmt.execute("""
                CREATE TABLE IF NOT EXISTS appointments (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    doctor_id INT,
                    patient_id INT,
                    appointment_date DATE,
                    appointment_time TIME,
                    status VARCHAR(20),
                    FOREIGN KEY (doctor_id) REFERENCES doctor_profiles(id),
                    FOREIGN KEY (patient_id) REFERENCES patient_profiles(id)
                );
            """);

                // جلوگیری از رزرو دوباره نوبت
                stmt.execute("""
                ALTER TABLE appointments
                ADD CONSTRAINT unique_appointment
                UNIQUE (doctor_id, appointment_date, appointment_time);
            """);

                // 8. جدول Notification
                stmt.execute("""
                CREATE TABLE IF NOT EXISTS notifications (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    user_id INT,
                    message TEXT,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    is_read BOOLEAN DEFAULT FALSE,
                    FOREIGN KEY (user_id) REFERENCES users(id)
                );
            """);

                System.out.println("جداول با موفقیت ساخته شدند!!");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }