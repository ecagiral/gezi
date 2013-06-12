package models;

import play.data.validation.Valid;
import play.db.jpa.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	@Temporal(TemporalType.TIMESTAMP)
	public Date entryDate;
	
    public Boolean positive;

    @Column(columnDefinition="TEXT")    
    public String st_text;

    
    public List<Statement> getNegativeChild() {
		return Statement.find("parent = ? and positive = ? order by entryDate DESC", this , Boolean.FALSE).fetch();
	}
    
    public List<Statement> getPositiveChild() {
    	return Statement.find("parent = ? and positive = ? order by entryDate DESC", this , Boolean.TRUE).fetch();
	}
    @ManyToOne
    public Suser owner;

    public int point;

}
