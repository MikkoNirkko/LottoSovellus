package com.example.Lotto.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.Lotto.domain.Lottorivi;
import com.example.Lotto.domain.LottoriviRepository;
import com.example.Lotto.domain.UserRepository;
import com.example.Lotto.domain.lottofunktiot;

@Controller
public class LottoController {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private LottoriviRepository repository;
	@Autowired
	private UserRepository urepository;

	Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	@RequestMapping(value = "/login")
	public String showLogin() {
		return "login";
	}

	// Adminin lisätoiminnallisuudet
	@RequestMapping(value = "/admin")
	public String adminSettings(Model model) {
		model.addAttribute("users", urepository.findAll());
		return "admin";
	}

	// lottopelin lopputulos
	@RequestMapping(value = "/results")
	public String showResults() {
		return "results";
	}

	// pääsivu
	@RequestMapping(value = "/main")
	public String showRows(Model model) {
		model.addAttribute("rows", repository.findAll());
		model.addAttribute("users", urepository.findAll());
		return "main";
	}

	// poista rivi / poista käyttäjä

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteRow(@PathVariable("id") Long rowid, Model model) {
		String sql = "DELETE FROM lottorivi WHERE id=" + rowid + ";";
		jdbcTemplate.execute(sql);
		model.addAttribute("rows", repository.findAll());
		return "redirect:../main";
	}

	@RequestMapping(value = "/deleteuser/{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("id") Long rowid, Model model) {
		String sql = "DELETE FROM user WHERE id=" + rowid + ";";
		jdbcTemplate.execute(sql);
		model.addAttribute("users", urepository.findAll());
		return "redirect:../admin";
	}

	// Lottorivin muokkaus

	@RequestMapping(value = "/edit/{id}")
	public String editBook(@PathVariable("id") Long Id, Model model) {
		List<Lottorivi> lottorivi = repository.findById(Id);
		Lottorivi oikearivi = lottorivi.get(0);
		model.addAttribute("lottorivi", oikearivi);
		return "editrow";
	}

	@RequestMapping(value = "/tallenna", method = RequestMethod.POST)
	public String tallenna(Lottorivi lottorivi, Model model) {
		Long id = lottorivi.getId();
		String playname = lottorivi.getPlayname();
		String row = lottorivi.getRow();
		Long wins = lottorivi.getWins();
		jdbcTemplate.update("UPDATE lottorivi SET playname=?, row=?, wins=? WHERE id=?;", playname, row, wins, id);
		return "redirect:main";
	}

	// Uuden lottorivin luonti ja tietokantaan ajo
	@RequestMapping(value = "/newrow")
	public String newrow(Model model) {

		// generoidaan lottorivistä sekä list-olio, että string
		List<Long> rivi = lottofunktiot.generoiRivi();
		String rivi1 = lottofunktiot.stringify(rivi);
		model.addAttribute("rivi1", rivi1);

		List<Long> rivi22 = lottofunktiot.generoiRivi();
		String rivi2 = lottofunktiot.stringify(rivi22);
		model.addAttribute("rivi2", rivi2);

		// Lasketaan samojen lukujen määrä rivien välillä ja mitkä luvut olivat
		int oikeat = lottofunktiot.laskeOikeat(rivi, rivi22);
		model.addAttribute("oikein", oikeat);
		String nimi = SecurityContextHolder.getContext().getAuthentication().getName();
		String oikealista = lottofunktiot.listaaOikeat(rivi, rivi22);
		model.addAttribute("samat", oikealista);

		jdbcTemplate.update("INSERT INTO lottorivi(playname, row, wins) VALUES (?,?,?)", nimi, rivi2, oikeat);

		return "results";
	}

	// Tietokannan palautus lähtöpisteeseen
	@RequestMapping(value = "/reset")
	public String resetEverything(Model model) {
		String sql = "DROP TABLE lottorivi;";
		jdbcTemplate.execute(sql);

		sql = " DROP TABLE user;";
		jdbcTemplate.execute(sql);

		sql = "CREATE TABLE lottorivi(id INT NOT NULL AUTO_INCREMENT,playname CHAR(30),row CHAR(50),wins SMALLINT,PRIMARY KEY (id));";
		jdbcTemplate.execute(sql);

		sql = "CREATE TABLE user(id INT NOT NULL AUTO_INCREMENT,username CHAR(30),password CHAR(70),role CHAR(10),PRIMARY KEY (id));";
		jdbcTemplate.execute(sql);

		sql = "INSERT INTO lottorivi(playname, row, wins)VALUES('Testimies 1', '1, 2, 3, 4, 5, 6, 7', 5);";
		jdbcTemplate.execute(sql);

		sql = "INSERT INTO lottorivi(playname, row, wins)VALUES('Testimies 2', '8, 9, 10, 11, 12, 13, 14', 3);";
		jdbcTemplate.execute(sql);

		sql = "INSERT INTO lottorivi(playname, row, wins)VALUES('Testimies 3', '15, 16, 17, 18, 19, 20, 21', 1);";
		jdbcTemplate.execute(sql);

		sql = "INSERT INTO user(username, password, role)VALUES('user', '$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6', 'USER');";
		jdbcTemplate.execute(sql);

		sql = "INSERT INTO user(username, password, role)VALUES('admin', '$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C', 'ADMIN');";
		jdbcTemplate.execute(sql);
		model.addAttribute("rows", repository.findAll());
		return "main";
	}
}