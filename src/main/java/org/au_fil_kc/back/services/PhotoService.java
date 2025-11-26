package org.au_fil_kc.back.services;

import org.au_fil_kc.back.entities.Prestation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PhotoService {
    private static long idNumber = 1L;

    public synchronized String generateNewId(String type) {
        return switch (type) {
            case "P" -> "ph" + idNumber++ + "PRo";
            case "S" -> "ph" + idNumber++ + "PReS";
            case "C" -> "ph" + idNumber++ + "PReC";
            default -> "ph" + idNumber++;
        };

//        String prefix = type.equals("S") ? "S" : "C";
//        String suffix = type.equals("S") ? "RV" : "RS";
//
//        // Récupère tous les IDs existants de ce type
//        List<String> existingIds = prestationRepository.findAll().stream()
//                .map(Prestation::getId)
//                .filter(id -> id != null && id.startsWith(prefix) && id.endsWith(suffix))
//                .toList();
//
//        // Trouve le chiffre le plus haut (ex: S2RV -> 2)
//        long maxId = existingIds.stream()
//                .mapToLong(id -> {
//                    try {
//                        // On extrait le chiffre entre le préfixe et le suffixe
//                        String numberPart = id.substring(prefix.length(), id.length() - suffix.length());
//                        return Long.parseLong(numberPart);
//                    } catch (NumberFormatException e) {
//                        return 0L;
//                    }
//                })
//                .max()
//                .orElse(0L); // Si aucun service, on commence à 0
//
//        return prefix + (maxId + 1) + suffix;
    }
}
