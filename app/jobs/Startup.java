package jobs;

import java.util.Date;

import models.Statement;
import models.Suser;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Startup extends Job {

	@Override
	public void doJob() throws Exception {		
		Suser suser = Suser.findById("admin");
		if(suser == null){
			suser = new Suser("admin","12345").save();
			Statement s = new Statement();
			s.owner = suser;
			s.st_text = "GEZİ PARKI DİRENİŞİNİ DESTEKLİYORUM";
			s.entryDate = new Date();
			s.save();
		}		
	}
}
