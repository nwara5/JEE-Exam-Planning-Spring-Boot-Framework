package com.Academia.GestionExamen.Controllers;

import com.Academia.GestionExamen.entities.*;
import com.Academia.GestionExamen.services.*;
import jakarta.validation.Valid;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/api/examens")
public class ExamenController {
    @Autowired
    private ExamenService examenService ;
    @Autowired
    private FiliereService filiereService ;
    @Autowired
    private SessionService sessionService ;
    @Autowired
    private SemestreService semestreService ;
    @Autowired
    private SalleService salleService ;
    @Autowired
    private EpService epService ;
    @Autowired
    private SurveillantService surveillantService;
    @Autowired
    private GroupService groupService ;
    @Autowired
    private AdministrateurService administrateurService;
    @Autowired
    private EnseignantService enseignantService ;


    public ExamenController(ExamenService examenService,EpService epService ,FiliereService filiereService, SessionService sessionService, SalleService salleService) {
        this.examenService = examenService;
        this.epService = epService ;
        this.filiereService = filiereService;
        this.sessionService = sessionService;
        this.salleService = salleService;
    }

    @RequestMapping("/allExams")
    public String allExamens(Model model){
        model.addAttribute("ListExam", examenService.listExamens());
        return "ListExamen" ;
    }


    @RequestMapping("/addExamen")
    public String addExamen(Model model){
        model.addAttribute("examen", new Examen());
        model.addAttribute("semestres", semestreService.findAllSemestres());
        model.addAttribute("sessions", sessionService.findAllSessions());
        model.addAttribute("filieres", filiereService.findAllFilieres());
        model.addAttribute("salless", salleService.findAllSalles());
        model.addAttribute("eps", epService.findAllEps());
        model.addAttribute("enseignants",enseignantService.afficherall());
        return "AjouterExamen";
    }



