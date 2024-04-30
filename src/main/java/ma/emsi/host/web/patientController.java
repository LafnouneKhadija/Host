package ma.emsi.host.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.emsi.host.entities.patients;
import ma.emsi.host.repositories.PatientRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//injection des dependances par constructeur
@AllArgsConstructor
public class patientController {
    private PatientRepository patientRepository;

    //pour faire appel à index
    @GetMapping("/index")
    public String index(Model model, @RequestParam(name= "p",defaultValue ="0" ) Integer page,
                        @RequestParam(name= "s",defaultValue ="4" ) Integer size,
    @RequestParam(name= "keyword",defaultValue ="" ) String kw){
        //declarer une liste de patients
        //List<patients> patientsList=patientRepository.findAll();
        //on stocke la liste dans model
       //model.addAttribute("listePatients",patientsList);
       //Page<patients> pagePatients=patientRepository.findAll(PageRequest.of(page, size));
        Page<patients> pagePatients=patientRepository.findByNomContaining(kw,PageRequest.of(page, size));
        model.addAttribute("listePatients",pagePatients.getContent());
        model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
       //pour faire la pagination on a besoin de stocker le nombre de pages dans model
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",kw);
        return "patient";
    }
    @GetMapping("/delete")
    public String delete(Long id, String keyword, int page){
        patientRepository.deleteById(id);
        //redirection
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/formPatients")
    public String formPatients(Model model){
        model.addAttribute("patients",new patients());
        return "formPatients";
    }

    @PostMapping(path = "/save")
    public String save(Model model, @Valid patients patient, BindingResult bindingResult ,

        @RequestParam(defaultValue = "0")  int page,
        @RequestParam(defaultValue = "") String keyword){
        //test
        if(bindingResult.hasErrors()) {
            return "formPatients" ;
        }
        patientRepository.save(patient);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/editPatient")
    public String editPatient(Model model, Long id, int page, String keyword){
        patients patient=patientRepository.findById(id).orElse(null);
        if(patient==null)throw new RuntimeException("Patient introuvable");

        model.addAttribute("patient",patient);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);


        return "editPatient";
    }
}
