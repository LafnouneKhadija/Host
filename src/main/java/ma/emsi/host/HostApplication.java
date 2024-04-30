package ma.emsi.host;

import ma.emsi.host.entities.patients;
import ma.emsi.host.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class HostApplication implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {

        SpringApplication.run(HostApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //pour enregistrer les patients
        //methode0:
        patientRepository.save(new patients(null,"DDDD",new Date(),false,130));
        patientRepository.save(new patients(null,"FFFF",new Date(),true,120));
        patientRepository.save(new patients(null,"HHHH",new Date(),false,150));
        
      //methode1: constructeur sans parametre
        //patients patient=new patients();
        //patient.setId(null);
        //patient.setNom("AAA");
        //patient.setDateNaissance(new Date());
        //patient.setMalade(false);
        //patient.setScore(23);

        //methode2: constructeur avec parametre
       // patients patient2=new patients(null,"BBBB",new Date(),false,123);

        //methode3: en utilisant builder
        //patients patient3= patients.builder()
          //      .nom("CCCC")
            //    .dateNaissance(new Date())
              //  .score(49)
                //.malade(true)
                //.build();



    }
}
