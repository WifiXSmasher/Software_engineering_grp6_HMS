package com.gray.hospital.service;

import com.gray.hospital.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdminReportService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final CabinBookingRepository cabinBookingRepository;
    private final PayslipRepository payslipRepository;

    public AdminReportService(
            AppointmentRepository appointmentRepository,
            PatientRepository patientRepository,
            CabinBookingRepository cabinBookingRepository,
            PayslipRepository payslipRepository){

        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.cabinBookingRepository = cabinBookingRepository;
        this.payslipRepository = payslipRepository;
    }

    public Map<String,Object> generateReport(){

        long totalPatients = patientRepository.count();

        long totalAppointments = appointmentRepository.count();

        long totalCabinBookings = cabinBookingRepository.count();

        BigDecimal appointmentRevenue =
                BigDecimal.valueOf(totalAppointments * 500);

        BigDecimal cabinRevenue =
                BigDecimal.valueOf(totalCabinBookings * 3000);

        BigDecimal totalRevenue =
                appointmentRevenue.add(cabinRevenue);

        long payslips = payslipRepository.count();

        Map<String,Object> report = new HashMap<>();

        report.put("totalPatients", totalPatients);
        report.put("totalAppointments", totalAppointments);
        report.put("totalCabinBookings", totalCabinBookings);
        report.put("totalRevenue", totalRevenue);
        report.put("payslipsGenerated", payslips);

        return report;
    }
}