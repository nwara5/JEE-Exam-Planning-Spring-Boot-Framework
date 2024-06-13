package com.Academia.GestionExamen.services;

import com.Academia.GestionExamen.dao.GroupRepo;
import com.Academia.GestionExamen.entities.Enseignant;
import com.Academia.GestionExamen.entities.Groupe;
import com.Academia.GestionExamen.entities.Surveillant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SurveillantService {

    @PersistenceContext
    public EntityManager entityManager;

    @Autowired
    private GroupRepo groupRepo ;
    @Transactional
    public Set<Surveillant> findSurvNotInEmploiDuTempsDate(String date) {
        String jpql = "SELECT s FROM Surveillant s WHERE NOT EXISTS " +
                "(SELECT e FROM s.empts e WHERE e.date = :date)";
        TypedQuery<Surveillant> query = entityManager.createQuery(jpql, Surveillant.class);
        query.setParameter("date", date);
        return new HashSet<>(query.getResultList());
    }

    @Transactional
    public Set<Surveillant> findSurvNotInEmploiDuTempsDateGrp(String date, String grp, int seuil, int combien) {
        String jpql = "SELECT s FROM Surveillant s " +
                "JOIN s.Groupes g " +
                "WHERE g.nomGrp = :groupe AND s.nbrSurveillance <= :seuil " +
                "AND NOT EXISTS (" +
                "  SELECT e FROM s.empts e WHERE e.date = :date" +
                ")";
        TypedQuery<Surveillant> query = entityManager.createQuery(jpql, Surveillant.class);
        query.setParameter("groupe", grp);
        query.setParameter("seuil", seuil);
        query.setParameter("date", date);
        query.setMaxResults(combien);
        return new HashSet<>(query.getResultList());
    }

    public Set<Surveillant> filterSurveillants(Set<Surveillant> surveillants) {
        Set<Surveillant> filteredSurveillants = new HashSet<>();
        for (Object surveillant : surveillants) {
            if (surveillant instanceof Surveillant) {
                filteredSurveillants.add((Surveillant)surveillant);
            }
        }
        return filteredSurveillants;
    }


    @Transactional
    public Set<Surveillant> fetch3Rand(String date, int combien, int seuil) {
        Set<Surveillant> surveillants = this.filterSurveillants(this.findSurvNotInEmploiDuTempsDate(date));
        Set<Surveillant> filteredSurveillants = surveillants.stream()
                .filter(surveillant -> surveillant.getNbrSurveillance() <= seuil)
                .collect(Collectors.toSet());
        int limit = Math.min(combien, filteredSurveillants.size());
        Set<Surveillant> selectedSurveillants = filteredSurveillants.stream()
                .limit(limit)
                .collect(Collectors.toSet());
        selectedSurveillants.forEach(surveillant -> surveillant.setNbrSurveillance(surveillant.getNbrSurveillance() + 1));
        return selectedSurveillants;
    }

    @Transactional
    public Set<Surveillant> fetchnGrp(String date, int combien, int seuil, String grp) {
        List<Surveillant> surveillantList = new ArrayList<>(this.filterSurveillants(this.findSurvNotInEmploiDuTempsDateGrp(date, grp, seuil, combien)));
        Collections.shuffle(surveillantList);
        Set<Surveillant> shuffledSet = new LinkedHashSet<>(surveillantList);
        shuffledSet.forEach(surveillant -> surveillant.setNbrSurveillance(surveillant.getNbrSurveillance() + 1));
        return shuffledSet;
    }

}
