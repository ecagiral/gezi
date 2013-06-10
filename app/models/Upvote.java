package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Upvote extends Model {
	
	@ManyToOne()
	public Suser suser;
	
	@ManyToOne()
	public Statement statement;
	
	public Upvote(Suser user, Statement statement){
		this.suser = user;
		this.statement = statement;
	}

}
