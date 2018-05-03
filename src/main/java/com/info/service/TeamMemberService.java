package com.info.service;

import com.info.model.TeamMember;
import org.springframework.stereotype.Service;

@Service
public interface TeamMemberService {
    void save(TeamMember teamMember);
}
