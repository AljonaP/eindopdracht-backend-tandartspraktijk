package nl.haaientanden.eindopdrachtbackendtandartspraktijk.services;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.PatientDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.PatientInputDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.Patient;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    static int lengthDutchZipCode = 6;

    public static Patient transferToPatient(PatientInputDto dto) {

        var patient = new Patient();
        String zipCode = dto.getZipCode();

        patient.setNamePatient(dto.getNamePatient());
        patient.setSurnamePatient(dto.getSurnamePatient());
        patient.setDob(dto.getDob());
        if (checkPostcode((zipCode)) == true){
            patient.setZipCode(dto.getZipCode());
        }
        patient.setZipCode(dto.getZipCode());
        patient.setHomeNumber(dto.getHomeNumber());
        patient.setEmail(dto.getEmail());
        patient.setPhoneNumber(dto.getPhoneNumber());
        patient.setReimburseByInsurancePercentage(dto.getReimburseByInsurancePercentage());

        return patient;
    }

    public static PatientDto transferToDto(Patient patient) {
        PatientDto dto = new PatientDto();

        String zipCode = dto.getZipCode();

        dto.setNamePatient(patient.getNamePatient());
        dto.setSurnamePatient(patient.getSurnamePatient());
        dto.setDob(patient.getDob());
        if (checkPostcode((zipCode)) == true){
            dto.setZipCode(patient.getZipCode());
        }
        dto.setZipCode(patient.getZipCode());
        dto.setHomeNumber(patient.getHomeNumber());
        dto.setEmail(patient.getEmail());
        dto.setPhoneNumber(patient.getPhoneNumber());
        dto.setReimburseByInsurancePercentage(patient.getReimburseByInsurancePercentage());

        return dto;
    }

    public static boolean checkPostcode(String zipCode) {
        if (zipCode.length() != lengthDutchZipCode || zipCode.charAt(0) == '0') {
            return false;
        }
        if (zipCode.length() == lengthDutchZipCode) {
            for (int i = 0; i < zipCode.length(); i++) {
                if (i < 4 && Character.isLetter(zipCode.charAt(i))) {
                    return false;
                }
                if (i > 3 && Character.isDigit(zipCode.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }
}
