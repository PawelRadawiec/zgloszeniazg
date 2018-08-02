package com.info.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.info.model.Roles;
import com.info.model.TeamLeader;
import com.info.repository.TeamLeaderRepository;

@Service("teamLeaderService")
public class TeamLeaderServiceImpl implements TeamLeaderService {

	@Autowired
	private TeamLeaderRepository teamLeaderRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public TeamLeader findTeamLeaderByEmail(String email) {
		return null;
	}

	@Override
	public boolean userExist(TeamLeader teamLeader) {
		TeamLeader existTeamLeader = teamLeaderRepository.findByEmail(teamLeader.getEmail());
		if (existTeamLeader == null)
			return false;
		if (teamLeader.getEmail().equals(existTeamLeader.getEmail())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void save(TeamLeader teamLeader) {
		if (teamLeader != null) {
			Date date = new Date();
			SimpleDateFormat currentDate = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
			teamLeader.setRole(Roles.TEAM_LEADER.toString());
			teamLeader.setActive(1);
			teamLeader.setDate(currentDate.format(date));
			teamLeader.setPassword(passwordEncoder.encode(teamLeader.getPassword()));
			teamLeaderRepository.save(teamLeader);
		}
	}

	@Override
	public TeamLeader findByEmail(String email) {
		return teamLeaderRepository.findByEmail(email);
	}

	@Override
	public String helloTeamLeader() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		TeamLeader teamLeaderFromSession = findByEmail(auth.getName());
		return teamLeaderFromSession.getFirstName();
	}

}
