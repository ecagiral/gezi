package jobs;

import models.Statement;
import models.Suser;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Startup extends Job {

	@Override
	public void doJob() throws Exception {		
		Suser suser = new Suser("admin","12345").save();
		Statement s = new Statement();
		s.owner = suser;
		s.st_text = "Gezi parkı direnişini destekliyorum";
		s.save();
	}
}
