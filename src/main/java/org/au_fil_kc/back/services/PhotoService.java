package org.au_fil_kc.back.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PhotoService {
    private static long idNumber = 1L;

    public synchronized String generateNewId(String type) {
        return type.equals("P") ? "ph" + idNumber++ + "P" : "ph" + idNumber++ + "S";
    }
}
