package infsus.SGF.Service.Impl;

import infsus.SGF.DTO.CreateNotificationRequest;
import infsus.SGF.Model.Obavijest;
import infsus.SGF.Repository.ObavijestRepository;
import infsus.SGF.Service.ObavijestService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ObavijestServiceImplementation implements ObavijestService {

    private final ObavijestRepository obavijestRepository;

    public ObavijestServiceImplementation(ObavijestRepository obavijestRepository) {
        this.obavijestRepository = obavijestRepository;
    }

    public Obavijest createNotification(CreateNotificationRequest request) {

        Obavijest obavijest = new Obavijest();

        obavijest.setTitle(request.getTitle());
        obavijest.setMessage(request.getMessage());
        obavijest.setTargetGroup(request.getTargetGroup());
        obavijest.setImageUrl(request.getImageUrl());
        obavijest.setCreatedAt(LocalDateTime.now());

        return obavijestRepository.save(obavijest);
    }


    public List<Obavijest> getNotificationsForUserType(
            Integer userType) {

        // posjetitelj
        if (userType == 2) {
            return obavijestRepository.findByTargetGroup(
                    "participants");
        }

        // osoblje
        if (userType == 4) {

            List<Obavijest> result =
                    new ArrayList<>();

            result.addAll(
                    obavijestRepository.findByTargetGroup(
                            "participants")
            );

            result.addAll(
                    obavijestRepository.findByTargetGroup(
                            "employees")
            );

            return result;
        }

        // organizator
        if (userType == 3) {

            /*List<Obavijest> result =
                    new ArrayList<>();

            result.addAll(
                    ObavijestRepository.findByTargetGroup(
                            "participants")
            );

            result.addAll(
                    ObavijestRepository.findByTargetGroup(
                            "employees")
            );

            return result;*/
        }

        return Collections.emptyList();
    }
}
