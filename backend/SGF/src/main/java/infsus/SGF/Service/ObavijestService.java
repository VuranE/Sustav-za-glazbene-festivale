package infsus.SGF.Service;

import infsus.SGF.DTO.CreateNotificationRequest;
import infsus.SGF.Model.Obavijest;

import java.util.List;

public interface ObavijestService {

    Obavijest createNotification(CreateNotificationRequest request);

    public List<Obavijest> getNotificationsForUserType(Integer userType);
}
