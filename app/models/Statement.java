package models;

import play.data.validation.Valid;
import play.db.jpa.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ermancagiral
 * Date: 10.06.2013
 * Time: 14:21
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class Statement extends Model {

	@ManyToOne()
    public Statement parent;

    public Boolean positive;

    @Column(columnDefinition="TEXT")    
    public String st_text;

    
    public List<Statement> getNegativeChild() {
		return Statement.find("parent = ? and positive = ?", this , Boolean.FALSE).fetch();
	}
    
    public List<Statement> getPositiveChild() {
    	return Statement.find("parent = ? and positive = ?", this , Boolean.TRUE).fetch();
	}
    @ManyToOne
    public Suser owner;

    public int point;

}
