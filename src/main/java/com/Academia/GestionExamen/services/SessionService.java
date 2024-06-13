package com.Academia.GestionExamen.services;

import com.Academia.GestionExamen.dao.SessionRepo;
import com.Academia.GestionExamen.entities.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {
    @Autowired
    private SessionRepo sessionRepo ;

    public SessionService(SessionRepo sessionRepo) {
        this.sessionRepo = sessionRepo;
    }

    public List<Session> findAllSessions(){
        return sessionRepo.findAll();
    }

    public Session findByTitreSession(String nomSession){
        return sessionRepo.findByTitreSession(nomSession);
    }
}
