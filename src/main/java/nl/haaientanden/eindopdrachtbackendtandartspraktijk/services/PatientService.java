package nl.haaientanden.eindopdrachtbackendtandartspraktijk.services;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.AppointmentDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.PatientDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.PatientInputDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.exceptions.RecordNotFoundException;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.Appointment;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.Patient;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import static nl.haaientanden.eindopdrachtbackendtandartspraktijk.utils.UtilityMethodes.checkPostcode;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public PatientDto savePatient(PatientInputDto dto) {
        Patient patient = transferToPatient(dto);
        patientRepository.save(patient);

        return transferToDto(patient);
    }

    public List<PatientDto> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        List<PatientDto> dtos = new ArrayList<>();

        for (Patient patient : patients) {
            dtos.add(transferToDto(patient));
        }

        return dtos;
    }

    public PatientDto getPatientById(Long id) {
        if (patientRepository.findById(id).isPresent()) {
            Patient patient = patientRepository.findById(id).get();

            return transferToDto(patient);
        } else {
            throw new RecordNotFoundException("Patient with the used Id isn't found.");
        }
    }

    public PatientDto updatePatient(Long id, PatientInputDto inputDto) {
        if (patientRepository.findById(id).isPresent()) {
            Patient patient = patientRepository.findById(id).get();
            Patient patient1 = transferToPatient(inputDto);
            patient1.setId(patient.getId());

            patientRepository.save(patient1);

            return transferToDto(patient1);
        } else {
            throw new RecordNotFoundException("Patient with the used Id isn't found.");
        }
    }

    public void deletePatientById(@RequestBody Long id) {
        patientRepository.deleteById(id);
    }

    public static Patient transferToPatient(PatientInputDto dto) {
        var patient = new Patient();
        String zipCode = dto.getZipCode();
        String inputPhoneNumber = dto.getPhoneNumber();

        patient.setNamePatient(dto.getNamePatient());
        patient.setSurnamePatient(dto.getSurnamePatient());
        patient.setDob(dto.getDob());
        if (checkPostcode(zipCode)) {
            patient.setZipCode(dto.getZipCode());
        }
        patient.setHomeNumber(dto.getHomeNumber());
        patient.setEmail(dto.getEmail());
        if (validPhoneNumber(inputPhoneNumber)) {
            patient.setPhoneNumber(dto.getPhoneNumber());
        } else {
            throw new RuntimeException("The entered phone number isn't correct. The phone number should: \n begin from '0';\n exist only from digits;\n total quantity of digits should be 10 (including first '0').");
        }
        patient.setReimburseByInsurancePercentage(dto.getReimburseByInsurancePercentage());

        return patient;
    }

    public static PatientDto transferToDto(Patient patient) {
        PatientDto dto = new PatientDto();
        String zipCode = patient.getZipCode();
        String inputPhoneNumber = patient.getPhoneNumber();

        dto.setId(patient.getId());
        dto.setNamePatient(patient.getNamePatient());
        dto.setSurnamePatient(patient.getSurnamePatient());
        dto.setDob(patient.getDob());
        if (checkPostcode(zipCode)) {
            dto.setZipCode(patient.getZipCode());
        }
        dto.setHomeNumber(patient.getHomeNumber());
        dto.setEmail(patient.getEmail());
        if (validPhoneNumber(inputPhoneNumber)) {
            dto.setPhoneNumber(patient.getPhoneNumber());
        } else {
            throw new RuntimeException("The entered phone number isn't correct. The phone number should:\n begin from '0';\n exist only from digits;\n total quantity of digits should be 10 (including first '0').");
        }
        dto.setReimburseByInsurancePercentage(patient.getReimburseByInsurancePercentage());
        if ((patient.isSetAppointment()) && !(patient.getAppointments() == null)) {
            List<Appointment> appointments = patient.getAppointments();
            List<AppointmentDto> appointmentDtoList = AppointmentService.transferAppointmentListToDtoList(appointments);
            dto.setAppointmentDto(appointmentDtoList);
        }

        return dto;
    }

    public static boolean validPhoneNumber(String inputPhoneNumber) {
        return inputPhoneNumber.charAt(0) == '0' && inputPhoneNumber.length() == 10 && inputPhoneNumber.matches("^[0]([0-9]{1,9}$)");
    }
}