    @PostMapping("/examForm")
    public String submitForm(@Valid @ModelAttribute("examen") Examen examen, BindingResult bindingResult, Model model) {
        model.addAttribute("examen", new Examen());
        model.addAttribute("semestres", semestreService.findAllSemestres());
        model.addAttribute("sessions", sessionService.findAllSessions());
        model.addAttribute("filieres", filiereService.findAllFilieres());
        model.addAttribute("salless", salleService.findAllSalles());
        model.addAttribute("eps", epService.findAllEps());
        model.addAttribute("enseignants",enseignantService.afficherall());

        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "** Ce champs est obligatoire !");
        }
        try {
            examenService.insertExamen(examen);
        }catch (Exception e){
            return "errors/genericError";
        }
        return "redirect:/api/examens/allExams";
    }

    @RequestMapping("/updateform/{examenId}")
    public String updateExamen(@PathVariable("examenId") Long examenId, Model model) {
        model.addAttribute("examenId", examenId);
        model.addAttribute("examen", examenService.findExamById(examenId));
        return "UpdateForm";
    }

    @RequestMapping("/handleUpdateForm")
    public String handleForm(@RequestParam("dureeReelle") Float dr,
                             @RequestParam("idExam") Long idExam) {
        try {
            examenService.updateExamen(idExam, dr);
        }catch(Exception e){
            return "errors/genericError";
        }
        return "redirect:/api/examens/allExams";
    }

    

    @RequestMapping ("/deleteExamen")
    public String deleteExamen(@RequestParam("idExam") Long idExamen){
        try {
            examenService.deleteExamen(idExamen);
        }catch (Exception e) {
            return "errors/genericError";
        }
        return "redirect:/api/examens/allExams";
    }




    @RequestMapping("/SalleSurveillant/{examenId}")
    public String SalleSurveillant(@PathVariable("examenId") Long examenId, Model model) {
        model.addAttribute("examen", examenService.findExamById(examenId));
        return "SelectionSalles";
    }




    @RequestMapping("/SalleSurveillantRand/{examenId}")
    public String SalleSurveillantRand(@PathVariable("examenId") Long examenId, Model model) {
        model.addAttribute("Salles", salleService.findAllSalles());
        model.addAttribute("choixRand","choix");
        model.addAttribute("examen", examenService.findExamById(examenId));
        return "SelectionSalles";
    }



    @RequestMapping("/submitFormRand/{examenId}")
    public String submitFormRand(@PathVariable("examenId") Long examenId,
                                 @RequestParam("salleInput1") String selectedSalle1,
                                 @RequestParam("salleInput2") String selectedSalle2,
                                 @RequestParam("salleInput3") String selectedSalle3,
                                 @RequestParam("nbrSurv1") int nbrSurv1,
                                 @RequestParam("nbrSurv2") int nbrSurv2,
                                 @RequestParam("nbrSurv3") int nbrSurv3,
                                 Model model){
        Salle salle1 = salleService.getSalleByNom(selectedSalle1);
        Salle salle2 = salleService.getSalleByNom(selectedSalle2);
        Salle salle3 = salleService.getSalleByNom(selectedSalle3);
        Set<Surveillant> surveillantsSalle1 = surveillantService.fetch3Rand(examenService.findExamById(examenId).getDate(),nbrSurv1, 10);
        Set<Surveillant> surveillantsSalle2 = surveillantService.fetch3Rand(examenService.findExamById(examenId).getDate(),nbrSurv2, 10);
        Set<Surveillant> surveillantsSalle3 = surveillantService.fetch3Rand(examenService.findExamById(examenId).getDate(),nbrSurv3, 10);
        if (examenService.areSurveillantsDisjoint(surveillantsSalle1, surveillantsSalle2, surveillantsSalle3)) {
            examenService.affecterSurveillantsRand(examenId, salle1, surveillantsSalle1);
            examenService.affecterSurveillantsRand(examenId, salle2, surveillantsSalle2);
            examenService.affecterSurveillantsRand(examenId, salle3, surveillantsSalle3);
        }
        Set<Administrateur> admisSalles = administrateurService.fetch3AdminAleatoirement(10);
        Set<Salle> salles = new HashSet<>();
        salles.add(salle1);
        salles.add(salle2);
        salles.add(salle3);
        examenService.affecterAdminsASalles(examenId, salles, admisSalles);
        model.addAttribute("affectationsalle","Les salles et les surveillants sont attribués avec succès aux salles d'examen");
        model.addAttribute("examen",examenService.findExamById(examenId));
        return "selectionSalles";
    }


    @RequestMapping("/Consulter/{examenId}")
    public String Consulter(@PathVariable("examenId") Long examenId,
                            Model model){
        model.addAttribute("examen",examenService.findExamById(examenId));
        return "Consulter";
    }



    @RequestMapping("/SalleSurveillantFil/{examenId}")
    public String SalleSurveillantFil(@PathVariable("examenId") Long examenId, Model model) {
        model.addAttribute("choixFil","choix");
        model.addAttribute("Salles", salleService.findAllSalles());
        model.addAttribute("examen", examenService.findExamById(examenId));
        model.addAttribute("Groupes",groupService.findAllGroupes());
        return "SelectionSalles";
    }



    @RequestMapping("/submitFormFil/{examenId}")
    public String submitFormFil(@PathVariable("examenId") Long examenId,
                                 @RequestParam("salleInput4") String selectedSalle1,
                                 @RequestParam("salleInput5") String selectedSalle2,
                                 @RequestParam("salleInput6") String selectedSalle3,
                                 @RequestParam("grpname")String grpname,
                                 @RequestParam("nbrSurv4") int nbrSurv1,
                                 @RequestParam("nbrSurv5") int nbrSurv2,
                                 @RequestParam("nbrSurv6") int nbrSurv3,
                                 @ModelAttribute("examen") Examen examen,
                                Model model){
        Salle salle1 = salleService.getSalleByNom(selectedSalle1);
        Salle salle2 = salleService.getSalleByNom(selectedSalle2);
        Salle salle3 = salleService.getSalleByNom(selectedSalle3);

        Set<Surveillant> surveillantsSalle1 = surveillantService.fetch3Rand(examenService.findExamById(examenId).getDate(), nbrSurv1, 10);
        Set<Surveillant> surveillantsSalle2 = surveillantService.fetch3Rand(examenService.findExamById(examenId).getDate(), nbrSurv2, 10);
        Set<Surveillant> surveillantsSalle3 = surveillantService.fetch3Rand(examenService.findExamById(examenId).getDate(), nbrSurv3, 10);
        examenService.affecterSurveillantsGrp(examenId, salle1, surveillantsSalle1);
        examenService.affecterSurveillantsGrp(examenId, salle2, surveillantsSalle2);
        examenService.affecterSurveillantsGrp(examenId, salle3, surveillantsSalle3);

        Set<Administrateur> admisSalles = administrateurService.fetch3AdminAleatoirement(10);
        Set<Salle> salles = new HashSet<>();
        salles.add(salle1);
        salles.add(salle2);
        salles.add(salle3);
        try {
            examenService.affecterAdminsASalles(examenId, salles, admisSalles);
        }catch (Exception e){
            return "errors/genericError";
        }
        model.addAttribute("affectationsalle","Les salles et les surveillants sont attribués avec succès aux salles d'examen");
        model.addAttribute("examen",examenService.findExamById(examenId));
        return "selectionSalles";
    }



    @RequestMapping("/papiers/{examenId}")
    public String papier(@PathVariable("examenId") Long examenId,
                         Model model){
        model.addAttribute("examen",examenService.findExamById(examenId));
        return "Papier";
    }
}
