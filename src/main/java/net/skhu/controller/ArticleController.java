package net.skhu.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.skhu.domain.Article;
import net.skhu.domain.Board;
import net.skhu.model.ArticleRegistrationModel;
import net.skhu.model.Pagination;
import net.skhu.repository.ArticleRepository;
import net.skhu.repository.BoardRepository;
import net.skhu.repository.UserRepository;

@Controller
@RequestMapping("article")
public class ArticleController {
	@Autowired
	ArticleRepository articleRepository;
	@Autowired
	BoardRepository boardRepository;
	@Autowired
	UserRepository userRepository;

	@RequestMapping("list")
	public String list(Pagination pagination, Model model) {
		Board board = boardRepository.findOne(pagination.getBd());
		List<Article> list = articleRepository.findAll(pagination);
		model.addAttribute("board", board);
		model.addAttribute("list", list);
		model.addAttribute("orderBy", ArticleRepository.orderBy);
		model.addAttribute("searchBy", ArticleRepository.searchBy);
		return "article/list";
	}

	@RequestMapping("view")
	public String view(@RequestParam("id") int id, Pagination pagination, Model model) {
		Article article = articleRepository.findOne(id);
		model.addAttribute("article", article);
		return "article/views";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(ArticleRegistrationModel article, Pagination pagination, Model model) {
		Board board = boardRepository.findOne(pagination.getBd());
		model.addAttribute("board", board.getBoardName() + "작성");
		return "article/edit";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid ArticleRegistrationModel articleModel, BindingResult bindingResult, Pagination pagination, Model model) {
		if(bindingResult.hasErrors()){
			Board board = boardRepository.findOne(pagination.getBd());
			model.addAttribute("board", board.getBoardName() + "작성");
			return "article/edit";
		}
		Article article = articleModel.toArticle();
		articleRepository.save(article);
		pagination.setPg(1);
		return "redirect:list?" + pagination.getQueryString();
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(@RequestParam("id") int id, Pagination pagination, Model model) {
		Board board = boardRepository.findOne(pagination.getBd());
		model.addAttribute("article", articleRepository.findOne(id));
		model.addAttribute("board", board.getBoardName() + "수정");
		return "article/edit";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(@Valid ArticleRegistrationModel articleModel, BindingResult bindingResult, Pagination pagination, Model model) {
		if(bindingResult.hasErrors()){
			Board board = boardRepository.findOne(pagination.getBd());
			model.addAttribute("board", board.getBoardName() + "수정");
			return "article/edit";
		}
		Article article = articleModel.toArticle();
		articleRepository.save(article);
		model.addAttribute("message", "저장했습니다.");
		return "article/edit";
	}

	@RequestMapping("delete")
	public String delete(@RequestParam("id") int id, Pagination pagination, Model model) {
		articleRepository.delete(id);
		return "redirect:list?" + pagination.getQueryString();
	}

}
