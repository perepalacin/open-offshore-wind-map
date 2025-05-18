package app.services;

import app.domain.PlatformDetails;
import app.repostitories.PlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlatformService {

    private final PlatformRepository platformRepository;

    public List<PlatformDetails> getAll () {
        return platformRepository.getAll();
    }

    public PlatformDetails getById (long platformId) {
        return platformRepository.getById(platformId);
    }


}
