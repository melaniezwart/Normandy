package game.webcontrollers;

import game.Game;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by mzwart on 23-11-2016.
 */
@Controller
public class WebController{

	NameForm nameForm;
	Game game;

	@GetMapping("/")
	public String greeting() {
		return "index";
	}

	@GetMapping("/start")
	public String start(NameForm nameForm){
		this.nameForm = nameForm;
		System.out.println("Set name");
		return "start";
	}

	@PostMapping("/start")
	public String startGame(){
		System.out.println("Starting game");
		game = new Game(nameForm.getName());
		System.out.println("Setting name");
		return "game";
	}

	@GetMapping("/game")
	public void gameWindow(){
		String name = game.getNormandy().getCaptain().getName();
		int energy = game.getNormandy().getEnergy();
		int missileDamage = game.getNormandy().getEquippedMissile().getDamage();
		int hull = game.getNormandy().getArmor().getHullHealth();

	}
}
