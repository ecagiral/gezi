package models;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.ForeignKey;
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
    @ForeignKey(name="statementchild_FK")
    public Statement parent;

    @OneToMany(mappedBy="parent")
    @Cascade(value = {CascadeType.DELETE })
    public List<Statement> children;

	@Temporal(TemporalType.TIMESTAMP)
	public Date entryDate;
	
    public Boolean positive;

    @Column(columnDefinition="TEXT")    
    public String st_text;

    @OneToMany(mappedBy = "statement")
    @Cascade(value = {CascadeType.DELETE })
    public List<Upvote> upvotes;
    
    public List<Statement> getNegativeChild() {
		return Statement.find("parent = ? and positive = ? order by point DESC", this , Boolean.FALSE).fetch();
	}
    
    public List<Statement> getPositiveChild() {
    	return Statement.find("parent = ? and positive = ? order by point DESC", this , Boolean.TRUE).fetch();
	}
    @ManyToOne
    public Suser owner;

    public int point;
    
    public String getAbbrTitle(int maxLength){
      
            if(st_text.length() > maxLength){
                //Conversation.anchorString
                if(st_text.contains("<a href=")){
                	return st_text.substring(0,maxLength) + "...";
                }else{
                	return st_text.substring(0,maxLength) + "...";
                }
            }else{
                return st_text;
            }
			

    }

}
