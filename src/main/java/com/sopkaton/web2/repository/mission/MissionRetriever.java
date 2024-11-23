package com.sopkaton.web2.repository.mission;

import com.sopkaton.web2.common.api.CustomException;
import com.sopkaton.web2.common.api.ErrorCode;
import com.sopkaton.web2.common.core.MissionNotFoundException;
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
        return missionRepository.findById(randomMissionId).orElseThrow(() -> new CustomException(
                ErrorCode.MISSION_NOT_FOUND));
    }
}
