package org.au_fil_kc.back.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PhotoService {
    private static long idNumber = 1L;

    //TODO ajouté conditionnele pour changer l'id en fonction du produit ou service
    // Si produit id = phPxxx; service id = phSxxx
    public synchronized String generateNewId() {
        String id = "P" + idNumber++ + "HT";
        return id;
    }
}
