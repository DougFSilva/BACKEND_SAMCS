package com.douglas.SAMA.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.SAMA.DTO.AlarmDTO;
import com.douglas.SAMA.model.Alarm;
import com.douglas.SAMA.model.Aluno;
import com.douglas.SAMA.repository.AlarmRepository;
import com.douglas.SAMA.service.Exception.ObjectNotFoundException;

@Service
public class AlarmService {

	@Autowired
	private AlarmRepository repository;

	public Alarm create(Alarm alarm) {
		Alarm newAlarm = repository.save(alarm);
		return newAlarm;
	}

	public List<AlarmDTO> findAll() {
		List<AlarmDTO> alarmesDTO = new ArrayList<>();
		List<Alarm> alarmes = repository.findAll();
		alarmes.forEach(alarme -> {
			alarmesDTO.add(new AlarmDTO(alarme));
		});

		return alarmesDTO;
	}

	public void deleteById(Integer id) {
		findById(id);
		repository.deleteById(id);
		return;
	}

	public void deleteAll() {
		repository.deleteAll();
		return;
	}

	public AlarmDTO updateStatusById(Integer id, boolean status) {
		Alarm alarm = findById(id);
		alarm.setStatus(status);
		repository.save(alarm);
		return new AlarmDTO(alarm);
	}

	public Alarm findById(Integer id) {
		Optional<Alarm> alarme = repository.findById(id);
		return alarme.orElseThrow(
				() -> new ObjectNotFoundException("Alarme com id " + id + " não encontrado na base de dados"));
	}

	public List<Alarm> findAllByAluno(Aluno aluno) {
		return repository.findAllByAluno(aluno);
	}

}
