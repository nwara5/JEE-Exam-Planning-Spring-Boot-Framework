package com.Academia.GestionExamen.Controllers;

import org.springframework.http.HttpHeaders;

import com.Academia.GestionExamen.entities.Examen;
import com.Academia.GestionExamen.services.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/api/file")
public class FileController {


    @Autowired
     private ExamenService examenService;

    @PostMapping("/procesVerbaux/{examenId}")
    public String singleFileUploadPV(@PathVariable("examenId") Long examenId,
                                   @RequestParam("filePV") MultipartFile file,
                                   RedirectAttributes redirectAttributes,
                                   Model model) {
        model.addAttribute("examen", examenService.findExamById(examenId));
        String UPLOAD_DIR = "C:/Users/Nwara/Documents/PVs/";
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/api/examens/papiers/" + examenId;
        }

        try {
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
            Files.write(path, bytes);

            Examen examen = examenService.findExamById(examenId);
            if (examen == null) {
                redirectAttributes.addFlashAttribute("message", "Exam not found");
                return "redirect:/api/examens/papiers/" + examenId;
            }
            try{
            examenService.setPVtoExamen(examen, path.toString());
            }catch (Exception e){
                return "/errors/genericError";
            }
            model.addAttribute("msg", "Vous avez téléchargé avec succès '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
        }
        return "Papier";
    }


    @RequestMapping("/viewPV/{examenId}")
    public ResponseEntity<FileSystemResource> viewPV(@PathVariable("examenId") Long examenId){

        Examen examen = examenService.findExamById(examenId);
        String path = examen.getExamenPV().getPathPV();
        try {
            FileSystemResource file = new FileSystemResource(path);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"");
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(file);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @RequestMapping("/enonces/{examenId}")
    public String singleFileUploadEnonce(@PathVariable("examenId") Long examenId,
                                   @RequestParam("fileEnonce") MultipartFile file,
                                   RedirectAttributes redirectAttributes,
                                   Model model) {
        model.addAttribute("examen", examenService.findExamById(examenId));
        String UPLOAD_DIR = "C:/Users/Nwara/Documents/Enonce/";
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/api/examens/papiers/" + examenId;
        }

        try {
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
            Files.write(path, bytes);

            Examen examen = examenService.findExamById(examenId);
            if (examen == null) {
                redirectAttributes.addFlashAttribute("message", "Exam not found");
                return "redirect:/api/examens/papiers/" + examenId;
            }
            try{
                examenService.setEnoncetoExamen(examen, path.toString());
            }catch (Exception e){
                return "/errors/genericError";
            }
            model.addAttribute("msg", "Vous avez téléchargé avec succès '" + file.getOriginalFilename() + "'");
            return "Papier";
        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
            return "/errors/genericError";
        }

    }

    @RequestMapping("/viewEnonce/{examenId}")
    public ResponseEntity<FileSystemResource> viewEnonce(@PathVariable("examenId") Long examenId){

        Examen examen = examenService.findExamById(examenId);
        String path = examen.getExamenEnonce().getPathEnonce();
        try {
            FileSystemResource file = new FileSystemResource(path);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"");
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(file);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @RequestMapping("/rapports/{examenId}")
    public String singleFileUploadRapport(
            @PathVariable("examenId") Long examenId,
            @RequestParam("fileRapport") MultipartFile file,
            RedirectAttributes redirectAttributes,
            Model model) {

        model.addAttribute("examen", examenService.findExamById(examenId));
        String UPLOAD_DIR = "C:/Users/Nwara/Documents/Rapports";
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload");
            return "redirect:/api/examens/papiers/" + examenId;
        }
        try {
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_DIR + "/" + file.getOriginalFilename());
            Files.write(path, bytes);
            Examen examen = examenService.findExamById(examenId);
            if (examen == null) {
                model.addAttribute("message", "Exam not found");
                return "redirect:/api/examens/papiers/" + examenId;
            }
            try{
                examenService.setRapporttoExamen(examen, path.toString());
            }catch (Exception e){
                return "/errors/genericError";
            }
            model.addAttribute("msg", "Vous avez chargé avec succès '" + file.getOriginalFilename() + "'");
            return "Papier";
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("msg", "File upload failed: " + e.getMessage());
            return "/errors/genericError";
        }

    }


    @RequestMapping("/viewRapport/{examenId}")
    public ResponseEntity<FileSystemResource> viewRapport(@PathVariable("examenId") Long examenId){
        Examen examen = examenService.findExamById(examenId);
        String path = examen.getExamenRapport().getPathRapport();
        try {
            FileSystemResource file = new FileSystemResource(path);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"");
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(file);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}



