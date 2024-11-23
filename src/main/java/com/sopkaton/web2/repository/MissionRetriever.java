package com.sopkaton.web2.repository;

import com.sopkaton.web2.common.MissionNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MissionRetriever {
    private final MissionRepository missionRepository;

    public long getCount() {
        return missionRepository.count();
    }

    public Mission findMissionById(long randomMissionId) {
        return missionRepository.findById(randomMissionId).orElseThrow(MissionNotFoundException::new);
    }
}
