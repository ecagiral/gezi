package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
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

    public Statement parent;

    public Boolean positive;

    public String text;

    public List<Statement> positiveChild;

    public List<Statement> negativeChild;

    public Suser owner;

}
