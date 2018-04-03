package net.skhu.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import net.skhu.domain.Article;

@Data
public class ArticleRegistrationModel {

	int no;

	@NotEmpty
	@Size(min=3, max=12)
	String title;

	String body;

	public Article toArticle(){
		Article article = new Article();
		article.setNo(this.no);
//		article.getUser().setId(1);
		article.setTitle(this.title);
		article.setBody(this.body);
		return article;
	}
}
